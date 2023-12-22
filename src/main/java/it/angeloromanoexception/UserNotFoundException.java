package it.angeloromanoexception;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String messaggio) {
        super(messaggio);
    }
}
