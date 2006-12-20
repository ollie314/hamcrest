/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest;

import junit.framework.TestCase;

public abstract class AbstractMatcherTest extends TestCase {

    /**
     * Create an instance of the Matcher so some generic safety-net tests can be run on it.
     */
    protected abstract Matcher<?> createMatcher();

    protected static final Object ARGUMENT_IGNORED = new Object();
    protected static final Object ANY_NON_NULL_ARGUMENT = new Object();

    public <T> void assertMatches(String message, Matcher<T> c, T arg) {
        assertTrue(message, c.matches(arg));
    }

    public <T> void assertDoesNotMatch(String message, Matcher<T> c, T arg) {
        assertFalse(message, c.matches(arg));
    }

    public void assertDescription(String expected, Matcher matcher) {
        Description description = new StringDescription();
        matcher.describeTo(description);
        assertEquals(expected, description.toString());
    }

    public void testIsNullSafe() {
       // should not throw a NullPointerException
       createMatcher().matches(null);
    }

    public void testCopesWithUnknownTypes() {
        // should not throw ClassCastException
        createMatcher().matches(new UnknownType());
    }

    public static class UnknownType {
    }

}
