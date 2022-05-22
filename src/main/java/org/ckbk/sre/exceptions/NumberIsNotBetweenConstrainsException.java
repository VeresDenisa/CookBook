package org.ckbk.sre.exceptions;

public class NumberIsNotBetweenConstrainsException extends Exception{

    public NumberIsNotBetweenConstrainsException() {
        super("Number has to be between 0 and 5.");
    }
}
