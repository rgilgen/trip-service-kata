package org.craftedsw.tripservicekata;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripDAO;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripService_OriginalTest {

    public UserSession setupMockedUserSessionMethod(User user) {
        UserSession secondObject = Mockito.mock(UserSession.class);
        Mockito.when(secondObject.getLoggedUser()).thenReturn(user);
        return secondObject;
    }

    @Test
    void testGetTripsByUser() {
        TripService_Original testee = new TripService_Original();
        User user = new User();
        UserSession userSessionMethod = setupMockedUserSessionMethod(user);
        List<Trip> trips;

        try (MockedStatic<UserSession> mockedUserSession = Mockito.mockStatic(UserSession.class)) {
            mockedUserSession.when(UserSession::getInstance).thenReturn(userSessionMethod);
            try (MockedStatic<TripDAO> mockedStatic = Mockito.mockStatic(TripDAO.class)) {
                mockedStatic.when(() -> TripDAO.findTripsByUser(user)).thenReturn(Collections.emptyList());
                trips = testee.getTripsByUser(user);
            }
        }

        assertEquals(0, trips.size());
    }

    @Test
    void testGetTripsByUserWithFriends() {
        TripService_Original testee = new TripService_Original();
        User user = new User();
        user.addFriend(user);
        UserSession userSessionMethod = setupMockedUserSessionMethod(user);
        List<Trip> trips;

        try (MockedStatic<UserSession> mockedUserSession = Mockito.mockStatic(UserSession.class)) {
            mockedUserSession.when(UserSession::getInstance).thenReturn(userSessionMethod);
            try (MockedStatic<TripDAO> mockedStatic = Mockito.mockStatic(TripDAO.class)) {
                mockedStatic.when(() -> TripDAO.findTripsByUser(user)).thenReturn(Collections.emptyList());
                trips = testee.getTripsByUser(user);
            }
        }

        assertEquals(0, trips.size());
    }

    @Test
    void testGetTripsByUserNullUsersThrowsException() {
        TripService_Original testee = new TripService_Original();
        User user = null;
        UserSession userSessionMethod = setupMockedUserSessionMethod(user);
        UserNotLoggedInException thrown;

        try (MockedStatic<UserSession> mockedUserSession = Mockito.mockStatic(UserSession.class)) {
            mockedUserSession.when(UserSession::getInstance).thenReturn(userSessionMethod);
            try (MockedStatic<TripDAO> mockedStatic = Mockito.mockStatic(TripDAO.class)) {
                mockedStatic.when(() -> TripDAO.findTripsByUser(user)).thenReturn(Collections.emptyList());
                thrown = Assertions.assertThrows(UserNotLoggedInException.class, () -> testee.getTripsByUser(user));
            }
        }

        assertNull(thrown.getMessage());
    }

}