package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.inmemory.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaAuthorRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final JpaAuthorRepository authorRepository;

    public AuthorServiceImpl(JpaAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        if(id == null)
            throw new IllegalArgumentException();
        return authorRepository.findById(id).get();
    }
}
