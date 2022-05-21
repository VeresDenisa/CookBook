package org.ckbk.sre.exceptions;

public class PasswordComplexityIsTooLowException extends Exception{

    public PasswordComplexityIsTooLowException() {
        super(String.format("The password is not strong enough. The password must contain: 8-20 characters and at least 1 digit, 1 special character, 1 upper letter and 1 lower letter."));
    }
}
