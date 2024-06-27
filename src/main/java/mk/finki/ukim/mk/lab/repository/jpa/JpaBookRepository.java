package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
//    void addAuthorToBook(Author tmpAuthor, Book tmpBook);

}
