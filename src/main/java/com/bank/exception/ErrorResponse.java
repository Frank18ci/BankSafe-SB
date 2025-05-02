package com.bank.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
	private int status;
    private String mensaje;
    private LocalDateTime timestamp;
    
}
