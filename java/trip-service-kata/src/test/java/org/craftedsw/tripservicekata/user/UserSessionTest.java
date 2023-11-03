package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserSessionTest {

    @Test
    void getInstance() {
        UserSession testee = UserSession.getInstance();

        assertNotNull(testee);
    }

    @Test
    void getLoggedUser() {
        UserSession userSession = mock(UserSession.class);
        User expectedUser = new User();
        when(userSession.getLoggedUser()).thenReturn(expectedUser);
        when(userSession.getLoggedUser()).thenReturn(expectedUser);

        User actualUser = userSession.getLoggedUser();

        verify(userSession).getLoggedUser();
        assertEquals(expectedUser, actualUser);
    }
}