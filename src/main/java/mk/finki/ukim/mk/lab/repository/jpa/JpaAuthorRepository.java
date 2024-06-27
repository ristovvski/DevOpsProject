package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAuthorRepository extends JpaRepository<Author, Long> {

}
