package com.platformbuilders.exception;

public class UserExceptions {
    public static class UserNotFoundException extends Exception {
        public UserNotFoundException() {
            super("Could not find user");
        }
    }

    public static class CannotUpdateUserException extends Exception {
        public CannotUpdateUserException() {
            super("Could not update user. User not found or wrong data");
        }
    }
}
