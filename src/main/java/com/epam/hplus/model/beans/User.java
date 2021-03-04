package com.epam.hplus.model.beans;

import java.util.Date;

/**
 * Bean class of model layer represents user.
 */
public class User {
    /**
     * User's role that defines account privileges level. Admin manges product catalog and other
     * user's accounts.
     */
    public static final int ROLE_ADMIN = 1;
    /**
     * User's role that defines account privileges level. Manager manges orders of the other users
     */
    public static final int ROLE_MANAGER = 2;
    /**
     * User's role that defines account privileges level. Customer can make an orders, and remove
     * them.
     */
    public static final int ROLE_CUSTOMER = 3;
    /**
     * Unique identification {@code String} value for user's account, also used during authorization
     * process.
     */
    private String username;
    /**
     * Secret {@code String} value that is determined by user and used during process of
     * authorization. Represents encrypted value of original password.
     */
    private String password;
    /**
     * First name of the user.
     */
    private String firstName;
    /**
     * Last name of the user.
     */
    private String lastName;
    /**
     * Preferred type of activity for user.
     */
    private String activity;
    /**
     * Date of birth of the user.
     */
    private Date dateOfBirth;
    /**
     * {@code int} value represents user's role and defines user's account privileges.
     */
    private int role;
    /**
     * {@code boolean} value represents status of the user account. {@code true} - for active
     * account, {@code false} - for deleted accounts.
     */
    private boolean active;

    /**
     * Explicit default constructor.
     */
    public User() {
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} username that represents unique identification name for the user's
     * account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param username {@code String} value represents unique identification name for the user's
     *                 account.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} value that represents encrypted value of the secret user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param password {@code String} value that represents encrypted value of the secret user's
     *                 password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} that represents the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param firstName {@code String} represents first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} that represents the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param lastName {@code String} represents last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} that represents preferred type of activity for the user
     */
    public String getActivity() {
        return activity;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param activity {@code String} represents preferred type of activity for the user
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code Date} value that represents date of birth of the user
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param dateOfBirth {@code Date} value that represents date of birth of the user
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code int} value represents user's role and defines user's account privileges
     */
    public int getRole() {
        return role;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param role {@code int} value represents user's role and defines user's account privileges
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code boolean} value that represents status of the user account. {@code true} - for
     * active account, {@code false} - for deleted accounts
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param active {@code boolean} value represents status of the user account. {@code true} - for
     *               active account, {@code false} - for deleted accounts
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
