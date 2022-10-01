package com.iot.api.seguridad.excepciones;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> body=new LinkedHashMap<>();
        body.put("timestamp",System.currentTimeMillis());
        body.put("status",status.value());

        List<String> errors=ex.getBindingResult().
                getFieldErrors().stream().map(x ->x.getDefaultMessage()).collect(Collectors.toList());
        body.put("errors",errors);

        return new ResponseEntity<Object>(body,status);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ChangeSetPersister.NotFoundException.class})
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            //org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }*/

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            HttpClientErrorException.Forbidden.class
    })
    @ResponseBody
    public ErrorMessage forbiddenRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            HttpClientErrorException.Conflict.class
    })
    @ResponseBody
    public ErrorMessage conflict(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            //Unauthorized.class,
            org.springframework.security.access.AccessDeniedException.class

    })
    public void unauthorized(){
        //Nothing to do;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }



}
