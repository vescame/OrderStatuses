package vescame.orderstatuses.httpapi.security.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import vescame.orderstatuses.httpapi.security.jwt.JWTSecurityConfiguration;
import vescame.orderstatuses.httpapi.security.jwt.encoder.AccessTokenJwtEncoder;
import vescame.orderstatuses.httpapi.security.jwt.decoder.AccessTokenJwtDecoder;
import javax.crypto.spec.SecretKeySpec;
import static com.nimbusds.jose.JWSAlgorithm.HS512;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static vescame.orderstatuses.httpapi.security.SecurityAuthority.READ;
import static vescame.orderstatuses.httpapi.security.SecurityAuthority.WRITE;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JWTSecurityConfiguration jwtSecurityConfiguration;
    private final Converter<Jwt, UsernamePasswordAuthenticationToken> converter;

    public SecurityConfiguration(
            JWTSecurityConfiguration jwtSecurityConfiguration,
            Converter<Jwt, UsernamePasswordAuthenticationToken> converter
    ) {
        this.jwtSecurityConfiguration = jwtSecurityConfiguration;
        this.converter = converter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AccessTokenJwtDecoder decoder) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(GET, "/orders/**", "/items").hasAuthority(READ.name())
                .requestMatchers(POST, "/orders").hasAuthority(WRITE.name())
                .requestMatchers(PUT, "/orders/**").hasAuthority(WRITE.name())
                .requestMatchers(POST, "/oauth", "/oauth/**").permitAll()
                .requestMatchers(GET, "/**").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(converter)
                .decoder(decoder.decoder());

        return http.build();
    }

    @Bean
    AccessTokenJwtEncoder accessTokenEncoder(JWKSource<SecurityContext> jwkSource) {
        return new AccessTokenJwtEncoder(new NimbusJwtEncoder(jwkSource));
    }

    @Bean
    AccessTokenJwtDecoder accessTokenDecoder(SecretKeySpec secretKey, JWSKeySelector<SecurityContext> keySelector) {
        return new AccessTokenJwtDecoder(
                NimbusJwtDecoder
                        .withSecretKey(secretKey)
                        .jwtProcessorCustomizer(processor -> processor.setJWSKeySelector(keySelector))
                        .build()
        );
    }

    @Bean
    JWSVerificationKeySelector<SecurityContext> jwsKeySelector(JWKSource<SecurityContext> source) {
        return new JWSVerificationKeySelector<>(HS512, source);
    }

    @Bean
    JWKSource<SecurityContext> jwkSource() {
        JWK key = new OctetSequenceKey
                .Builder(jwtSecurityConfiguration.getTokenSecret().getBytes())
                .algorithm(HS512)
                .build();

        JWKSet jwkSet = new JWKSet(key);

        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }
}
