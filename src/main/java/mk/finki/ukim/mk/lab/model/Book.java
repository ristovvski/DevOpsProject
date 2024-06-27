package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString(exclude = {"authors", "bookList"})
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    @ManyToMany
    private List<Author> authors = new ArrayList<>();
    @ManyToOne
    private BookStore bookStore;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Review> reviews;
    @ManyToOne
    private BookPrice bookPrice;

    public Book(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
        this.reviews = new ArrayList<Review>();
    }

    public Book(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore, Long id) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
        this.reviews = new ArrayList<Review>();
    }

    public Book(String isbn, String title, String genre, int year, List<Author> authors, Long id, BookStore bookStore, List<Review> reviews) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
        this.reviews = reviews;
    }

    public Book() {

    }
}
