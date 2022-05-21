package org.ckbk.sre.exceptions;

public class NumberIsNotBetweenConstrainsException extends Exception{

    public NumberIsNotBetweenConstrainsException() {
        super(String.format("Number has to be between 0 and 5."));
    }
}
