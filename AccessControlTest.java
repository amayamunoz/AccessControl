//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Access Control Test
// Files: (a list of all source files used by that program)
// Course: CS300
//
// Author: Amaya Munoz
// Email: ajmunoz@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Kudirat Alimi
// Partner Email: kalimi@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * The AccessControlTest class contains our tests for the AccessControl class
 * 
 * @author amayamunoz
 *
 */
public class AccessControlTest {
    /*
     * Testing main. Runs each test and prints which (if any) failed.
     * int fails increments if any test methods fail\
     * if fails == 0, then main will print "All tests passed"
     * 
     */
    public static void main(String[] args) {
        int fails = 0;
        if (!testLogin1()) {
            System.out.println("testLogin1 [bad username] failed");
            fails++;
        }
        if (!testLogin2()) {
            System.out.println("testLogin2 [good login] failed");
            fails++;
        }
        if (!testLogin3()) {
            System.out.println("testLogin3 [bad username with default password] failed");
            fails++;
        }
        if (!testAddUser1()) {
            System.out.println("testAddUser1 failed");
            fails++;
        }
        if (!testAddUser2()) {
            System.out.println("testAddUser2 failed");
            fails++;
        }
        if (!testGiveAdmin()) {
            System.out.println("testGiveAdmin failed");
            fails++;
        }
        if (!testTakeAdmin()) {
            System.out.println("testTakeAdmin failed");
            fails++;
        }
        if (!testResetPassword()) {
            System.out.println("testResetPassword failed");
            fails++;
        }
        if (fails == 0)
            System.out.println("All tests passed!");

    }


    /**
     * This test checks if the isValidLogin method works successfully
     * The test tries to log in a user that doesn't exist
     * isValidLogin should return false
     * 
     * @return boolean test passed
     */
    public static boolean testLogin1() {
        AccessControl ac = new AccessControl(); //initializes user
        String user = "probablyNotInTheSystem1234"; //username
        String pw = "password"; //password
        return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
    }

    /**
     * this test checks if the isValidLogin method works successfully
     * This test tries to log in a user that does exist
     * isValidLogin should return true
     * @return boolean test passed
     */
    public static boolean testLogin2() {
        AccessControl ac = new AccessControl(); //initializes user
        String user = "admin"; //username
        String pw = "root"; //password
        return AccessControl.isValidLogin(user, pw); // isValidLogin should return true
    }

    /**
     * this test checks if the isValidLogin method works successfully
     * this test tries to log in a user that does not exist but has a correct password
     * isValidLogin should return false
     * @return boolean test passed
     */
    public static boolean testLogin3() {
        AccessControl ac = new AccessControl(); //initializes users
        String user = "probablyNotInTheSystem1234"; //username
        String pw = "changeme"; //password
        return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
    }

    /**
     * this test checks if the addUser method works successfully
     * this test tries to add a user from a user with no admin privileges
     * addUserReport should evaluate to false
     * @return boolean test passed
     */
    public static boolean testAddUser1() {
        AccessControl ac = new AccessControl();
        String user = "alexi";
        boolean addUserReport = ac.addUser(user);
        if (addUserReport)
            return false; // addUserReport should be false
        // Make sure user wasn't added anyway
        return !AccessControl.isValidLogin(user, "changeme");
    }

    /**
     * this test checks if the addUser method works successfully
     * this method tries to add a user from a user with no admin privileges
     * this method should evaluate to true
     * @return boolean test passed
     */
    public static boolean testAddUser2() {
        AccessControl ac = new AccessControl();
        ac.setCurrentUser("admin");
        String user = "alexi";
        boolean addUserReport = ac.addUser(user);
        if (!addUserReport)
            return false; // user should be added since current user is admin
        return AccessControl.isValidLogin(user, "changeme"); // should return true
    }

    /**
     * this test checks if the giveAdmin() method works successfully
     * this test tries to give admin status to a user from a user with admin privileges
     * this test should give the set currentUser admin priviledges 
     * @return boolean test passed
     */
    public static boolean testGiveAdmin() {
        AccessControl ac = new AccessControl();
        ac.setCurrentUser("admin");
        ac.addUser("genki1");
        if (ac.giveAdmin("genki1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this test checks if the takeAdmin method works successfully
     * this test tries to take admin status from a user using a user with admin privileges
     * this test should remove user priviledges from "genki2"
     * @return boolean test passed
     */
    public static boolean testTakeAdmin() {
        AccessControl ac = new AccessControl(); //initializes user
        ac.setCurrentUser("admin"); //sets current user to "admin"
        ac.addUser("genki2", true); //adds user and checks if isadmin
        if (ac.takeAdmin("genki2")) { //takes away admin privilidges
            return true;
        } else {
            return false;
        }

    }

    /**this test checks if the resetPassword method works successfully
     * this test tries to reset a users password using a user with admin privileges
     * this method should return true
     * @return boolean test passed
     */
    public static boolean testResetPassword() {
        AccessControl ac = new AccessControl(); //initializes user
        ac.setCurrentUser("admin"); //sets currentUser to "admin"
        ac.addUser("genki3"); //adds user
        ac.setCurrentUser("genki3"); //sets "genki3" to currentUser
        ac.changePassword("testpassword"); //currentUser genki3 changes password
        ac.setCurrentUser("admin"); //sets the currentUser back to "admin"
        if (ac.resetPassword("genki3")) { //"admin" with admin privilidges resets
            return true;                 //user "genki3"'s password
        } else {
            return false;
        }

    }

}