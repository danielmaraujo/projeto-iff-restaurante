package br.edu.iff.restaurante.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCPFValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCPFValidation {
    String message() default "CPF já foi cadastrado.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
