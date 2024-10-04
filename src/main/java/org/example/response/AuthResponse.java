package org.example.response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.model.USER_ROLE;
import lombok.Data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class AuthResponse {
        private String jwt;
        private String message;
        private USER_ROLE role;

    }

