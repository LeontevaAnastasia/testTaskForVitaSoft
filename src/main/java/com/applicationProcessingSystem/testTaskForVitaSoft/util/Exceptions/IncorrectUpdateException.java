package com.applicationProcessingSystem.testTaskForVitaSoft.util.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Invalid edit")
public class IncorrectUpdateException extends RuntimeException{
}
