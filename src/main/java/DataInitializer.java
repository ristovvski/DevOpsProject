import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initData() {
        // Execute SQL statements to populate the database
        jdbcTemplate.execute("INSERT INTO authors (id, author_fullname, biography, date_of_birth) VALUES " +
                "(1, 'Jane Austen', 'Jane Austen was an English novelist known for her six major novels, including ''Pride and Prejudice''.', '1980-01-15'), " +
                "(2, 'William Shakespeare', 'William Shakespeare was an English playwright and poet, widely regarded as one of the greatest writers in the English language.', '1564-04-23'), " +
                "(3, 'George Orwell', 'George Orwell was an English novelist and essayist known for works such as ''1984'' and ''Animal Farm''.', '1903-06-25'), " +
                "(4, 'Agatha Christie', 'Agatha Christie was a British mystery writer known for her detective novels, including ''Murder on the Orient Express''.', '1890-09-15'), " +
                "(5, 'J.K. Rowling', 'J.K. Rowling is a British author best known for the ''Harry Potter'' series.', '1965-07-31')");

        jdbcTemplate.execute("INSERT INTO book_stores (id, name, city, address) VALUES " +
                "(1, 'Book Haven', 'New York', '123 Main St'), " +
                "(2, 'City Books', 'Los Angeles', '456 Oak St'), " +
                "(3, 'Readers'' Paradise', 'Chicago', '789 Maple St'), " +
                "(4, 'Book Oasis', 'San Francisco', '101 Pine St'), " +
                "(5, 'Literary Nook', 'Seattle', '202 Cedar St')");

        jdbcTemplate.execute("INSERT INTO books (isbn, title, genre, year, book_store_id) VALUES " +
                "('ISBN123', 'Pride and Prejudice', 'Fiction', 1813, 1), " +
                "('ISBN456', 'Hamlet', 'Tragedy', 1600, 2), " +
                "('ISBN789', '1984', 'Dystopian', 1949, 3), " +
                "('ISBN101', 'Murder on the Orient Express', 'Mystery', 1934, 4), " +
                "('ISBN202', 'Harry Potter and the Philosopher''s Stone', 'Fantasy', 1997, 5)");

        jdbcTemplate.execute("INSERT INTO books_authors (book_id, author_id) VALUES " +
                "(1, 1), " +
                "(2, 2), " +
                "(3, 3), " +
                "(4, 4), " +
                "(5, 5)");

        jdbcTemplate.execute("INSERT INTO reviews (score, description, book_id, timestamp) VALUES " +
                "(4, 'A great read!', 1, NOW() - INTERVAL '7 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(5, 'Highly recommended!', 2, NOW() - INTERVAL '5 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(3, 'Interesting storyline.', 3, NOW() - INTERVAL '10 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(4, 'Captivating plot.', 4, NOW() - INTERVAL '8 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(5, 'A must-read!', 5, NOW() - INTERVAL '12 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(3, 'Enjoyable.', 1, NOW() - INTERVAL '6 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(4, 'Well-written.', 2, NOW() - INTERVAL '9 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(5, 'Couldn''t put it down!', 3, NOW() - INTERVAL '11 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(3, 'Good character development.', 4, NOW() - INTERVAL '7 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(4, 'Impressive!', 5, NOW() - INTERVAL '5 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(5, 'Page-turner!', 1, NOW() - INTERVAL '8 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(3, 'Average.', 2, NOW() - INTERVAL '10 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(4, 'Well-crafted.', 3, NOW() - INTERVAL '6 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(5, 'Entertaining!', 4, NOW() - INTERVAL '9 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(3, 'Decent read.', 5, NOW() - INTERVAL '11 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(4, 'Thought-provoking.', 1, NOW() - INTERVAL '7 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(5, 'Brilliant!', 2, NOW() - INTERVAL '5 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(3, 'Unexpected twists.', 3, NOW() - INTERVAL '10 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(4, 'Engaging.', 4, NOW() - INTERVAL '8 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)), " +
                "(5, 'Loved it!', 5, NOW() - INTERVAL '12 days' - INTERVAL '2 hours' * FLOOR(random() * 24 * 30)))");
    }
}
