package vescame.orderstatuses.httpapi.security.jwt.convert;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final UserDetailsManager userDetailsManager;

    public UsernamePasswordAuthenticationConverter(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public UsernamePasswordAuthenticationToken convert(@NotNull Jwt source) {
        var user = userDetailsManager.loadUserByUsername(source.getSubject());
        return new UsernamePasswordAuthenticationToken(user, source, user.getAuthorities());
    }
}
