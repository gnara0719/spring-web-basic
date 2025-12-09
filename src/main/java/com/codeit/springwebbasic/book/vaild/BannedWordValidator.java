package com.codeit.springwebbasic.book.vaild;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class BannedWordValidator implements ConstraintValidator<NoBannedWord, String> {

    // 나중에는 금지 단어들을 DB같은 곳에 작성
    private final List<String> BANNED_WORD = List.of("바보", "멍청이");

    @Override
    public void initialize(NoBannedWord constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null) {
            return true;
        }

        for (String bannedWord : BANNED_WORD) {
            if (value.contains(bannedWord)) {
                return false;
            }
        }

        return false;
    }
}
