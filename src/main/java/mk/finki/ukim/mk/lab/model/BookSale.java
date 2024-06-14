package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sales")
public class BookSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numCopies;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    @ManyToOne
    private Book book;

    public BookSale(Integer numCopies, LocalDateTime timestamp, Book book) {
        this.numCopies = numCopies;
        this.timestamp = timestamp;
        this.book = book;
    }

    public BookSale(){

    }
}
