package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "book-stores")
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    @OneToMany(mappedBy = "bookStore", fetch = FetchType.EAGER)
    private List<Book> bookList;

    public BookStore(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public BookStore(){

    }
}
