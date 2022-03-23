package br.edu.iff.restaurante.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCPFValidator implements ConstraintValidator<UniqueCPFValidation, String> {
    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        //TODO: Acessar banco de dados e checar se existe algum cliente ou funcionário com o cpf inserido
        return true;
    }
}
