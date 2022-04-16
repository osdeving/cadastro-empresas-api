package br.com.broadfactor.cadempresas.configuration.validation;

import br.com.broadfactor.cadempresas.exceptions.CnpjJaExisteException;
import br.com.broadfactor.cadempresas.exceptions.EmailJaExisteException;
import br.com.broadfactor.cadempresas.exceptions.EmailNaoCorrespondeException;
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
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
        List<ErroDeFormularioDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);

            dto.add(erro);
        });

        return dto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailJaExisteException.class)
    public ErroDeFormularioDto handle(EmailJaExisteException exception) {
        return new ErroDeFormularioDto("email", exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CnpjJaExisteException.class)
    public ErroDeFormularioDto handle(CnpjJaExisteException exception) {
        return new ErroDeFormularioDto("cnpj", exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNaoCorrespondeException.class)
    public ErroDeFormularioDto handle(EmailNaoCorrespondeException exception) {
        return new ErroDeFormularioDto("email", exception.getMessage());
    }
}
