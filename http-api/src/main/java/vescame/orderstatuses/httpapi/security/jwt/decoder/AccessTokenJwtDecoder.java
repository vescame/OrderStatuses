package vescame.orderstatuses.httpapi.security.jwt.decoder;

import org.springframework.security.oauth2.jwt.JwtDecoder;

public record AccessTokenJwtDecoder(JwtDecoder decoder) { /* empty */ }
