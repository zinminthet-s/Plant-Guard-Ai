package com.zinminthet.plantguardai.auth.exceptionHandlers;

import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorizationFailureHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, String> errorMessages = new HashMap<>();
        errorMessages.put("admin", "Only admin roles can access to this resource");
        errorMessages.put("farmer", "Only farmer roles can access to this resource");
        errorMessages.put("merchant", "Only merchant roles can access to this resource");

        List<String> roles = List.of("admin", "farmer", "merchant");

        String message = null;

        for(var role: roles) {
            if(request.getRequestURI().contains(role)){
                message = errorMessages.get(role);
            }
        }

        if(message == null ) {
            message = "You don't have access to this resource";
        }

         response.setStatus(HttpServletResponse.SC_FORBIDDEN);
         response.setContentType("application/json"); response.setCharacterEncoding("UTF-8");


        var apiResponse = ResponseBuilder.error(
                HttpServletResponse.SC_UNAUTHORIZED,
                message,
                request,
                null
        );

        String jsonResponse = String.format(
                "{" +
                        "\"statusText\":\"%s\"," +
                        "\"statusCode\":%d," +
                        "\"message\":\"%s\"," +
                        "\"data\":%s," +
                        "\"errors\":%s," +
                        "\"meta\":{" +
                        "\"requestId\":\"%s\"," +
                        "\"path\":\"%s\"," +
                        "\"timestamp\":\"%s\"," +
                        "\"method\":\"%s\"" +
                        "}," +
                        "\"pagination\":%s" +
                        "}",
                apiResponse.getStatusText(),
                apiResponse.getStatusCode(),
                apiResponse.getMessage(),
                apiResponse.getData() == null ? "null" : "\"" + apiResponse.getData() + "\"",
                apiResponse.getErrors() == null ? "null" : "\"" + apiResponse.getErrors() + "\"",
                apiResponse.getMeta().getRequestId(),
                apiResponse.getMeta().getPath(),
                apiResponse.getMeta().getTimestamp(),
                apiResponse.getMeta().getMethod(),
                apiResponse.getPagination() == null ? "null" : "\"" + apiResponse.getPagination() + "\""
        );

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);


        return;
    }
}
