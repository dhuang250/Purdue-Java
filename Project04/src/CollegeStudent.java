/**
 * The main class of the program, that links together all of the other classes in this project.
 *
 * @author Darius Tse
 * @version today's date!
 */

public abstract class CollegeStudent {
    protected double tuition;
    protected int age;
    protected String firstName;
    protected String lastName;
    protected String stateOfResidency;
    protected String major;
    protected String housing;
    protected String id;



    public abstract double getTuition();
    public abstract int getStudentAge();
    public abstract String getStudentFirstName();
    public abstract String getStudentLastName();
    public abstract String getStateOfResidence();
    public abstract String getFullName();
    public abstract void calculateTuition();
    public abstract String getMajor();
    public abstract String getHousing();
    public abstract String getState();
    public abstract String generateID();
    public abstract String getID();

}
