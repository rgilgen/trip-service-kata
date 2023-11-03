package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

 @Test
 void testGetFriends(){
  User testee = new User();
  int expectedSize = 0;

  int actualSize = testee.getFriends().size();

  assertEquals(expectedSize,actualSize);
 }

 @Test
 void testAddFriends(){
  User testee = new User();
  int expectedSize = 1;
  int actualSize;

  testee.addFriend(new User());
  actualSize = testee.getFriends().size();

  assertEquals(expectedSize,actualSize);
 }

 @Test
 void testAddTrips(){
  User testee = new User();
  int expectedSize = 1;
  int actualSize;

  testee.addTrip(new Trip());
  actualSize = testee.trips().size();

  assertEquals(expectedSize,actualSize);
 }

 @Test
 void testTrips(){
  User testee = new User();
  int expectedSize = 0;

  int actualSize = testee.trips().size();

  assertEquals(expectedSize,actualSize);
 }


}
