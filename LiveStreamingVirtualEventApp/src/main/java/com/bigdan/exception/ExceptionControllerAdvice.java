package com.bigdan.exception;

import com.bigdan.entity.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;

// Handler for all  exception


        @ExceptionHandler(Exception.class)
        public String exceptionHandler(Exception ex) {
            return ex.getMessage();
        }


    //Handler for LSVEAException

        @ExceptionHandler(lsveaException.class)
        public ResponseEntity<ErrorMessage> exceptionHandler(lsveaException ex)
        {
            ErrorMessage error = new ErrorMessage();
            error.setMessage(environment.getProperty(ExceptionConstant.SERVER_ERROR.toString()));
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

    //Handler that handles the exception raised because of invalid data that is received as method argument (DTO)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException  ex)
//    {
//        ErrorMessage error = new ErrorMessage();
//
//        error.setMessage(ex.getBindingResult().getAllErrors()
//                .stream().map(ObjectError::getDefaultMessage)//lambda equivalent -> x->x.getDefaultMessage()
//                .collect(Collectors.joining(", ")));
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }


////Handler that handles the exception raised because of invalid data that is received as
////URI parameter (path variables, request parameters)
//@ExceptionHandler(ConstraintViolationException.class)
//public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(ConstraintViolationException ex)
//{
//    ErrorMessage error = new ErrorMessage();
//    error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//    error.setMessage(ex.getConstraintViolations()
//            .stream().map(ConstraintViolation::getMessage)//lambda equivalent -> x->x.getMessage()
//            .collect(Collectors.joining(", ")));
//    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//}

}
