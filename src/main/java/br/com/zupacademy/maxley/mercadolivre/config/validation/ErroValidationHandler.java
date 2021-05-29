package br.com.zupacademy.maxley.mercadolivre.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidationHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroRequest> handle(MethodArgumentNotValidException exception){
        List<ErroRequest> errosRequest = new ArrayList<>();
        List<FieldError> errs = exception.getBindingResult().getFieldErrors();

        for(FieldError err: errs){
            String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErroRequest erro = new ErroRequest(err.getField(), mensagem);
            errosRequest.add(erro);
        }

        return errosRequest;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ErroRequest> handle(BindException exception){
        List<ErroRequest> errosRequest = new ArrayList<>();
        List<FieldError> errs = exception.getBindingResult().getFieldErrors();

        for(FieldError err: errs){
            String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErroRequest erro = new ErroRequest(err.getField(), mensagem);
            errosRequest.add(erro);
        }

        return errosRequest;
    }
}
