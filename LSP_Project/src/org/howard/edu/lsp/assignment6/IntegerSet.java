package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements a mathematical set of integers using an internal ArrayList.
 * The set does not allow duplicate elements.
 * * @author Dshawan Dunwell
 */
public class IntegerSet {
    // Use an ArrayList from the Java Collections Framework (JCF)
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the internal representation of the set, removing all elements.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * * @return the integer length (size) of the set.
     */
    public int length() {
        return set.size();
    }

    /**
     * Compares this IntegerSet with another object for equality.
     * Two sets are equal if they contain exactly the same elements,
     * regardless of order.
     * * @param o The object to be compared for equality with this set.
     * @return true if the specified object is equal to this set, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        // 1. Check for self-comparison
        if (this == o) {
            return true;
        }
        
        // 2. Check for null or different class type
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        // 3. Cast the object to IntegerSet
        IntegerSet otherSet = (IntegerSet) o;
        
        // 4. Check if sizes are different (cannot be equal)
        if (this.length() != otherSet.length()) {
            return false;
        }
        
        // 5. Check for content equality (order-independent)
        // Since sizes are equal, we only need to check if 'this'
        // contains all elements of 'otherSet'.
        return this.set.containsAll(otherSet.set);
    }

    /**
     * Checks if the set contains a specific integer value.
     * * @param value The integer value to check for presence.
     * @return true if the set contains the value, false otherwise.
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * * @return the maximum integer value in the set.
     * @throws IllegalStateException if the set is empty.
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("The set is empty.");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest item in the set.
     * * @return the minimum integer value in the set.
     * @throws IllegalStateException if the set is empty.
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("The set is empty.");
        }
        return Collections.min(set);
    }

    /**
     * Adds an integer item to the set.
     * If the item is already present, the set remains unchanged.
     * * @param item The integer to add to the set.
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an integer item from the set.
     * If the item is not present, the set remains unchanged.
     * * @param item The integer to remove from the set.
     */
    public void remove(int item) {
        // Must use Integer.valueOf() to force remove(Object)
        // instead of remove(int index)
        set.remove(Integer.valueOf(item));
    }

    /**
     * Modifies this set to be the union of itself and another set.
     * The resulting set contains all unique elements from both sets.
     * * @param other The other IntegerSet to union with.
     */
    public void union(IntegerSet other) {
        // Iterate through the other set and add each element.
        // Our 'add' method automatically handles duplicate checks.
        for (int item : other.set) {
            this.add(item);
        }
    }

    /**
     * Modifies this set to be the intersection of itself and another set.
     * The resulting set contains only elements that are present in both sets.
     * * @param other The other IntegerSet to intersect with.
     */
    public void intersect(IntegerSet other) {
        // JCF's retainAll is a perfect implementation of intersection
        set.retainAll(other.set);
    }

    /**
     * Modifies this set to be the set difference (this \ other).
     * The resulting set contains elements that are in this set but
     * not in the other set.
     * * @param other The other IntegerSet to difference with.
     */
    public void diff(IntegerSet other) {
        // JCF's removeAll is a perfect implementation of set difference
        set.removeAll(other.set);
    }

    /**
     * Modifies this set to be the complement (other \ this).
     * The resulting set contains elements that are in the other set but
     * not in this set.
     * * @param other The other IntegerSet to complement against.
     */
    public void complement(IntegerSet other) {
        // This is the most complex operation as it modifies 'this'
        // to become (other \ this).
        
        // 1. Start with a copy of 'other.set'
        List<Integer> tempComplement = new ArrayList<>(other.set);
        
        // 2. Remove all elements that are in 'this.set'
        tempComplement.removeAll(this.set);
        
        // 3. Replace 'this.set' with the result
        this.set = tempComplement;
    }

    /**
     * Checks if the set is empty (contains no elements).
     * * @return true if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in the format [element1, element2, ...].
     * Overrides the default Object.toString() method.
     * * @return A string representation of the set.
     */
    @Override
    public String toString() {
        // ArrayList's default toString() matches the required format "[1, 2, 3]"
        return set.toString();
    }
}