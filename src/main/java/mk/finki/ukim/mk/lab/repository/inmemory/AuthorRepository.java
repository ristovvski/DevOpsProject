package mk.finki.ukim.mk.lab.repository.inmemory;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    public static List<Author> listAuthors = new ArrayList<>();

    @PostConstruct
    public void init(){
        listAuthors.add(new Author(1L, "Jane", "Austen", "Jane Austen was an English novelist known for her six major novels, including 'Pride and Prejudice'."));
        listAuthors.add(new Author(2L, "William", "Shakespeare", "William Shakespeare was an English playwright and poet, widely regarded as one of the greatest writers in the English language."));
        listAuthors.add(new Author(3L, "George", "Orwell", "George Orwell was an English novelist and essayist known for works such as '1984' and 'Animal Farm'."));
        listAuthors.add(new Author(4L, "Agatha", "Christie", "Agatha Christie was a British mystery writer known for her detective novels, including 'Murder on the Orient Express'."));
        listAuthors.add(new Author(5L, "J.K.", "Rowling", "J.K. Rowling is a British author best known for the 'Harry Potter' series"));
    }

    public List<Author> findAll(){
        return listAuthors;
    }

    public Optional<Author> findById(Long id){
        return listAuthors.stream().filter(a -> a.getId().equals(id)).findFirst();
    }


}
