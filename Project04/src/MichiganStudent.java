import java.util.ArrayList;
/**
 * The main class of the program, that links together all of the other classes in this project.
 *
 * @author Darius Tse
 * @version today's date!
 */
public class MichiganStudent  extends CollegeStudent {
    private static ArrayList<String> idNums = new ArrayList<>();

    public MichiganStudent(int age, String firstName, String lastName,
                          String stateOfResidency, String major, String housing) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stateOfResidency = stateOfResidency;
        this.major = major;
        this.housing = housing;
    }

    public MichiganStudent(String id) {
        this.id = id;
    }

    public MichiganStudent() {
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
            tuition = CollegeConstants.UNIVERSITY_OF_MICHIGAN_IN_STATE_TUITION;
        } else {
            tuition = CollegeConstants.UNIVERSITY_OF_MICHIGAN_OUT_OF_STATE_TUITION;
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
        return CollegeConstants.UNIVERSITY_OF_MICHIGAN_STATE;
    }

    @Override
    public String generateID() {
        String result = "";
        for (int i = 0; i < 5; i++) {
            result += (int) (Math.random() * 10);
            result += "3";
        }
        id = result;
        return result;
    }

    @Override
    public String getID() {
        return id;
    }
}
