package com.jesusfc.springboot3java17.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@RestController
@AllArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex){
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorized(UnauthorizedException ex){
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    protected ResponseEntity<Object> handleExpiredJwt(UnauthorizedException ex){
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }
/*
    @ExceptionHandler(value = JwtTokenMissingException.class)
    protected ResponseEntity<Object> handleJwtTokenMissing(JwtTokenMissingException ex){
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }
*/
    @ExceptionHandler(value = ForbiddenException.class)
    protected ResponseEntity<Object> handleForbidden(ForbiddenException ex){
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex){
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AppException.class)
    protected ResponseEntity<Object> handleAppException(NotFoundException ex, WebRequest request){
        String genericErrorMessage = messageSource.getMessage("api.error.generic.text", null, getLocaleRequestHeader(request));
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), genericErrorMessage);
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected final ResponseEntity<ApiError> handleAnyExceptions(Exception ex, WebRequest request) {
        String genericErrorMessage = messageSource.getMessage("api.error.generic.text", null, getLocaleRequestHeader(request));
        ApiError details = new ApiError(LocalDateTime.now(ZoneOffset.UTC), genericErrorMessage);
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    public ResponseEntity<Map<String, String>> handleMultipartException(MaxUploadSizeExceededException ex) {
        Map<String, String> result = new HashMap<>();
        result.put("message", "Error ==> Large File is too large ");
        return  ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(result);

    }

    private Locale getLocaleRequestHeader(WebRequest request){
        if (request.getHeader(HttpHeaders.ACCEPT_LANGUAGE) == null) return LocaleContextHolder.getLocale();
        return new Locale(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE));
    }

}


