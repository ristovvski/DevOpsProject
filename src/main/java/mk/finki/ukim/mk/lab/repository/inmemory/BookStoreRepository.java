package mk.finki.ukim.mk.lab.repository.inmemory;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookStoreRepository {
    public static List<BookStore> listBookStores = new ArrayList<>();

    @PostConstruct
    public void init(){
        listBookStores.add(new BookStore("Book Haven", "New York", "123 Main St"));
        listBookStores.add(new BookStore("City Books", "Los Angeles", "456 Oak St"));
        listBookStores.add(new BookStore("Readers' Paradise", "Chicago", "789 Maple St"));
        listBookStores.add(new BookStore("Book Oasis", "San Francisco", "101 Pine St"));
        listBookStores.add(new BookStore("Literary Nook", "Seattle", "202 Cedar St"));
    }

    public List<BookStore> findAll(){
        return listBookStores;
    }

    public BookStore findById(Long id) {
        return listBookStores.stream().filter(l -> l.getId().equals(id)).findFirst().get();
    }
}
