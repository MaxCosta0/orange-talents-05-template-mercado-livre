package br.com.zupacademy.maxley.mercadolivre.config.annotation;

import br.com.zupacademy.maxley.mercadolivre.config.validation.ExisteIdValidator;
import br.com.zupacademy.maxley.mercadolivre.config.validation.ValorUnicoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExisteIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {
    String message() default "Este id nao existe";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName();
    Class<?> domainClass();
}
