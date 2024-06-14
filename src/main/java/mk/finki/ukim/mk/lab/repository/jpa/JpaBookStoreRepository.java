package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookStoreRepository extends JpaRepository<BookStore, Long> {
}
