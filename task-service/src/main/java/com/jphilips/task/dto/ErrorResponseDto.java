package com.jphilips.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public  record ErrorResponseDto(LocalDateTime timestamp,
                                int status, String error,
                                @JsonInclude(JsonInclude.Include.NON_NULL) String message,
                                String path){

}
