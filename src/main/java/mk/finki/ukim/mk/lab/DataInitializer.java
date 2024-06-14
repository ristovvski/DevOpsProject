package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.*;
import mk.finki.ukim.mk.lab.repository.jpa.JpaAuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaBookStoreRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(JpaBookStoreRepository bookStoreRepository, JpaAuthorRepository authorRepository,
                                  JpaBookRepository bookRepository, JpaReviewRepository reviewRepository) {
        return args -> {
            // Create a few book stores
            BookStore store1 = new BookStore("Book Haven", "New York", "123 Main St");
            BookStore store2 = new BookStore("City Books", "Los Angeles", "456 Oak St");
            BookStore store3 = new BookStore("Readers' Paradise", "Chicago", "789 Maple St");
            BookStore store4 = new BookStore("Book Oasis", "San Francisco", "101 Pine St");
            BookStore store5 = new BookStore("Literary Nook", "Seattle", "202 Cedar St");

            // Save them to the repository
            bookStoreRepository.saveAll(List.of(store1, store2, store3, store4, store5));

            // Create authors
            Author author1 = new Author(null, "Jane", "Austen", "Jane Austen was an English novelist known for her six major novels, including 'Pride and Prejudice'.", LocalDate.of(1980, 1, 15));
            Author author2 = new Author(null, "William", "Shakespeare", "William Shakespeare was an English playwright and poet, widely regarded as one of the greatest writers in the English language.", LocalDate.of(1564, 4, 23));
            Author author3 = new Author(null, "George", "Orwell", "George Orwell was an English novelist and essayist known for works such as '1984' and 'Animal Farm'.", LocalDate.of(1903, 6, 25));
            Author author4 = new Author(null, "Agatha", "Christie", "Agatha Christie was a British mystery writer known for her detective novels, including 'Murder on the Orient Express'.", LocalDate.of(1890, 9, 15));
            Author author5 = new Author(null, "J.K.", "Rowling", "J.K. Rowling is a British author best known for the 'Harry Potter' series.", LocalDate.of(1965, 7, 31));

            // Save authors to the repository
            authorRepository.saveAll(List.of(author1, author2, author3, author4, author5));

            // Create books
            Book book1 = new Book("ISBN123", "Pride and Prejudice", "Fiction", 1813, List.of(author1), store1);
            Book book2 = new Book("ISBN456", "Hamlet", "Tragedy", 1600, List.of(author2), store2);
            Book book3 = new Book("ISBN789", "1984", "Dystopian", 1949, List.of(author3), store3);
            Book book4 = new Book("ISBN101", "Murder on the Orient Express", "Mystery", 1934, List.of(author4), store4);
            Book book5 = new Book("ISBN202", "Harry Potter and the Philosopher's Stone", "Fantasy", 1997, List.of(author5), store5);

            // Save books to the repository
            bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));

            // Create reviews
            List<Review> reviews = List.of(
                    new Review(4, "A great read!", book1, LocalDateTime.now().minusDays(7).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(5, "Highly recommended!", book2, LocalDateTime.now().minusDays(5).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(3, "Interesting storyline.", book3, LocalDateTime.now().minusDays(10).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(4, "Captivating plot.", book4, LocalDateTime.now().minusDays(8).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(5, "A must-read!", book5, LocalDateTime.now().minusDays(12).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(3, "Enjoyable.", book1, LocalDateTime.now().minusDays(6).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(4, "Well-written.", book2, LocalDateTime.now().minusDays(9).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(5, "Couldn't put it down!", book3, LocalDateTime.now().minusDays(11).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(3, "Good character development.", book4, LocalDateTime.now().minusDays(7).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(4, "Impressive!", book5, LocalDateTime.now().minusDays(5).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(5, "Page-turner!", book1, LocalDateTime.now().minusDays(8).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(3, "Average.", book2, LocalDateTime.now().minusDays(10).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(4, "Well-crafted.", book3, LocalDateTime.now().minusDays(6).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(5, "Entertaining!", book4, LocalDateTime.now().minusDays(9).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(3, "Decent read.", book5, LocalDateTime.now().minusDays(11).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(4, "Thought-provoking.", book1, LocalDateTime.now().minusDays(7).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(5, "Brilliant!", book2, LocalDateTime.now().minusDays(5).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(3, "Unexpected twists.", book3, LocalDateTime.now().minusDays(10).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(4, "Engaging.", book4, LocalDateTime.now().minusDays(8).minusHours((int) (Math.random() * 24 * 30))),
                    new Review(5, "Loved it!", book5, LocalDateTime.now().minusDays(12).minusHours((int) (Math.random() * 24 * 30)))
            );

            // Save reviews to the repository
            reviewRepository.saveAll(reviews);
        };
    }
}
