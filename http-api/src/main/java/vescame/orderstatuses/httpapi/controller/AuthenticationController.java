package vescame.orderstatuses.httpapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vescame.orderstatuses.httpapi.security.SecurityAuthentication;
import vescame.orderstatuses.httpapi.security.jwt.JWTService;
import vescame.orderstatuses.httpapi.security.request.AccessTokenRequest;
import vescame.orderstatuses.httpapi.security.token.BearerToken;

@RestController
@RequestMapping("/oauth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JWTService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<BearerToken> accessToken(@RequestBody AccessTokenRequest accessTokenRequest) {
        SecurityAuthentication authentication = new SecurityAuthentication(
                authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken.unauthenticated(
                                accessTokenRequest.username(),
                                accessTokenRequest.password()
                        )
                )
        );

        return ResponseEntity.ok(jwtService.generate(authentication));
    }
}
