package org.ckbk.sre.exceptions;

public class InvalidImagePathException extends Exception{

    public InvalidImagePathException() {
        super(String.format("Image is not available."));
    }
}
