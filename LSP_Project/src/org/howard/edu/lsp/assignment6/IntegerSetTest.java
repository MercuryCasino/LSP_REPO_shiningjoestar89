package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for the IntegerSet class.
 * Verifies the correctness of all public methods.
 * * @author Dshawan Dunwell
 */
public class IntegerSetTest {

    private IntegerSet setA;
    private IntegerSet setB;
    private IntegerSet emptySet;

    /**
     * Sets up common IntegerSet objects before each test.
     * setA = [1, 2, 3]
     * setB = [3, 4, 5]
     * emptySet = []
     */
    @BeforeEach
    void setUp() {
        setA = new IntegerSet();
        setA.add(1);
        setA.add(2);
        setA.add(3);

        setB = new IntegerSet();
        setB.add(3);
        setB.add(4);
        setB.add(5);

        emptySet = new IntegerSet();
    }

    @Test
    @DisplayName("Test clear()")
    public void testClear() {
        setA.clear();
        assertTrue(setA.isEmpty());
        assertEquals(0, setA.length());
    }

    @Test
    @DisplayName("Test length()")
    public void testLength() {
        assertEquals(3, setA.length());
        assertEquals(3, setB.length());
        assertEquals(0, emptySet.length());
    }

    @Test
    @DisplayName("Test equals(Object o)")
    public void testEquals() {
        IntegerSet setA_copy = new IntegerSet();
        setA_copy.add(3); // Add in different order
        setA_copy.add(1);
        setA_copy.add(2);
        
        IntegerSet setC = new IntegerSet();
        setC.add(1);
        setC.add(2); // Different size

        assertTrue(setA.equals(setA_copy), "Sets with same elements in diff order should be equal");
        assertTrue(setA.equals(setA), "Set should be equal to itself");
        assertFalse(setA.equals(setB), "Sets with different elements should not be equal");
        assertFalse(setA.equals(emptySet), "Set should not be equal to empty set");
        assertFalse(setA.equals(setC), "Sets with different lengths should not be equal");
        assertFalse(setA.equals(new String("Not a set")), "Set should not be equal to different object type");
        assertFalse(setA.equals(null), "Set should not be equal to null");
    }

    @Test
    @DisplayName("Test contains(int value)")
    public void testContains() {
        assertTrue(setA.contains(1));
        assertTrue(setA.contains(3));
        assertFalse(setA.contains(99));
        assertFalse(emptySet.contains(1));
    }

    @Test
    @DisplayName("Test largest()")
    public void testLargest() {
        assertEquals(3, setA.largest());
        assertEquals(5, setB.largest());
    }

    @Test
    @DisplayName("Test largest() throws IllegalStateException on empty set")
    public void testLargestThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.largest();
        });
        assertEquals("The set is empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Test smallest()")
    public void testSmallest() {
        assertEquals(1, setA.smallest());
        assertEquals(3, setB.smallest());
    }

    @Test
    @DisplayName("Test smallest() throws IllegalStateException on empty set")
    public void testSmallestThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.smallest();
        });
        assertEquals("The set is empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Test add(int item)")
    public void testAdd() {
        setA.add(4);
        assertTrue(setA.contains(4));
        assertEquals(4, setA.length());
        
        // Test adding duplicate
        setA.add(1);
        assertEquals(4, setA.length(), "Adding a duplicate item should not change length");
    }

    @Test
    @DisplayName("Test remove(int item)")
    public void testRemove() {
        setA.remove(2);
        assertFalse(setA.contains(2));
        assertEquals(2, setA.length());
        
        // Test removing non-existent item
        setA.remove(99);
        assertEquals(2, setA.length(), "Removing non-existent item should not change length");
    }

    @Test
    @DisplayName("Test union(IntegerSet other)")
    public void testUnion() {
        setA.union(setB); // setA = [1, 2, 3] U [3, 4, 5] -> [1, 2, 3, 4, 5]
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        
        assertTrue(setA.equals(expected));
        assertEquals(5, setA.length());
        
        // Test union with empty set (should not change)
        setB.union(emptySet);
        assertEquals(3, setB.length());
        assertTrue(setB.contains(3) && setB.contains(4) && setB.contains(5));
    }

    @Test
    @DisplayName("Test intersect(IntegerSet other)")
    public void testIntersect() {
        setA.intersect(setB); // setA = [1, 2, 3] ∩ [3, 4, 5] -> [3]
        IntegerSet expected = new IntegerSet();
        expected.add(3);
        
        assertTrue(setA.equals(expected));
        assertEquals(1, setA.length());
        
        // Test intersection with no common elements
        setA.clear();
        setA.add(1);
        setB.clear();
        setB.add(9);
        setA.intersect(setB);
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("Test diff(IntegerSet other) (this \\ other)")
    public void testDiff() {
        setA.diff(setB); // setA = [1, 2, 3] \ [3, 4, 5] -> [1, 2]
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        expected.add(2);
        
        assertTrue(setA.equals(expected));
        assertEquals(2, setA.length());
        
        // Test diff with self (should become empty)
        setB.diff(setB);
        assertTrue(setB.isEmpty());
    }
    
    @Test
    @DisplayName("Test complement(IntegerSet other) (other \\ this)")
    public void testComplement() {
        // setA becomes (setB \ setA)
        setA.complement(setB); // setA = [3, 4, 5] \ [1, 2, 3] -> [4, 5]
        IntegerSet expected = new IntegerSet();
        expected.add(4);
        expected.add(5);
        
        assertTrue(setA.equals(expected));
        assertEquals(2, setA.length());
        
        // Test complement with self (should become empty)
        setB.complement(setB);
        assertTrue(setB.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty()")
    public void testIsEmpty() {
        assertFalse(setA.isEmpty());
        assertTrue(emptySet.isEmpty());
        setA.clear();
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("Test toString()")
    public void testToString() {
        assertEquals("[1, 2, 3]", setA.toString());
        assertEquals("[]", emptySet.toString());
        
        setA.remove(2);
        assertEquals("[1, 3]", setA.toString()); // Order is preserved by ArrayList
    }
}