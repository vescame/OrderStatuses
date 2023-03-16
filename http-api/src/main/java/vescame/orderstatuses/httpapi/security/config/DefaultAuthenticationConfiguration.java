package vescame.orderstatuses.httpapi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import vescame.orderstatuses.httpapi.security.SecurityAuthority;
import vescame.orderstatuses.httpapi.security.SecurityUser;
import vescame.orderstatuses.httpapi.security.jwt.JWTSecurityConfiguration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import static org.springframework.security.oauth2.jose.jws.JwsAlgorithms.HS512;

@Configuration
public class DefaultAuthenticationConfiguration {

    private final JWTSecurityConfiguration jwtSecurityConfiguration;

    public DefaultAuthenticationConfiguration(JWTSecurityConfiguration jwtSecurityConfiguration) {
        this.jwtSecurityConfiguration = jwtSecurityConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsManager userDetailsManager(AuthenticationManagerBuilder builder, PasswordEncoder passwordEncoder) {
        SecurityUser user = new SecurityUser(
                jwtSecurityConfiguration.getAdminUsername(),
                passwordEncoder.encode(jwtSecurityConfiguration.getAdminPassword()),
                List.of(SecurityAuthority.READ, SecurityAuthority.WRITE)
        );

        UserDetailsManager manager;
        try {
            manager = builder.inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder)
                    .getUserDetailsService();
        } catch (Exception e) {
            manager = new InMemoryUserDetailsManager();
        }

        manager.createUser(user);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecretKey jwtSecretKey() {
        return new SecretKeySpec(jwtSecurityConfiguration.getTokenSecret().getBytes(), HS512);
    }
}
