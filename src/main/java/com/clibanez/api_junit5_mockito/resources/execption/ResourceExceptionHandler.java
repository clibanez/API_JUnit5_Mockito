package com.clibanez.api_junit5_mockito.resources.execption;

import com.clibanez.api_junit5_mockito.services.exception.DataIntegratyViolationException;
import com.clibanez.api_junit5_mockito.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException objectNotFoundException,
                                                                 HttpServletRequest HttpServletRequest){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Objeto não Encontrado! ", objectNotFoundException.getMessage(),HttpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardError> dataIntegratyViolationException(DataIntegratyViolationException dataIntegratyViolationException,
                                                                 HttpServletRequest HttpServletRequest){
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Objeto não Encontrado! ", dataIntegratyViolationException.getMessage(),HttpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}

