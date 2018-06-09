package io.ikatoo.handler;

import io.ikatoo.error.ErrorDetails;
import io.ikatoo.error.ResourceNotFoundDetails;
import io.ikatoo.error.ResourceNotFoundException;
import io.ikatoo.error.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
        ResourceNotFoundDetails build = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Recurso não encontrado.")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();
        return new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                          HttpHeaders headers,
                                                          HttpStatus status,
                                                          WebRequest request) {
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldsMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErrorDetails build = ValidationErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Erro na validação de campo.")
                .detail("Erro na validação de campo.")
                .developerMessage(manvException.getClass().getName())
                .field(fields)
                .fieldMessage(fieldsMessages)
                .build();
        return new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception manvException, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Exceção interna")
                .detail(manvException.getMessage())
                .developerMessage(manvException.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, headers, status);
    }
}
