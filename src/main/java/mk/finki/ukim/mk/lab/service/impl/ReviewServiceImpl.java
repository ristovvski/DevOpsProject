package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookStoreRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaReviewRepository;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    JpaBookRepository bookRepository;
    JpaReviewRepository reviewRepository;

    public ReviewServiceImpl(JpaBookRepository bookRepository, JpaReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findReviewsForBookById(Long id) {
//        Book tmp = bookRepository.findById(id).get();
//        return tmp.getReviews();
        return reviewRepository.findByBookId(id);
    }

    @Override
    public List<Review> findReviewsForBookByIdAndDate(Long id, LocalDateTime from, LocalDateTime to) {
        return reviewRepository.findByTimestampBetweenAndBook_Id(from, to, id);
    }

    @Override
    public void saveReview(Long id, Integer score, String description, LocalDateTime date) {
        Book tmpBook = bookRepository.findById(id).get();
        Review tmp = new Review(score, description, tmpBook, date);

        reviewRepository.save(tmp);
    }
}
