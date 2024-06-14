package mk.finki.ukim.mk.lab.repository.inmemory;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.exceptions.NoSuchBookException;
import org.springframework.stereotype.Repository;
import mk.finki.ukim.mk.lab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    public static List<Book> listBooks = new ArrayList<>();

    private final BookStoreRepository bookStoreRepository;

    public BookRepository(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @PostConstruct
    public void init(){
        List<Author> tmpList1 = new ArrayList<>();
        tmpList1.add(new Author(1L, "Jane", "Austen", "Jane Austen was an English novelist known for her six major novels, including 'Pride and Prejudice'."));
        listBooks.add(new Book("ISBN123", "Pride and Prejudice", "Fiction", 1813, tmpList1, bookStoreRepository.findAll().get(0)));

        List<Author> tmpList2 = new ArrayList<>();
        tmpList2.add(new Author(2L, "William", "Shakespeare", "William Shakespeare was an English playwright and poet, widely regarded as one of the greatest writers in the English language."));
        listBooks.add(new Book("ISBN456", "Hamlet", "Tragedy", 1600, tmpList2, bookStoreRepository.findAll().get(1)));

        List<Author> tmpList3 = new ArrayList<>();
        tmpList3.add(new Author(3L, "George", "Orwell", "George Orwell was an English novelist and essayist known for works such as '1984' and 'Animal Farm'."));
        listBooks.add(new Book("ISBN789", "1984", "Dystopian", 1949, tmpList3, bookStoreRepository.findAll().get(2)));

        List<Author> tmpList4 = new ArrayList<>();
        tmpList4.add(new Author(4L, "Agatha", "Christie", "Agatha Christie was a British mystery writer known for her detective novels, including 'Murder on the Orient Express'."));
        listBooks.add(new Book("ISBN101", "Murder on the Orient Express", "Mystery", 1934, tmpList4, bookStoreRepository.findAll().get(3)));

        List<Author> tmpList5 = new ArrayList<>();
        tmpList5.add(new Author(5L, "J.K.", "Rowling", "J.K. Rowling is a British author best known for the 'Harry Potter' series."));
        listBooks.add(new Book("ISBN202", "Harry Potter and the Philosopher's Stone", "Fantasy", 1997, tmpList5, bookStoreRepository.findAll().get(4)));

    }

    public List<Book> findAll(){
        return listBooks;
    }

    public Optional<Book> findByIsbn(String isbn){
        return listBooks.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
    }

    public Author addAuthorToBook(Author author, Book book){
        Optional<Book> tmpBook = listBooks.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).findFirst();

        if(tmpBook.isEmpty())
            throw new IllegalArgumentException();

        tmpBook.get().getAuthors().add(author);
        listBooks.removeIf(b -> b.getIsbn().equals(book.getIsbn()));
        listBooks.add(tmpBook.get());

        return author;
    }

    public Book saveBook(Book book){
        listBooks.removeIf(b -> b.getId().equals(book.getId()));
        listBooks.add(book);
        return book;
    }

    public void deleteById(Long id){
        listBooks.removeIf(i -> i.getId().equals(id));
    }

    public Book findById(Long id){
        return listBooks.stream().filter(b -> b.getId().equals(id)).findFirst().orElseThrow(NoSuchBookException::new);
    }

}
