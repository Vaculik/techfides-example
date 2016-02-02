package cz.muni.fi.exceptions;

/**
 * Created by Vaculik on 02/02/2016.
 */
public class InvalidRequestFormatException extends RuntimeException {

    public InvalidRequestFormatException(String msg) {
        super(msg);
    }
}
