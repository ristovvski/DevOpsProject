package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookId(Long id);
    List<Review> findByTimestampBetweenAndBook_Id(LocalDateTime from, LocalDateTime to, Long id);
}
