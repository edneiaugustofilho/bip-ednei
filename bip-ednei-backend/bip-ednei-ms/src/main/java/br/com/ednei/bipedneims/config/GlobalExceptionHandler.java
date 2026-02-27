package br.com.ednei.bipedneims.config;

import br.com.ednei.bipedneims.helper.ApiErrorBuilder;
import br.com.ednei.bipedneims.http.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(ApiErrorBuilder.build(e.getMessage(), HttpStatus.BAD_REQUEST.name()));
    }
}
