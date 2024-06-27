package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookSale;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.model.exceptions.NoSuchBookException;
import mk.finki.ukim.mk.lab.service.BookSaleService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;
    private final ReviewService reviewService;
    private final BookSaleService bookSaleService;

    public BookController(BookService bookService, BookStoreService bookStoreService,
                          ReviewService reviewService, BookSaleService bookSaleService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
        this.reviewService = reviewService;
        this.bookSaleService = bookSaleService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute(bookService.listBooks());
        if(error != null){
            model.addAttribute("error", error);
            System.out.println(error);
        }
        return "listBooks";
    }

    @GetMapping("/add")
    public String addBook(Model model){
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        return "addBook";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookstore){
        this.bookService.saveBook(title, isbn, genre, year, bookstore);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
            try{
                Book book = bookService.findById(id);
                List<BookStore> bookStores = bookStoreService.findAll();

                model.addAttribute("book", book);
                model.addAttribute("bookStores", bookStores);

                return "addBook";
            } catch(NoSuchBookException exception){
                return "redirect:/books?error=NoBookWithGivenID";
            }

    }

    @GetMapping("/review/{id}")
    public String getReviews(@PathVariable Long id,
                             Model model,
                             @RequestParam(required = false) LocalDateTime from,
                             @RequestParam(required = false) LocalDateTime to){
        try{
            Book book = bookService.findById(id);
            List<Review> reviews;
            if(from == null){
                reviews = reviewService.findReviewsForBookById(id);
            }else{
//                LocalDateTime to_f = LocalDateTime.parse(to);
//                LocalDateTime from_f = LocalDateTime.parse(from);
                reviews = reviewService.findReviewsForBookByIdAndDate(id, from, to);
                System.out.println("ID: " + id);
                System.out.println("From: " + from);
                System.out.println("To: " + to);
            }

            model.addAttribute("id", id);
            model.addAttribute("book", book);
            model.addAttribute("reviews", reviews);

            return "reviewsBooks";
        } catch(NoSuchBookException exception){
            return "redirect:/books?error=NoBookWithGivenID";
        }
    }

    @PostMapping("/save-review/{id}")
    public String saveReview(@PathVariable Long id,
                             @RequestParam Integer score,
                             @RequestParam String description,
                             @RequestParam LocalDateTime date){

        reviewService.saveReview(id, score, description, date);
        return "redirect:/books/review/{id}";

    }

    @GetMapping("/add-review/{id}")
    public String addBookReview(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        return "addReview";
    }

    @GetMapping("/sales/{id}")
    public String getSales(Model model,
                           @PathVariable(required = false) String id){
//        List<BookSale> bookSales = bookSaleService.findAllSales();

        List<BookSale> bookSales = new ArrayList<>();


        bookSales = bookSaleService.findByBookId(Long.parseLong(id));


        int totalPrice = 0;
        for (BookSale bookSale : bookSales) {
            totalPrice += bookSale.getBook().getBookPrice().getPrice() - bookSale.getBook().getBookPrice().getDiscount();
        }


        model.addAttribute("bookSales", bookSales);
        model.addAttribute("totalPrice", totalPrice);
        return "sales";
    }

    @GetMapping("/sales")
    public String getSales(Model model){
//        List<BookSale> bookSales = bookSaleService.findAllSales();

        List<BookSale> bookSales = new ArrayList<>();


        bookSales = bookSaleService.findAllSales();



        model.addAttribute("bookSales", bookSales);
        return "sales";
    }

}
