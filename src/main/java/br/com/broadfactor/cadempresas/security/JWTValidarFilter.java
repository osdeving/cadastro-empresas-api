package br.com.broadfactor.cadempresas.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidarFilter extends BasicAuthenticationFilter {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "Bearer ";
    public JWTValidarFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String bearerToken = request.getHeader(HEADER_AUTHORIZATION);

        if(bearerToken == null || !bearerToken.startsWith(AUTHORIZATION_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = bearerToken.replace(AUTHORIZATION_PREFIX, "");
        UsernamePasswordAuthenticationToken authToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String login = JWT.require(Algorithm.HMAC512(JWTAutenticarFilter.TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();

        if(login == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(login, null, new ArrayList<>());
    }
}
