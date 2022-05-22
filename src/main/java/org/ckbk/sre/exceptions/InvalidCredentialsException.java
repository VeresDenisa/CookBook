package org.ckbk.sre.exceptions;

public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException() {
        super("Invalid credentials! Try again...");
    }
}
