package com.example.parking.global.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultResponse<T> {

    private T response;

    private HttpStatus status;

}
