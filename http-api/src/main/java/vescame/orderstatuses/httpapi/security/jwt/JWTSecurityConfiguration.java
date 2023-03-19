package vescame.orderstatuses.httpapi.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Configuration
public class JWTSecurityConfiguration {

    private final Duration tokenDuration;
    private final String tokenIssuer;
    private final String tokenSecret;
    private final String adminUsername;
    private final String adminPassword;

    public JWTSecurityConfiguration(
            @Value("${jwt.auth.accessToken.duration}") Duration tokenDuration,
            @Value("${jwt.auth.issuer}") String tokenIssuer,
            @Value("${jwt.auth.secret}") String tokenSecret,
            @Value("${jwt.auth.admin.username}") String adminUsername,
            @Value("${jwt.auth.admin.password}") String adminPassword
    ) {
        this.tokenDuration = tokenDuration;
        this.tokenIssuer = tokenIssuer;
        this.tokenSecret = tokenSecret;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public Duration getTokenDuration() {
        return tokenDuration;
    }

    public Long getTokenExpirationInSeconds() {
        return tokenDuration.toSeconds();
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }
}
