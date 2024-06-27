package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    List<Review> findReviewsForBookById(Long id);
    List<Review> findReviewsForBookByIdAndDate(Long id, LocalDateTime from, LocalDateTime to);

    void saveReview(Long id, Integer score, String description, LocalDateTime date);
}
