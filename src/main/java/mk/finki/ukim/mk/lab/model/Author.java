package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import mk.finki.ukim.mk.lab.model.converters.AuthorFullname;
import mk.finki.ukim.mk.lab.model.converters.AuthorFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@ToString(exclude = "bookList")
@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = AuthorFullnameConverter.class)
    private AuthorFullname authorFullname;
    private String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> bookList;

    public Author(Long id, String name, String surname, String biography) {
        this.authorFullname = new AuthorFullname(name, surname);
        this.biography = biography;
    }

    public Author(Long id, String name, String surname, String biography, LocalDate dateOfBirth) {
        this.authorFullname = new AuthorFullname(name, surname);
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }

    public Author(){

    }
}
