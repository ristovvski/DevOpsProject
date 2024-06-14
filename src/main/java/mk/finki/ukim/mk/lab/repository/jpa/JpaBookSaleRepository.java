package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBookSaleRepository extends JpaRepository<BookSale, Long> {
    List<BookSale> findAllByBook_Id(Long id);
//    List<BookSale> findAllByBook_BookStore(Long id);
}
