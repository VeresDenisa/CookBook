package org.ckbk.sre.exceptions;

public class InvalidImagePathException extends Exception{

    public InvalidImagePathException() {
        super("Image is not available.");
    }
}
