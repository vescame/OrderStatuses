package vescame.orderstatuses.httpapi.security.jwt;

import vescame.orderstatuses.httpapi.security.SecurityAuthentication;
import vescame.orderstatuses.httpapi.security.token.BearerToken;

public interface JWTService {

    BearerToken generate(SecurityAuthentication authentication);
}
