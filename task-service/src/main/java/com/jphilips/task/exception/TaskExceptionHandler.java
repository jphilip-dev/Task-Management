package com.jphilips.task.exception;

import com.jphilips.task.dto.ErrorResponseDto;
import com.jphilips.task.exception.custom.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@Slf4j
@RestControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTaskNotFoundException(TaskNotFoundException ex, WebRequest request){
        return createResponse(HttpStatus.NOT_FOUND,"ERR_TASK_NOT_FOUND",getPath(request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex, WebRequest request){

       log.warn("Unhandled exception: {}" , ex.getMessage(), ex);

        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, getPath(request));
    }

    // Helper methods

    private ResponseEntity<ErrorResponseDto> createResponse(HttpStatus status, String message, String path){
        var response =  new ErrorResponseDto(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path);
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<ErrorResponseDto> createResponse(HttpStatus status, String path){
        return createResponse(status,null,path);
    }

    private String getPath(WebRequest request){
        return request.getDescription(false).replace("uri=","");
    }


}
