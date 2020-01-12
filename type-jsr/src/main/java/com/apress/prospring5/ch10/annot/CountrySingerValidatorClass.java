package com.apress.prospring5.ch10.annot;

import com.apress.prospring5.ch10.entities.Singer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidatorClass implements ConstraintValidator<CheckCountrySinger, Singer> {
    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {

    }

    @Override
    public boolean isValid(Singer singer, ConstraintValidatorContext context) {
        boolean result = true;
        if (singer.getGenre() != null
                && (singer.isCountrySinger()
                && (singer.getLastName() == null || singer.getGender() == null))) {
            return false;
        }
        return result;
    }
}
