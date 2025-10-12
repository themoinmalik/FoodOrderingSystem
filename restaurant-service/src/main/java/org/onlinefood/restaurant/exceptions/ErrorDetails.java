package org.onlinefood.restaurant.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorDetails {


    private int statusCode;
    private String message;
    private String details;


}
