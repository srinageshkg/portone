package com.dcp.portone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ObjRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ObjErrorResponse> handleException(ObjNotFoundException onf) {
        ObjErrorResponse oerError = new ObjErrorResponse();   // Body
        oerError.setStatus(HttpStatus.NOT_FOUND.value()); //404
        oerError.setMessage(onf.getMessage());
        oerError.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<ObjErrorResponse>(oerError,HttpStatus.NOT_FOUND);  // body and status code
    }

    @ExceptionHandler
    public ResponseEntity<ObjErrorResponse> handleException(Exception ex) {
        ObjErrorResponse error = new ObjErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<ObjErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
