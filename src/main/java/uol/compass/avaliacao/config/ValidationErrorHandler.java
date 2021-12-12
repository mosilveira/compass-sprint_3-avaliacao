package uol.compass.avaliacao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    // Trata a exceção MethodArgumentNotValidException dos métodos POST e PUT
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormValidationErrorDTO> handle(MethodArgumentNotValidException exception) {

        // DTO para exibir os campos e mensagens dos erros
        List<FormValidationErrorDTO> dto = new ArrayList<>();

        // Recupera um lista de possíveis erros e seus campos
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {

            // Captura a mensagem do erro
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            FormValidationErrorDTO error = new FormValidationErrorDTO(e.getField(), message);
            dto.add(error);
        });
        return dto;
    }
}
