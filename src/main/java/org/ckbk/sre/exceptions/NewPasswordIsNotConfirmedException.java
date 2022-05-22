package org.ckbk.sre.exceptions;

public class NewPasswordIsNotConfirmedException  extends Exception{

    public NewPasswordIsNotConfirmedException() {
        super("New Password is not confirmed.");
    }
}