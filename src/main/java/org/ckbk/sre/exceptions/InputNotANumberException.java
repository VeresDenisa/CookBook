package org.ckbk.sre.exceptions;

public class InputNotANumberException  extends Exception{

    public InputNotANumberException() {
        super("Input filed requires a number!");
    }
}
