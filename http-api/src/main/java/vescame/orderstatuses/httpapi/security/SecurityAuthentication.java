package vescame.orderstatuses.httpapi.security;

import org.springframework.security.core.Authentication;

public record SecurityAuthentication(Authentication authentication) { /* empty */ }
