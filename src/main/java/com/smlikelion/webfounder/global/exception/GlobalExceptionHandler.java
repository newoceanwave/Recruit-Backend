package com.smlikelion.webfounder.global.exception;

import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import com.smlikelion.webfounder.global.dto.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation errors:\n");

            bindingResult.getFieldErrors().forEach(fieldError ->
                    errorMessage.append(fieldError.getField())
                            .append(": ")
                            .append(fieldError.getDefaultMessage())
                            .append("\n")
            );
            log.warn("GLOBAL-001> 요청 URI: " + request.getRequestURI() + ", 에러메세지: " + errorMessage.toString());
            return new BaseResponse<>(ErrorCode.INVALID_INPUT_VALUE_ERROR, errorMessage.toString());
        }

        log.warn("GLOBAL-001> 요청 URI: " + request.getRequestURI() + ", 에러메세지: " + "Invalid input value");
        return new BaseResponse<>(ErrorCode.INVALID_INPUT_VALUE_ERROR, "Invalid input value");
    }
}
