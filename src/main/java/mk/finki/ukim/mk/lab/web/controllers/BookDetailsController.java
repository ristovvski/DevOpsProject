package mk.finki.ukim.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Controller
@RequestMapping("/book-details")
public class BookDetailsController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookDetailsController(AuthorService authorService, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping
    public String BookDetailsPage(HttpServletRequest request, Model model){
        bookService.addAuthorToBook(Long.parseLong(request.getParameter("authorId")), (String) request.getServletContext().getAttribute("isbn"));

        Book tmpBook = bookService.findBookByIsbn((String) request.getServletContext().getAttribute("isbn"));

        model.addAttribute("bookDetails", bookService.findBookByIsbn((String) request.getServletContext().getAttribute("isbn")));

        return "bookDetails";
    }


}
