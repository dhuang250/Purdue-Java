import java.io.*;
import java.util.Scanner;

/**
 * The main class of the program, that links together all of the other classes in this project.
 *
 * @author Darius Tse
 * @version today's date!
 */
public class StudentOperator {

    private static String firstName;
    private static String lastName;
    private static int age;
    private static String ageInput;
    private static String major;
    private static String collegeName;
    private static String state;
    private static CollegeStudent student;
    private static String housing;
    private static CollegeStudent lookedupStudent;

    public static void main(String[] args) throws InvalidStateException, InvalidCollegeException,
            InvalidStudentException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the College Student Database!");
        while (true) {
            int initialChoice;
            do {
                System.out.println("What would you like to do today?");
                System.out.println("(1) Add my information to the database");
                System.out.println("(2) Lookup Student Information");
                System.out.println("(3) Exit");
                initialChoice = scanner.nextInt();
                scanner.nextLine();
            } while (initialChoice > 3 | initialChoice <= 0);

            switch (initialChoice) {
                case 1: {
                    System.out.println("What college are you attending?");
                    collegeName = scanner.nextLine();

                    if (!verifyCollege(collegeName)) {
                        throw new InvalidCollegeException("The college you entered is not one supported by this " +
                                "logger!");
                    }

                    System.out.println("What is your first name?");
                    firstName = scanner.nextLine();

                    if (!verifyName(firstName)) {
                        throw new InvalidStudentException("The student's first name can only contain characters!");
                    }

                    System.out.println("What is your last name?");
                    lastName = scanner.nextLine();

                    if (!verifyName(lastName)) {
                        throw new InvalidStudentException("The student's last name can only contain characters!");
                    }

                    System.out.println("How old are you?");
                    ageInput = scanner.nextLine();

                    if (!verifyAge(ageInput)) {
                        throw new InvalidStudentException("The student's age must be a number between 16 and 22!");
                    } else {
                        age = Integer.parseInt(ageInput);
                    }

                    System.out.println("What is your major?");
                    major = scanner.nextLine();

                    if (!verifyMajor(major)) {
                        throw new InvalidStudentException("The student's major must not be blank and only contain " +
                                "letters with the exception of a space or the '/' character!");
                    }

                    System.out.println("What state do you currently reside in?");
                    state = scanner.nextLine();

                    if (!verifyState(state)) {
                        throw new InvalidStateException("The state you entered was not a state in the " +
                                "United States!");
                    }

                    System.out.println("Do you plan to live on or off campus?");
                    housing = scanner.nextLine();

                    if (!verifyHousing(housing)) {
                        throw new InvalidStudentException("The student must be living either on campus or off campus!");
                    }
                    switch (collegeName) {
                        case CollegeConstants.PURDUE_UNIVERSITY_NAME:
                            student = new PurdueStudent(age, firstName, lastName, state, major,
                                    housing);
                            break;
                        case CollegeConstants.INDIANA_UNIVERSITY_NAME:
                            student = new IndianaStudent(age, firstName, lastName, state, major,
                                    housing);
                            break;
                        case CollegeConstants.OHIO_STATE_UNIVERSITY_NAME:
                            student = new OhioStateStudent(age, firstName, lastName, state, major,
                                    housing);
                            break;
                        case CollegeConstants.PENN_STATE_UNIVERSITY_NAME:
                            student = new PennStateStudent(age, firstName, lastName, state, major,
                                    housing);
                            break;
                        case CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME:
                            student = new MichiganStudent(age, firstName, lastName, state, major,
                                    housing);
                            break;
                    }
                    student.calculateTuition();
                    student.generateID();
                    try {
                        writeStudentToFile(student);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Here is your new student ID : " + student.getID());
                    System.out.println("Thank you for the information! Your information has been documented. "
                            + "Good luck in college!");
                    break;
                }
                case 2: {
                    String nameOfCollege;
                    System.out.println("Please enter the college of the student you would like to lookup.");
                    nameOfCollege = scanner.nextLine();
                    if (!verifyCollege(nameOfCollege)) {
                        throw new InvalidCollegeException("Not a college supported by this logger!");
                    }
                    System.out.println("Enter the ID of the student you would like to know information about.");
                    String id = scanner.nextLine();
                    CollegeStudent collegeStudent;
                    switch (nameOfCollege) {
                        case CollegeConstants.PURDUE_UNIVERSITY_NAME:
                            collegeStudent = new PurdueStudent(id);
                            break;
                        case CollegeConstants.INDIANA_UNIVERSITY_NAME:
                            collegeStudent = new IndianaStudent(id);
                            break;
                        case CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME:
                            collegeStudent = new MichiganStudent(id);
                            break;
                        case CollegeConstants.OHIO_STATE_UNIVERSITY_NAME:
                            collegeStudent = new OhioStateStudent(id);
                            break;
                        case CollegeConstants.PENN_STATE_UNIVERSITY_NAME:
                            collegeStudent = new PennStateStudent(id);
                            break;
                        default: {
                            throw new InvalidStudentException("That student doesn't exist in the database!");
                        }
                    }
                    if (lookupID(collegeStudent)) {
                        String input;
                        do {
                            System.out.println("What information would you like to know about the student?");
                            System.out.println("(1) Name");
                            System.out.println("(2) Age");
                            System.out.println("(3) Housing");
                            System.out.println("(4) Major");
                            System.out.println("(5) Student Origin");
                            input = scanner.nextLine();
                        } while (Integer.parseInt(input) > 5 | Integer.parseInt(input) < 1);

                        collegeStudent = lookedupStudent;
                        if (Integer.parseInt(input) == 1) {
                            System.out.println("The student's name is " + collegeStudent.getFullName());
                        } else if (Integer.parseInt(input) == 2) {
                            System.out.println("The student's age is " + collegeStudent.getStudentAge());
                        } else if (Integer.parseInt(input) == 3) {
                            System.out.println("The student's housing is considered " + collegeStudent.getHousing());
                        } else if (Integer.parseInt(input) == 4) {
                            System.out.println("The student's major is " + collegeStudent.getMajor());
                        } else if (Integer.parseInt(input) == 5) {
                            System.out.println("The student's home is in " + collegeStudent.getStateOfResidence());
                        }
                    } else {
                        System.out.println("That student doesn't exist in the database. " +
                                "Thank you for using the Lookup Tool!");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Thank you for using the College Database Program!");
                    return;
                }
            }
        }
    }

    /**
     * The {@code verifyState()} method takes in a state and makes sure it is present in the enum {@code States}. If
     * the state is not present, then the method returns false. If it is, then the method returns true. If the name
     * of the state is two words, like New York, then this method should also convert it to where the space is now
     * an underscore character.If this method returns false, the method that called it should throw a {@code
     * InvalidStateException}.
     *
     * @param str The state to be checked
     * @return The result of whether the state is in the United States
     */
    private static boolean verifyState(String str) {
        // Implement the method with the spec given above.
        for (States i : States.values()) {
            if (i.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The {@code verifyAge()} method verifies that the age is between the specified bounds of being 16 years or older
     * and being 22 years or younger. Since the main method will read any input, you should also ensure that the
     * input is an integer.If this method returns false, the method that called it should throw a {@code
     * InvalidStudentException}.
     *
     * @param ageToVerify The age to verify
     * @return The result of whether the age falls between the specified bounds
     */
    private static boolean verifyAge(String ageToVerify) {
        // Implement the method with the spec given above.
        return  (Integer.parseInt(ageToVerify) >= 16 && Integer.parseInt(ageToVerify) <= 22);
            //return true;

        //return false;
    }

    /**
     * The {@code verifyMajor()} method verifies whether the major is not a blank line. If the major is a blank line,
     * the method returns false. If the major is anything except a blank line, the method returns true. In addition,
     * the method should only allow letters. The only three characters allowed besides letters are a space, a '/'
     * character, and a '-' character. You should remove these in this method, and replace them with nothing. If this
     * method returns false, the method that called it should throw a {@code InvalidStudentException}.
     *
     * @param majorToVerify The major to verify
     * @return The result of whether the major is a valid major.
     */
    private static boolean verifyMajor(String majorToVerify) {
        // Implement the method with the spec given above.

        if (!majorToVerify.equals("")) {
            if (majorToVerify.contains("/")) {
                majorToVerify.replace("/", "");
            }
            if (majorToVerify.contains("-")) {
                majorToVerify.replace("-", "");
            }
            if (majorToVerify.contains(" ")) {
                majorToVerify.replace(" ", "");
            }
            if (majorToVerify.matches("[a-zA-Z]+")) {
                return true;
            }
        }

        return false;
    }

    /**
     * The {@code verifyHousing()} method verifies that the housing statement matches either the on campus or
     * off campus housing constants in the {@code CollegeConstants} class.If this method returns false, the method
     * that called it should throw a {@code InvalidStudentException}.
     *
     * @param housingToVerify The housing status to verify
     * @return The result of whether the housing is valid.
     */
    private static boolean verifyHousing(String housingToVerify) {
        // Implement the method with the spec given above.

        return  (housingToVerify.equalsIgnoreCase(CollegeConstants.ON_CAMPUS) ||
                housingToVerify.equalsIgnoreCase(CollegeConstants.OFF_CAMPUS));
            //return true;

        //return false;
    }

    /**
     * The {@code verifyCollege()} method verifies that the college is one of the give colleges supported by
     * the CollegeLogger project. If it is not one of the five colleges, the method returns false. If it is,
     * the method returns true. If this method returns false, the method that called it should throw a {@code
     * InvalidCollegeException}.
     *
     * @param str The college to check support for
     * @return The result of whether or not this college is supported by this program.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean verifyCollege(String str) {
        // Implement the method with the spec given above.
        if (
                str.equalsIgnoreCase(CollegeConstants.PURDUE_UNIVERSITY_NAME) ||
                str.equalsIgnoreCase(CollegeConstants.PENN_STATE_UNIVERSITY_NAME) ||
                str.equalsIgnoreCase(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME) ||
                str.equalsIgnoreCase(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME) ||
                str.equalsIgnoreCase(CollegeConstants.INDIANA_UNIVERSITY_NAME)
        ) {
            return true;
        }

        return false;
    }

    /**
     * The {@code verifyName()} method will check whether the name is composed purely of letters. A name containing
     * anything other than letters is considered an invalid name. Similarly, an empty string is also considered an
     * invalid name.If this method returns false, the method that called it should throw a {@code
     * InvalidStudentException}.
     *
     * @param str The name to check validity of
     * @return Whether the name is a valid name for the program to use.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean verifyName(String str) {
        // Implement the method with the spec given above.
        if (!str.equals("")) {
            if (str.matches("[a-zA-Z]+")) {
                return true;
            }
        }
        return false;
    }

    /**
     * The {@code writeStudentToFile()} method takes the information provided by the user to the program and
     * pretty prints it to the appropriate text file. If the file is not empty, meaning there are
     * already one or more entries, a row of 20 dashes separated by a single space should be printed to separate
     * the entries. This information should be put to the appropriate file based on the student's college name. Refer
     * to the handout in order to understand the order of the data to be printed. In addition, keep in mind that the
     * tuition should be printed without a dollars sign but have two decimal places.
     * <p>
     * The files will be named as follows:
     * <p>
     * For Purdue University, data is stored in a file called purdueUniversityStudents.txt
     * For Indiana University, data is stored in a file called indianaUniversityStudents.txt
     * For University of Michigan, data is stored in a file called universityOfMichiganStudents.txt
     * For Pennsylvania State University, data is stored in a file called pennsylvaniaStateUniversityStudents.txt
     * For The Ohio State University, data is stored in a file called theOhioStateUniversityStudents.txt
     *
     * @param studentToWrite The {@code CollegeStudent} to write data about.
     */
    private static void writeStudentToFile(CollegeStudent studentToWrite) throws FileNotFoundException {
        // Implement the method with the spec given above.
        File f;
        FileOutputStream fos;
        PrintWriter pw;
        if (collegeName.contains("Purdue")) {
            f = new File("purdueUniversityStudents.txt");
        } else if (collegeName.contains("Indiana")) {
            f = new File("indianaUniversityStudents.txt");
        } else if (collegeName.contains("Michigan")) {
            f = new File("universityOfMichiganStudents.txt");
        } else if (collegeName.contains("Pennsylvania")) {
            f = new File("pennsylvaniaStateUniversityStudents.txt");
        } else {
            f = new File("theOhioStateUniversityStudents.txt");
        }
        try {
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
            } else {
                fos = new FileOutputStream(f);
            }
            pw = new PrintWriter(fos);
            pw.println(studentToWrite.id);
            pw.println((studentToWrite.lastName).toUpperCase() + ", " + (studentToWrite.firstName).toUpperCase());
            pw.println(studentToWrite.age);
            pw.println(studentToWrite.housing);
            pw.println(studentToWrite.major);
            pw.println(studentToWrite.stateOfResidency);
            pw.println(String.format("$%.2f", studentToWrite.tuition));
            pw.println("--------------------");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The {@code lookupID} method will take a parameter of a {@code CollegeStudent} object, and return whether
     * the ID of the student was found. If it was found, it should also set the appropriate fields of an object
     * of type {@code CollegeStudent} to the values found of the student.
     *
     * @param studentToLookup The {@code CollegeStudent} to check existence in the database.
     * @return a boolean indicator that represents if the student was found in the respective database or not.
     */
    private static boolean lookupID(CollegeStudent studentToLookup) {
        // Implement the method with the spec given above.
        File f;
        if (studentToLookup instanceof PurdueStudent) {
            f = new File("purdueUniversityStudents.txt");
        } else if (studentToLookup instanceof IndianaStudent) {
            f = new File("indianaUniversityStudents.txt");
        } else if (studentToLookup instanceof MichiganStudent) {
            f = new File("universityOfMichiganStudents.txt");
        } else if (studentToLookup instanceof PennStateStudent) {
            f = new File("pennsylvaniaStateUniversityStudents.txt");
        } else {
            f = new File("theOhioStateUniversityStudents.txt");
        }
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            while (true) {
                String line = bfr.readLine();
                if (line == null) {
                    break;
                }
                String x = String.valueOf(studentToLookup);
                if (x.equals(line)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }







    /**
     * The {@code toNameCase} method should take a {@code String} parameter, and convert it to "name case". Name
     * Case is represented by the String having a capitalized first letter, and the rest of the {@code String} being
     * lowercase. For example:
     * <p>
     * Given a String - purdue
     * This method should convert it to say - Purdue
     * <p>
     * Given a String - dunsmore
     * This method should convert it to say - Dunsmore
     *
     * @param str the wrongly formatted {@code String} that must be converted to name case
     * @return the correctly formatted String, following name case standards.
     */
    private static String toNameCase(String str) {
        // Implement the method with the spec given above.
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
