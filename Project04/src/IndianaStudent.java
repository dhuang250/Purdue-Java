import java.util.ArrayList;

/**
 * The main class of the program, that links together all of the other classes in this project.
 *
 * @author Darius Tse
 * @version today's date!
 */

public class IndianaStudent extends CollegeStudent {
    private static ArrayList<String> idNums = new ArrayList<>();

    public IndianaStudent(int age, String firstName, String lastName,
                          String stateOfResidency, String major, String housing) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stateOfResidency = stateOfResidency;
        this.major = major;
        this.housing = housing;
    }

    public IndianaStudent(String id) {
        this.id = id;
    }

    public IndianaStudent() {
    }


    @Override
    public double getTuition() {
        return tuition;
    }

    @Override
    public int getStudentAge() {
        return age;
    }

    @Override
    public String getStudentFirstName() {
        return firstName;
    }

    @Override
    public String getStudentLastName() {
        return lastName;
    }

    @Override
    public String getStateOfResidence() {
        return stateOfResidency;
    }

    @Override
    public String getFullName() {
        return (firstName + " " + lastName);
    }

    @Override
    public void calculateTuition() {
        if (getStateOfResidence().equals(getState())) {
            tuition = CollegeConstants.INDIANA_UNIVERSITY_IN_STATE_TUITION;
        } else {
            tuition = CollegeConstants.INDIANA_UNIVERSITY_OUT_OF_STATE_TUITION;
        }
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public String getHousing() {
        return housing;
    }

    @Override
    public String getState() {
        return CollegeConstants.INDIANA_UNIVERSITY_STATE;
    }

    @Override
    public String generateID() {
        String result = "8";
        double sevenDigits = (int) (Math.random() * 1000000);
        result += sevenDigits;
        result += "21";
        for (String i : idNums) {
            if (i.equals(result)) {
                generateID();
            }
        }
        id = result;
        return result;
    }

    @Override
    public String getID() {
        return id;
    }
}
