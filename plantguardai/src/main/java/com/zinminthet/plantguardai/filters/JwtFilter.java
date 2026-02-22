package com.zinminthet.plantguardai.filters;

import com.zinminthet.plantguardai.utils.JwtUtils;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.lang.Objects;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            var authorizationHeader = request.getHeader("Authorization");
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                var token = authorizationHeader.trim().split(" ")[1];
                if(!Objects.isEmpty(token)){
                    var claims = JwtUtils.validate(token);
                    var role = "ROLE_" + claims.get("role", String.class);
                    var authenticationToken =  new UsernamePasswordAuthenticationToken(claims.get("email", String.class), null, AuthorityUtils.commaSeparatedStringToAuthorityList(role));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }catch (JwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            var errorResponse = ResponseBuilder.error( HttpServletResponse.SC_UNAUTHORIZED, e.getMessage(), request, null );
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return;
        }finally {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        if("/test".endsWith(request.getRequestURI())){
//            return true;
//        }
        return true;
    }
}
