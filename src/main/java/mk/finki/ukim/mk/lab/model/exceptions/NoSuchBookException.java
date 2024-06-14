package mk.finki.ukim.mk.lab.model.exceptions;

public class NoSuchBookException extends RuntimeException{
    public NoSuchBookException() {
        super("There isn't a book with the given ID.");
    }

}
