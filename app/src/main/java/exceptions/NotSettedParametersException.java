package exceptions;

public class NotSettedParametersException extends RuntimeException {
    public NotSettedParametersException(String parameter, String clazz) {
        super(String.format("Don't set parameter '%s' in class '%s'", parameter, clazz));
    }
}
