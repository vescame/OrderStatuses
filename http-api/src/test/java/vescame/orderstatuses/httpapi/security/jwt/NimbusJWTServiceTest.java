package vescame.orderstatuses.httpapi.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import vescame.orderstatuses.httpapi.security.SecurityAuthentication;
import vescame.orderstatuses.httpapi.security.jwt.encoder.AccessTokenJwtEncoder;
import vescame.orderstatuses.httpapi.security.token.BearerToken;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NimbusJWTServiceTest {

    private final JwtEncoder jwtEncoder = mock(JwtEncoder.class);
    private final AccessTokenJwtEncoder encoder = new AccessTokenJwtEncoder(jwtEncoder);
    private final JWTSecurityConfiguration configuration = mock(JWTSecurityConfiguration.class);
    private final NimbusJWTService service = new NimbusJWTService(encoder, configuration);

    @Test
    public void shouldGenerateTokenSuccessfully() {
        var jwt = mock(Jwt.class);
        var auth = mock(Authentication.class);
        var user = User
                .builder()
                .username("user")
                .password("123")
                .authorities(new SimpleGrantedAuthority("login"))
                .build();

        SecurityAuthentication securityAuthentication = new SecurityAuthentication(auth);

        Long expiration = 3600L;
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYzMzkwMjJ9" +
                ".TO_0En41lJDjlVdN_gyIrD1RXnB9ml9irySvZFDo72E";
        var expected = new BearerToken(token, expiration);

        when(configuration.getTokenDuration()).thenReturn(Duration.ofSeconds(expiration));
        when(configuration.getTokenIssuer()).thenReturn("vescame.orderstatuses");
        when(configuration.getTokenExpirationInSeconds()).thenReturn(expiration);

        when(jwtEncoder.encode(any())).thenReturn(jwt);

        when(jwt.getTokenValue()).thenReturn(token);

        when(auth.getPrincipal()).thenReturn(user);

        var actual = service.generate(securityAuthentication);

        assertEquals(expected, actual);
    }
}