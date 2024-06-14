package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.inmemory.BookStoreRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final JpaBookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(JpaBookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

}
