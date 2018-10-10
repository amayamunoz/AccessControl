//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: User
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
 * The user class contains the methods for an object of type User
 * 
 * @author amayamunoz
 *
 */
public class User {

    private final String USERNAME; // The user's name
    private String password; // The user's password
    private boolean isAdmin; // Whether or not the user has Admin powers

    // Creates a new user with the given password and admin status
    public User(String username, String password, boolean isAdmin) {
        this.USERNAME = username;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    // Report whether the password is correct
    public boolean isValidLogin(String password) {
        boolean isValidLogin = false;
        if (this.password.equals(password)) {
            isValidLogin = true;
        }
        return isValidLogin;
    }

    // Return the user's name
    public String getUsername() {
        return this.USERNAME;
    }

    // Report whether the user is an admin
    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    // Set the new password
    public void setPassword(String password) {
        this.password = password;
    }

    // Set the new admin status
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
