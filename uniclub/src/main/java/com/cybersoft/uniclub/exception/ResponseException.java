package com.cybersoft.uniclub.exception;

import com.cybersoft.uniclub.payload.respond.BaseRespond;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseException {
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e){
        BaseRespond baseRespond = new BaseRespond();
        baseRespond.setStatusCode(500);
        baseRespond.setMessage(e.getMessage());
        return new ResponseEntity<>(baseRespond, HttpStatus.OK);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(item -> {
            String field = ((FieldError) item).getField();
            String message = item.getDefaultMessage();
            errors.put(field,message);
        });

        BaseRespond baseRespond = new BaseRespond();
        baseRespond.setStatusCode(400);
        baseRespond.setMessage("");
        baseRespond.setData(errors);

        return new ResponseEntity<>(baseRespond, HttpStatus.OK);
    }
}


