package org.ckbk.sre.exceptions;

public class InputNotANumberException  extends Exception{

    public InputNotANumberException() {
        super(String.format("Input filed requires a number!"));
    }
}
