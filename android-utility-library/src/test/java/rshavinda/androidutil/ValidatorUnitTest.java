package rshavinda.androidutil;

import org.junit.Test;

import static org.junit.Assert.*;


public class ValidatorUnitTest {

    @Test
    public void isEmptyStringReturnsTrue() {
        String string = new String();
        String stringNull = null;
        assertTrue(Validator.isEmptyString("    "));
        assertTrue(Validator.isEmptyString(string));
        assertTrue(Validator.isEmptyString(stringNull));
    }

    @Test
    public void isEmptyStringReturnsFalse() {
        String string = "Test";
        assertFalse(Validator.isEmptyString("  1  "));
        assertFalse(Validator.isEmptyString(string));
    }

    @Test
    public void getStringValueTest() {
        String string = new String();
        String stringNull = null;
        assertEquals("", Validator.getStringValue(string));
        assertEquals("", Validator.getStringValue(stringNull));
        assertEquals("Test", Validator.getStringValue("Test"));
        assertEquals("Test", Validator.getStringValue("  Test    "));
    }

    @Test
    public void isValidEmailReturnsTrue() {
        assertTrue(Validator.isValidEmail("name@email.co.uk"));
        assertTrue(Validator.isValidEmail("name_123@email.co"));
        assertTrue(Validator.isValidEmail("name_.name@email.co"));
    }

    @Test
    public void isValidEmailReturnsFalse() {
        assertFalse(Validator.isValidEmail("name$%@email.co.uk"));
        assertFalse(Validator.isValidEmail("name@email"));
        assertFalse(Validator.isValidEmail("name_123@email.co..com"));
    }
}
