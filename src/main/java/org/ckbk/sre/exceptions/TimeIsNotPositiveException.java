package org.ckbk.sre.exceptions;

public class TimeIsNotPositiveException  extends Exception{

    public TimeIsNotPositiveException() {
        super("Time is not positive.");
    }
}
