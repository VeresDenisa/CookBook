package org.ckbk.sre.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
        super(String.format("An account with this username already exists!"));
    }
}
