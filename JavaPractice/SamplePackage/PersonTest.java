package SamplePackage;

import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    @Test
    public void testPerson() {
        Person p = new Person("Joe", "Author", 42, 173, 82, "Brown", "MALE");
        Logger logger = Logger.getLogger(Person.class.getName());
        logger.info("Name: " + p.getName());
        logger.info("Age: " + p.getAge());
        logger.info("Height (cm): " + p.getHeight());
        logger.info("Weight (kg): " + p.getWeight());
        logger.info("Eye Color:  " + p.getEyeColor());
        logger.info("Gender:  " + p.getGender());
        assertEquals("Joe Author", p.getName());
        assertEquals(42, p.getAge());
        assertEquals(173, p.getHeight());
        assertEquals(82, p.getWeight());
        assertEquals("Brown", p.getEyeColor());
        assertEquals("MALE", p.getGender());
    }
}
