package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.JpaAuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final JpaAuthorRepository authorRepository;
    private final JpaBookRepository bookRepository;
    private final JpaBookStoreRepository bookStoreRepository;

    public BookServiceImpl(JpaAuthorRepository authorRepository, JpaBookRepository bookRepository, JpaBookStoreRepository bookStoreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        if(authorId == null || isbn == null || isbn.isEmpty())
            throw new IllegalArgumentException();

        Author tmpAuthor = authorRepository.findById(authorId).get();
        Book tmpBook = bookRepository.findByIsbn(isbn).get();

        List<Author> tmpAuthors = tmpBook.getAuthors();
        tmpAuthors.add(tmpAuthor);

        Book reference = bookRepository.getReferenceById(tmpBook.getId());
        reference.setAuthors(tmpAuthors);
        bookRepository.save(reference);

        return tmpAuthor;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        if(isbn == null || isbn.isEmpty())
            throw new IllegalArgumentException();

        return bookRepository.findByIsbn(isbn).get();
    }

    // Kluch za knigata e ISBN, ne ID!
    public Book saveBook(String title, String isbn, String genre, Integer year, Long id){
        BookStore bookStore = bookStoreRepository.findById(id).get();
        Optional<Book> checkIfBookExists = bookRepository.findByIsbn(isbn);
        Book tmp;

        if(!checkIfBookExists.isPresent()){
            tmp = new Book(isbn, title, genre, year, new ArrayList<Author>(), bookStore);
        }else{
            Book bookToUpdate = bookRepository.getReferenceById(checkIfBookExists.get().getId());
            bookToUpdate.setTitle(title);
            bookToUpdate.setGenre(genre);
            bookToUpdate.setYear(year);
            bookToUpdate.setBookStore(bookStore);
            return bookRepository.save(bookToUpdate);
        }

        return bookRepository.save(tmp);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }
}
