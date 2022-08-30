package org.example.model;

public class Profile {
    private String id;
    private String firstname;
    private String lastname;
    private String userId;

    public Profile(String id, String firstname, String lastname, String userId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
