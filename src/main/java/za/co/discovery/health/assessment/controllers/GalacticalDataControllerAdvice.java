package za.co.discovery.health.assessment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.discovery.health.assessment.exceptions.DuplicateEntryException;
import za.co.discovery.health.assessment.exceptions.ErrorDetails;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

//TODO: Add other exceptions - PersistenceException, etc

@ControllerAdvice
public class GalacticalDataControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException enfEx, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), enfEx.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public final ResponseEntity<ErrorDetails> handleDuplicateEntryException(DuplicateEntryException deEx, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), deEx.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleOtherException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "whoops!! on collision path", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }
}
