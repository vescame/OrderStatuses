package vescame.orderstatuses.httpapi.security.token;

public record BearerToken(String accessToken, Long expiresIn) { /* empty */ }
