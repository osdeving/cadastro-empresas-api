package br.com.broadfactor.cadempresas.security;

import br.com.broadfactor.cadempresas.model.Login;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public static final int TOKEN_TEMPO_EXPIRACAO = 60;
    public static final String TOKEN_SENHA = "e937a364-f78f-4c79-8193-5cc16a511869";

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Login login = new ObjectMapper()
                    .readValue(request.getInputStream(), Login.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getLogin(),
                            login.getPassword(),
                            new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuário", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        DetalhesDoUsuario detalhesDoUsuario = (DetalhesDoUsuario) authResult.getPrincipal();

        Instant expiration = Instant.now().plus(TOKEN_TEMPO_EXPIRACAO, ChronoUnit.MINUTES);

        String token = JWT.create()
                .withSubject(detalhesDoUsuario.getUsername())
                .withExpiresAt(Date.from(expiration))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
