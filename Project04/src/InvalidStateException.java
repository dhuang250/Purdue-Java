/**
 * The main class of the program, that links together all of the other classes in this project.
 *
 * @author Darius Tse
 * @version today's date!
 */

public class InvalidStateException extends Exception {
    public InvalidStateException() {

    }

    public InvalidStateException(String message) {
        super(message);
    }
}
