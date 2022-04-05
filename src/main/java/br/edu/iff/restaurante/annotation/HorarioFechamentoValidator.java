package br.edu.iff.restaurante.annotation;

import br.edu.iff.restaurante.model.Comanda;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class HorarioFechamentoValidator implements ConstraintValidator<HorarioFechamentoValidation, Comanda> {


    @Override
    public boolean isValid(Comanda comanda, ConstraintValidatorContext constraintValidatorContext) {
        return comanda.getHorarioFechamento().isBefore(comanda.getHorarioAbertura()) ? false : true;
    }
}
