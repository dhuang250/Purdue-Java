import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;


/**
 * An set of local test cases for CollegeStudent
 *
 * @author Purdue CS
 * @version 03/30/2020
 */
public class CollegeStudentLocalTest {

    private final InputStream originalInput = System.in;

    private final PrintStream originalOutput = System.out;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayInputStream testIn;

    private ByteArrayOutputStream testOut;

    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    
    @Test(timeout = 1000)
    public void testIfTuitionFieldExists() {
        Field tuitionField;

        try {
            tuitionField = CollegeStudent.class.getDeclaredField("tuition");
            if (!tuitionField.getType().equals(double.class)) {
                fail("Ensure that the tuition field in CollegeStudent is of type double!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field tuition in CollegeStudent that is protected and is of type double!");
            return;
        }

        assertEquals("Ensure that your field tuition is of scope protected!", 
                     Modifier.PROTECTED, tuitionField.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfStudentAgeFieldExists() {
        Field ageField;

        try {
            ageField = CollegeStudent.class.getDeclaredField("age");
            if (!ageField.getType().equals(int.class)) {
                fail("Ensure that the age field in CollegeStudent is of type int!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field age in CollegeStudent that is protected and is of type int!");
            return;
        }

        assertEquals("Ensure that your field age is of scope protected!", Modifier.PROTECTED,
                ageField.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfFirstNameFieldExists() {
        Field firstNameField;

        try {
            firstNameField = CollegeStudent.class.getDeclaredField("firstName");
            if (!firstNameField.getType().equals(String.class)) {
                fail("Ensure that the firstName field in CollegeStudent is of type String!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field firstName in CollegeStudent that is protected and is of type String!");
            return;
        }

        assertEquals("Ensure that your field firstName is of scope protected!",
                     Modifier.PROTECTED, firstNameField.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfLastNameFieldExists() {
        Field lastNameField;

        try {
            lastNameField = CollegeStudent.class.getDeclaredField("lastName");
            if (!lastNameField.getType().equals(String.class)) {
                fail("Ensure that the lastName field in CollegeStudent is of type String!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field lastName in CollegeStudent that is protected and is of type String!");
            return;
        }

        assertEquals("Ensure that your field lastName is of scope protected!",
                     Modifier.PROTECTED, lastNameField.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfStateOfResidencyFieldExists() {
        Field stateField;

        try {
            stateField = CollegeStudent.class.getDeclaredField("stateOfResidency");
            if (!stateField.getType().equals(String.class)) {
                fail("Ensure that the stateOfResidency field in CollegeStudent is of type String!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field stateOfResidency" +
                    " in CollegeStudent that is protected and is of type String!");
            return;
        }

        assertEquals("Ensure that your field stateOfResidency is of scope protected!", 
                     Modifier.PROTECTED, stateField.getModifiers());
    }
    
    @Test(timeout = 1000)
    public void testIfMajorFieldExists() {
        Field majorField;

        try {
            majorField = CollegeStudent.class.getDeclaredField("major");
            if (!majorField.getType().equals(String.class)) {
                fail("Ensure that the field major in CollegeStudent is of type String!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field major in CollegeStudent that is protected and is of type String!");
            return;
        }

        assertEquals("Ensure that you have a field major in CollegeStudent that is protected!",
                Modifier.PROTECTED, majorField.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfHousingFieldExists() {
        Field housingField;

        try {
            housingField = CollegeStudent.class.getDeclaredField("housing");
            if (!housingField.getType().equals(String.class)) {
                fail("Ensure that the housing field in CollegeStudent is of type String!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field housing in CollegeStudent that is protected and is of type String!");
            return;
        }

        assertEquals("Ensure that your field housing is of scope protected!", 
                     Modifier.PROTECTED, housingField.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfIDFieldExists() {
        Field idField;

        try {
            idField = CollegeStudent.class.getDeclaredField("id");
            if (!idField.getType().equals(String.class)) {
                fail("Ensure that the field id in CollegeStudent is of type String!");
            }
        } catch (NoSuchFieldException e) {
            fail("Ensure that you have a field id in CollegeStudent that is protected and is of type String!");
            return;
        }

        assertEquals("Ensure that you have a field id in CollegeStudent that is protected!",
                Modifier.PROTECTED, idField.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetTuitionMethodExists() {
        Method getTuitionMethod;

        try {
            getTuitionMethod = CollegeStudent.class.getDeclaredMethod("getTuition");
            if (!getTuitionMethod.getReturnType().equals(double.class)) {
                fail("Ensure that the getTuition method in CollegeStudent returns a double!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getTuition in CollegeStudent that is public, abstract and " +
                    "returns" +
                    " a double!");
            return;
        }

        assertEquals("Ensure that your method getTuition in CollegeStudent is public and abstract!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getTuitionMethod.getModifiers());

    }

    @Test(timeout = 1000)
    public void testIfGetStudentAgeMethodExists() {
        Method getStudentAge;

        try {
            getStudentAge = CollegeStudent.class.getDeclaredMethod("getStudentAge");
            if (!getStudentAge.getReturnType().equals(int.class)) {
                fail("Ensure that the getStudentAge method in CollegeStudent returns an int!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getStudentAge in CollegeStudent that is public, abstract and" +
                    " returns an int!");
            return;
        }

        assertEquals("Ensure that your method getStudentAge in CollegeStudent is public and abstract!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getStudentAge.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetStudentFirstNameMethodExists() {
        Method getStudentFirstNameMethod;

        try {
            getStudentFirstNameMethod = CollegeStudent.class.getDeclaredMethod("getStudentFirstName");
            if (!getStudentFirstNameMethod.getReturnType().equals(String.class)) {
                fail("Ensure that the getStudentFirstName method in CollegeStudent returns a String!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getStudentFirstName method in CollegeStudent that is " +
                    "public, abstract" +
                    " and returns a String!");
            return;
        }

        assertEquals("Ensure that your method getStudentFirstName in CollegeStudent is public and abstract!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getStudentFirstNameMethod.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetStudentLastNameMethodExists() {
        Method getStudentLastNameMethod;

        try {
            getStudentLastNameMethod = CollegeStudent.class.getDeclaredMethod("getStudentLastName");
            if (!getStudentLastNameMethod.getReturnType().equals(String.class)) {
                fail("Ensure that the getStudentLastName method in CollegeStudent returns a String!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getStudentLastName in CollegeStudent that is abstract " +
                    "and returns a String!");
            return;
        }

        assertEquals("Ensure that your method getStudentLastName in CollegeStudent is public, abstract!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getStudentLastNameMethod.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetStateOfResidenceMethodExists() {
        Method getStateOfResidenceMethod;

        try {
            getStateOfResidenceMethod = CollegeStudent.class.getDeclaredMethod("getStateOfResidence");
            if (!getStateOfResidenceMethod.getReturnType().equals(String.class)) {
                fail("Ensure that the getStateOfResidence method in CollegeStudent returns a String!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getStateOfResidence in CollegeStudent that is public, abstract" +
                    " " +
                    "and returns a String!");
            return;
        }

        assertEquals("Ensure that your method getStateOfResidency in CollegeStudent is public and abstract!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getStateOfResidenceMethod.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetFullNameMethodExists() {
        Method getFullNameMethod;

        try {
            getFullNameMethod = CollegeStudent.class.getDeclaredMethod("getFullName");
            if (!getFullNameMethod.getReturnType().equals(String.class)) {
                fail("Ensure that the getFullName method in CollegeStudent returns a String!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getFullName in CollegeStudent that is public, abstract and" +
                    "returns a String!");
            return;
        }

        assertEquals("Ensure that your method getFullName in CollegeStudent is abstract and public!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getFullNameMethod.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfCalculateTuitionMethodExists() {
        Method calculateTuitionMethod;

        try {
            calculateTuitionMethod = CollegeStudent.class.getDeclaredMethod("calculateTuition");
            if (!calculateTuitionMethod.getReturnType().equals(void.class)) {
                fail("Ensure that the calculateTuition method in CollegeStudent returns nothing (void)!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called calculateTuition in CollegeStudent that is public, abstract " +
                    "and" +
                    "returns nothing!");
            return;
        }

        assertEquals("Ensure that your method calculateTuition in CollegeStudent " +
                        "is abstract and public!", Modifier.ABSTRACT + Modifier.PUBLIC,
                calculateTuitionMethod.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetMajorMethodExists() {
        Method getMajorMethod;

        try {
            getMajorMethod = CollegeStudent.class.getDeclaredMethod("getMajor");
            if (!getMajorMethod.getReturnType().equals(String.class)) {
                fail("Ensure that the getMajor method in CollegeStudent returns a String!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getMajor in CollegeStudent that is public, " +
                    "abstract and " +
                    "returns" +
                    " a String!");
            return;
        }

        assertEquals("Ensure that you have a method called getMajor in CollegeStudent that is abstract and" +
                        "public!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getMajorMethod.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetHousingMethodExists() {
        Method getHousingMethod;

        try {
            getHousingMethod = CollegeStudent.class.getDeclaredMethod("getHousing");
            if (!getHousingMethod.getReturnType().equals(String.class)) {
                fail("Ensure that the getHousing method in CollegeStudent returns a String!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getHousing in CollegeStudent that is public, abstract and " +
                    "returns a String!");
            return;
        }

        assertEquals("Ensure that you have a method called getHousing in CollegeStudent " +
                        "that is abstract and public!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getHousingMethod.getModifiers());
    }

    @Test(timeout = 1000)
    public void testIfGetIDMethodExists() {
        Method getIDMethod;

        try {
            getIDMethod = CollegeStudent.class.getDeclaredMethod("getID");
            if (!getIDMethod.getReturnType().equals(String.class)) {
                fail("Ensure that the getID method in CollegeStudent returns a String!");
            }
        } catch (NoSuchMethodException e) {
            fail("Ensure that you have a method called getID in CollegeStudent that is public, abstract and " +
                    "returns a String!");
            return;
        }

        assertEquals("Ensure that you have a method called getID in CollegeStudent " +
                        "that is abstract and public!",
                Modifier.ABSTRACT + Modifier.PUBLIC, getIDMethod.getModifiers());
    }


    /**
     * UTILITY METHODS BELOW
     */
    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    @SuppressWarnings("unused")
    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreInputAndOutput() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }
}
