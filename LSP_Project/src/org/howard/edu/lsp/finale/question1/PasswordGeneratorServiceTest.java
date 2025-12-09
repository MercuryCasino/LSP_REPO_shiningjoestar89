package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

public class PasswordGeneratorServiceTest {
    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() throws Exception {
        service = PasswordGeneratorService.getInstance();
        
        // RESETTING SINGLETON STATE FOR TESTING PURPOSES
        // Because the service is a Singleton, the strategy field persists between tests.
        // We use reflection to set 'strategy' to null before each test to ensure isolation.
        Field strategyField = PasswordGeneratorService.class.getDeclaredField("strategy");
        strategyField.setAccessible(true);
        strategyField.set(service, null);
    }

    @Test
    public void checkInstanceNotNull() {
        // verify that 'service' is not null
        assertNotNull(service, "Service instance should not be null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // Verify that both 'service' (created in @BeforeEach)
        // and 'second' refer to the EXACT same object in memory.
        assertSame(service, second, "Both variables must refer to the same Singleton instance");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // verify correct exception behavior
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        });
        
        String expectedMessage = "No algorithm selected";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        
        // verify required behavior
        assertEquals(10, p.length(), "Password length should be 10");
        assertTrue(p.matches("[0-9]+"), "Basic password should contain digits only");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        
        // verify required behavior
        assertEquals(12, p.length(), "Password length should be 12");
        assertTrue(p.matches("[A-Za-z0-9]+"), "Enhanced password should contain alphanumeric chars");
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        
        // verify required behavior
        assertEquals(8, p.length(), "Password length should be 8");
        assertTrue(p.matches("[A-Za-z]+"), "Letters password should contain letters only");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        // 1. Basic
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertTrue(p1.matches("[0-9]+"), "First switch should be numeric");

        // 2. Letters
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertTrue(p2.matches("[A-Za-z]+"), "Second switch should be letters only");

        // 3. Enhanced
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Third switch should be alphanumeric");
    }
}