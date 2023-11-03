package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.Collections;
import java.util.List;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = UserSession.getInstance().getLoggedUser();
		if (loggedUser != null) {
			boolean isFriend = verifyFriend(user, loggedUser);
			if (isFriend) {
				return TripDAO.findTripsByUser(user);
			}
			return Collections.emptyList();
		} else {
			throw new UserNotLoggedInException();
		}
	}

	private static boolean verifyFriend(User user, User loggedUser) {
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				return true;
			}
		}
		return false;
	}

}
