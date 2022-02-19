package com.kbtg.techkamp.week1.shop.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationHandlerError {

    public static ObjectMapper getException(final HttpServletResponse response, final HttpStatus status, final int code, final String message) {
        try {
            final ErrorData authenticationError = new ErrorData(message, code);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setStatus(status.value());
            response.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(), authenticationError);
            return objectMapper;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
