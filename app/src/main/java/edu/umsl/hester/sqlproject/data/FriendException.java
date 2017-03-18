package edu.umsl.hester.sqlproject.data;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendException extends Exception {

    public FriendException() {
        super();
    }

    public FriendException(String msg) {
        super(msg);
    }

    public FriendException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FriendException(Throwable cause) {
        super(cause);
    }
}
