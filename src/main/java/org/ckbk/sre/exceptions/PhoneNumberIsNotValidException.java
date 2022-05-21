package org.ckbk.sre.exceptions;

public class PhoneNumberIsNotValidException extends Exception{

    public PhoneNumberIsNotValidException() {
        super(String.format("The phone number is not valid."));
    }
}
