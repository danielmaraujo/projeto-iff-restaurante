package br.edu.iff.restaurante.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HorarioFechamentoValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HorarioFechamentoValidation {
    String message() default "Horario de fechamento não pode ser anterior ao horário de abertura";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
