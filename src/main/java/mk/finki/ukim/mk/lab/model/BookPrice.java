package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "bookList")
@Data
@Entity
@Table(name = "price")
public class BookPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price;
    private Integer discount;
    @OneToMany(mappedBy = "bookPrice")
    private List<Book> bookList;

    public BookPrice(Long id, Integer price, Integer discount, List<Book> bookList) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.bookList = bookList;
    }

    public BookPrice() {

    }
}
