//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AccessControl
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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The AccessControl class maintains a record of valid users and their properties
 * 
 * @author amayamunoz
 *
 */
public class AccessControl {

    /**
     * Launch an AccessControl instance
     */
    public static void main(String[] args) {
        AccessControl ac = new AccessControl();
        // If we have any persistent information to lead
        // this is where we load it.
        Scanner userIn = new Scanner(System.in);
        ac.loginScreen(userIn);
    }

    private static ArrayList<User> users; // An ArrayList of valid users.
    private User currentUser; // Who is currently logged in, if anyone?
    private static final String DEFAULT_PASSWORD = "changeme";
    // Default password given to
    // new users or when we reset a user's password.


    /**
     * This no-parameter constructor makes a new AccessControl object
     * 
     * It initializes any non-static field, and initializes the class variables
     * 
     */
    public AccessControl() {
        users = new ArrayList<>(); // initializes our users array list
        User admin = new User("admin", "root", true); // creates the admin account
        users.add(admin); // adds the admin account to the users list
        currentUser = null; // set current user to null

    }

    /**
     * Checks whether a username and password combination is valid and in the system
     * 
     * This method goes through our users ArrayList (which contains all of the users) and returns
     * true if the username/password pair matches any user in the array list and false otherwise.
     * 
     * Its only job is to determine whether a username/password pair is valid.
     * 
     * @param username is the username the user entered
     * @param password is the password the user entered
     * 
     * @return true if valid, false if not
     */
    public static boolean isValidLogin(String username, String password) {
        boolean isValidLogin = false; // holds whether it is a valid login or not
        // iterates through our list and sees if any user object holds that username
        // if it does, it checks to see if it matches the password within that entry
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                if (users.get(i).isValidLogin(password) == true)
                    isValidLogin = true;
                break; // so we don't keep iterating through the array list once we find a match
            } else {
                isValidLogin = false;
            }
        }
        return isValidLogin;
    }

    /**
     * This method changes the current users password
     * 
     * @param newPassword is the password the user wants their old password to be changed to
     */
    public void changePassword(String newPassword) {
        currentUser.setPassword(newPassword);
    }

    /**
     * This method logs out the current user by making currentUser null
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * A mutator you can use to write tests without simulating user input
     * 
     * Sets the current user
     * 
     * @param username is the username we want to set to the currentUser
     */
    public void setCurrentUser(String username) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                currentUser = users.get(i);
                break;
            }
        }

    }


    /**
     * Creates a new user with the default password and isAdmin==false and adds to our users array.
     * Only an admin can add a new user to the system.
     * 
     * @param username is the new user we want to add
     * @return false if not an admin or if currentUser is null or or if it could not add that user
     *         because the username already exists.
     * @return true if able to add user
     */
    public boolean addUser(String username) {
        if (currentUser == null) {
            return false;
        } else if (currentUser.getIsAdmin() == true) {
            // returns false if user w that username already exists
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                    return false; // means user with that name already exists
                }
            }
            User user = new User(username, DEFAULT_PASSWORD, false);
            users.add(user);
            return true;
        } else {
            return false; // means current user was not an admin
        }
    }

    /**
     * Create a new user to add to system and specify their admin status. Only admins have this
     * privilege.
     * 
     * @param username is the new users username that we want to add
     * @param isAdmin is the boolean that says whether the new user gets admin status or not
     * @return false if currentUser is null, if there is already a user with that username, or if
     *         current user is not an admin.
     * @return true if able to add user
     */
    public boolean addUser(String username, boolean isAdmin) {
        if (currentUser == null) {
            return false;
        } else if (currentUser.getIsAdmin() == true) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                    return false; // means user with that name already exists
                }
            }
            User user = new User(username, DEFAULT_PASSWORD, isAdmin);
            users.add(user);
            return true;
        } else {
            return false; // means current user was not an admin
        }
    }

    /**
     * Removes a user from the system (names should be unique). Only admins have this privilege.
     * 
     * @param username which is the user we want to remove
     * @return true if able to remove user
     * @return false if current user is null, current user is not an admin, or could not find user
     *         with that username
     */
    public boolean removeUser(String username) {
        if (currentUser == null) {
            return false;
        } else if (currentUser.getIsAdmin() == true) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                    users.remove(i);
                    return true;
                }
            }
            return false; // means could not find user with that username
        } else {
            return false; // means current user was not an admin
        }
    }

    /**
     * Gives a user admin power. Only admins have this privilege.
     * 
     * @param username is the user we want to give admin status to
     * @return true if able to give admin status to user
     * @return false if current user is null, current user is not an admin, or could not find user
     *         with that username
     */
    public boolean giveAdmin(String username) {
        if (currentUser == null) {
            return false;
        } else if (currentUser.getIsAdmin() == true) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                    users.get(i).setIsAdmin(true);
                    return true;
                }
            }
            return false; // means could not find user with that username
        } else {
            return false; // means current user was not an admin
        }
    }

    /**
     * Takes a users admin power. Only admins have this privilege.
     * 
     * note: method does not account for if user with that username is already an admin or not, just
     * makes it false so if the username entered was already not an admin, it does nothing
     * 
     * @param username is the user we want to take admin status from
     * @return true if able to take admin status to user
     * @return false if current user is null, current user is not an admin, or could not find user
     *         with that username
     * 
     */
    public boolean takeAdmin(String username) {
        if (currentUser == null) {
            return false;
        } else if (currentUser.getIsAdmin() == true) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                    users.get(i).setIsAdmin(false);
                    return true;
                }
            }
            return false; // means could not find user with that username
        } else {
            return false; // means current user was not an admin
        }
    }

    /**
     * Reset another user's password to the default password. Only admins have this privilege.
     * 
     * @param username, the user who we want to resets password
     * @return true if able to reset users password
     * @return false if current user is null, current user is not an admin, or could not find user
     *         with that username
     */
    public boolean resetPassword(String username) {
        if (currentUser == null) {
            return false;
        } else if (currentUser.getIsAdmin() == true) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                    users.get(i).setPassword(DEFAULT_PASSWORD);
                    return true;
                }
            }
            return false; // means could not find user with that username
        } else {
            return false; // means current user was not an admin
        }
    }


    /**
     * Main driver loop - Prompts the user for login information, calls the isValidLogin method, and
     * if the login is valid it calls the sessionScreen method.
     * 
     * This is what a user uses to log in.
     * 
     * @param userInputScanner contains the users input
     */
    public void loginScreen(Scanner userInputScanner) {
        while (true) {
            System.out.println("Username:");
            String username = userInputScanner.nextLine();
            System.out.println("Password:");
            String password = userInputScanner.nextLine();
            if (isValidLogin(username, password) == true) {
                sessionScreen(username, userInputScanner);
            } else {
                System.out.println("invalid login");
            }
        }
    }

    /**
     * This method sets the currentUser, and allows them to changePassword or logout. If they are an
     * admin, they can use the admin methods as well.
     * 
     * note: this method does not account for when a user types commands in incorrectly. here, we
     * assume a user is always writing them in correctly.
     * 
     * @param username is the username of the user
     * @param userInputScanner contains the users input
     */
    public void sessionScreen(String username, Scanner userInputScanner) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(username)) {
                currentUser = users.get(i);
                break; // so we don't keep going through the entire ArrayList
            }
        }

        while (currentUser != null) {
            System.out.println("Things you can do: (write exactly as shown without brackets) ");
            System.out.println("logout\n" + "newpw [newpassword]");
            System.out.println("adduser [username]\n" + "adduser [username] [true or false]\n"
                + "rmuser [username]\n" + "giveadmin [username]\n" + "rmadmin [username]\n"
                + "resetpw [username]");

            String command = userInputScanner.nextLine(); // holds what the user wants progran to do

            // we create an array to put the users input from command into, so we can hold the
            // different words at different values and do things with them
            String[] userInputArray = new String[command.length()];
            userInputArray = command.split(" ");

            switch (userInputArray[0]) {
                case "logout":
                    logout();
                    break;
                case "newpw":
                    // if the user requested to have a newpw, that means they also wrote the new
                    // password they want in their command meaning the second word (the password
                    // they want) is held in the 1st index
                    changePassword(userInputArray[1]);
                    break;
                case "adduser":
                    if (userInputArray.length == 2) {
                        addUser(userInputArray[1]);
                    } else if (userInputArray.length == 3) {
                        // turn the string in the second index of array to a boolean so it can be
                        // read
                        addUser(userInputArray[1], Boolean.parseBoolean(userInputArray[2]));
                    }
                    break;
                case "rmuser":
                    removeUser(userInputArray[1]);
                    break;
                case "giveadmin":
                    giveAdmin(userInputArray[1]);
                    break;
                case "rmadmin":
                    takeAdmin(userInputArray[1]);
                    break;
                case "resetpw":
                    resetPassword(userInputArray[1]);
                    break;
            }
        }
    }

}
