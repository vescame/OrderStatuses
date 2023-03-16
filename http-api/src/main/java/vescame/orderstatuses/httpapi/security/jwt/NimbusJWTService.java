package vescame.orderstatuses.httpapi.security.jwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import vescame.orderstatuses.httpapi.security.SecurityAuthentication;
import vescame.orderstatuses.httpapi.security.jwt.encoder.AccessTokenJwtEncoder;
import vescame.orderstatuses.httpapi.security.token.BearerToken;

import java.time.Instant;
import java.util.UUID;

@Component
public class NimbusJWTService implements JWTService {

    private final AccessTokenJwtEncoder encoder;
    private final JWTSecurityConfiguration jwtSecurityConfiguration;

    public NimbusJWTService(AccessTokenJwtEncoder encoder, JWTSecurityConfiguration jwtSecurityConfiguration) {
        this.encoder = encoder;
        this.jwtSecurityConfiguration = jwtSecurityConfiguration;
    }

    @Override
    public BearerToken generate(SecurityAuthentication authentication) {
        User user = (User) authentication.authentication().getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getUsername())
                .issuer(jwtSecurityConfiguration.getTokenIssuer())
                .issuedAt(now)
                .expiresAt(now.plus(jwtSecurityConfiguration.getTokenDuration()))
                .build();

        JwsHeader headers = JwsHeader.with(MacAlgorithm.HS512).build();

        String token = encoder.encoder().encode(JwtEncoderParameters.from(headers, claimsSet)).getTokenValue();

        return new BearerToken(
                token,
                jwtSecurityConfiguration.getTokenExpirationInSeconds()
        );
    }
}
