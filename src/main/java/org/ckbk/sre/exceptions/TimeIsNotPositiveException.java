package org.ckbk.sre.exceptions;

public class TimeIsNotPositiveException  extends Exception{

    public TimeIsNotPositiveException() {
        super(String.format("Time is not positive."));
    }
}
