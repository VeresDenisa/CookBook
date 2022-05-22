package org.ckbk.sre.exceptions;

public class EmptyInputFieldException extends Exception{

    public EmptyInputFieldException() {
        super("Empty input field! Fill all input fields!");
    }
}
