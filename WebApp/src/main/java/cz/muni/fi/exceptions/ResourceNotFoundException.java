package cz.muni.fi.exceptions;

/**
 * Created by Vaculik on 02/02/2016.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
