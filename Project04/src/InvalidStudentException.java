/**
 * The main class of the program, that links together all of the other classes in this project.
 *
 * @author Darius Tse
 * @version today's date!
 */

public class InvalidStudentException extends Exception {
    public InvalidStudentException() {

    }

    public InvalidStudentException(String message) {
        super(message);
    }
}
