package com.smlikelion.webfounder.manage.exception;

import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import com.smlikelion.webfounder.global.dto.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ManageExceptionHandler {
    @ExceptionHandler(UnsupportedTrackException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleUnsupportedTrackException(UnsupportedTrackException e, HttpServletRequest request) {
        log.warn("MANAGE-001> 요청 URI: " + request.getRequestURI() + ", 에러메세지: " + e.getMessage());
        return new BaseResponse<>(ErrorCode.UNSUPPORTED_TRACK_ERROR);
    }

    @ExceptionHandler(MismatchedYearException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleMismatchedYearException(MismatchedYearException e, HttpServletRequest request) {
        log.warn("MANAGE-002> 요청 URI: " + request.getRequestURI() + ", 에러메세지: " + e.getMessage());
        return new BaseResponse<>(ErrorCode.MISMATCHED_YEAR_ERROR);
    }

    @ExceptionHandler(AlreadyExistsQuestionNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleAlreadyExistsQuestionNumberException(AlreadyExistsQuestionNumberException e, HttpServletRequest request) {
        log.warn("MANAGE-003> 요청 URI: " + request.getRequestURI() + ", 에러메세지: " + e.getMessage());
        return new BaseResponse<>(ErrorCode.ALREADY_EXISTS_QUESTION_NUMBER_ERROR);
    }

}
