package org.ckbk.sre.exceptions;

public class EmptyInputFieldException extends Exception{

    public EmptyInputFieldException() {
        super(String.format("Empty input field! Fill all input fields!"));
    }
}
