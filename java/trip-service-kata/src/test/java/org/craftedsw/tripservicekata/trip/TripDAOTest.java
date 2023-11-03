package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TripDAOTest {

    @Test
    void testFindTripsByUserStaticReplacement() {
        User user = new User();
        List<Trip> expectedList;

        try (MockedStatic<TripDAO> mockedStatic = Mockito.mockStatic(TripDAO.class)) {
            mockedStatic.when(() -> TripDAO.findTripsByUser(user)).thenReturn(Collections.emptyList());
            expectedList = TripDAO.findTripsByUser(user);
        }

        assertEquals(0, expectedList.size());
    }

    @Test
    void testFindTripsByUserExceptionThatNotShouldBeTested() {
        CollaboratorCallException thrown = Assertions.assertThrows(CollaboratorCallException.class, () -> TripDAO.findTripsByUser(null));

        assertEquals("TripDAO should not be invoked on an unit test.", thrown.getMessage());
    }
}
