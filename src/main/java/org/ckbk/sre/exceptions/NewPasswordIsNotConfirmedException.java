package org.ckbk.sre.exceptions;

public class NewPasswordIsNotConfirmedException  extends Exception{

    public NewPasswordIsNotConfirmedException() {
        super(String.format("New Password is not confirmed."));
    }
}