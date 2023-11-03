package org.craftedsw.tripservicekata.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CollaboratorCallExceptionTest {
    @Test
    void testNoneParameter() {
        CollaboratorCallException testee = new CollaboratorCallException();

        assertNull(testee.getCause());
    }

    @Test
    void testWithThrowable() {
        CollaboratorCallException testee = new CollaboratorCallException(new Throwable("test"));

        assertEquals("test", testee.getCause().getMessage());
    }

    @Test
    void testWithMessageAndThrowable() {
        CollaboratorCallException testee = new CollaboratorCallException("this is a", new Throwable("test"));

        assertEquals("this is a", testee.getMessage());
        assertEquals("test", testee.getCause().getMessage());
    }
}