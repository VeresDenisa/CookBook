package org.ckbk.sre.exceptions;

public class EmailAddressIsNotValidException extends Exception{

    public EmailAddressIsNotValidException() {
        super("The email address is not valid.");
    }
}