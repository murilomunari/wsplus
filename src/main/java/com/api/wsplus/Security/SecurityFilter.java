package com.api.wsplus.Security;

import com.api.wsplus.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();


        boolean isPublicUrl = requestURI.startsWith("/api/v3/api-docs") ||
                requestURI.startsWith("/api/swagger-ui") ||
                requestURI.equals("/api/swagger-ui.html") ||
                requestURI.equals("/auth/login") ||
                requestURI.equals("/auth/register") ||
                requestURI.equals("/api/client");

        if (isPublicUrl) {
            System.out.println("Acessando URL pública, pulando validação de token: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }


        var token = this.recoverToken(request);

        if (token != null) {
            try {
                var login = tokenService.validateToken(token);

                if (login == null || login.isEmpty()) {
                    System.out.println("Login extraído do token é nulo ou vazio. Token inválido ou malformado.");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    return;
                }

                UserDetails user = userRepository.findByLogin(login);

                if (user == null) {
                    System.out.println("Usuário não encontrado no banco de dados para o login: " + login);
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    return;
                }

                if (user.getAuthorities() == null) {
                    System.out.println("Permissões (Authorities) do usuário são nulas para o login: " + login);
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    return;
                }

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JWTVerificationException e) {
                System.out.println("Falha na verificação do JWT: " + e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            } catch (Exception e) {
                System.out.println("Erro inesperado no filtro de segurança: " + e.getMessage());
                e.printStackTrace();
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return;
            }
        } else {
            System.out.println("Nenhum token de autorização encontrado no cabeçalho para uma rota protegida: " + requestURI);

        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}