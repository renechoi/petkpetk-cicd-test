package com.petkpetk.admin.service.admin;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
@Component
public class VerificationCodeGenerator {

    private static final int CODE_LENGTH = 6;

    public String generateVerificationCode() {
        return new Random().ints(CODE_LENGTH, 0, 10)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining());
    }
}
