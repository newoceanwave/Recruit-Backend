package com.smlikelion.webfounder.admin.exception;

import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import com.smlikelion.webfounder.global.dto.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class AdminExceptionHandler {
    @ExceptionHandler(AlreadyExistsAccountException.class)
    public BaseResponse<?> handleAlreadyExistsAdminException(AlreadyExistsAccountException e, HttpServletRequest request) {
        log.warn("ADMIN-001> 요청 URI: " + request.getRequestURI() + ", 에러메세지: " + e.getMessage());
        return new BaseResponse<>(ErrorCode.ALREADY_EXISTS_ACCOUNT_ERROR);
    }

    @ExceptionHandler(AlreadyExistsNameException.class)
    public BaseResponse<?> handleAlreadyExistsNameException(AlreadyExistsNameException e, HttpServletRequest request) {
        log.warn("ADMIN-002> 요청 URI: " + request.getRequestURI() + ", 에러메세지: " + e.getMessage());
        return new BaseResponse<>(ErrorCode.ALREADY_EXISTS_NAME_ERROR);
    }
}
