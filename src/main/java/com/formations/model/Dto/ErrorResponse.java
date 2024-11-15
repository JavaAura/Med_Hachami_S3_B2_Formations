package com.formations.model.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public  class ErrorResponse {
    private String message;
    private String exception;
    private String url;
    private HttpStatus status;
}