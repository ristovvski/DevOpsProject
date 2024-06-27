package mk.finki.ukim.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public AuthorController(AuthorService authorService, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

//    WebContext context =  new WebContext(webExchange);
//        context.setVariable("isbn", req.getParameter("isbn"));
//    getServletContext().setAttribute("isbn", req.getParameter("isbn"));
//        context.setVariable("listAuthors", authorService.listAuthors());

    @GetMapping()
    public String getAuthorsPage(HttpServletRequest request, Model model){
        model.addAttribute("isbn", request.getParameter("isbn"));
        request.getServletContext().setAttribute("isbn", request.getParameter("isbn"));
        model.addAttribute("listAuthors", authorService.listAuthors());

        return "authorList";
    }
}
