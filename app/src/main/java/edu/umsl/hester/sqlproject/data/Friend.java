package edu.umsl.hester.sqlproject.data;

/**
 * Created by Austin on 3/17/2017.
 */

public class Friend {

    private int _id;
    private String firstName;
    private String lastName;
    private String email;

    public Friend(int _id, String firstName, String lastName, String email) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int get_id() {
        return _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
