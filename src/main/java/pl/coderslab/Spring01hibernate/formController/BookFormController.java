package pl.coderslab.Spring01hibernate.formController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Spring01hibernate.dao.AuthorDao;
import pl.coderslab.Spring01hibernate.dao.BookDao;
import pl.coderslab.Spring01hibernate.dao.PublisherDao;
import pl.coderslab.Spring01hibernate.entity.Author;
import pl.coderslab.Spring01hibernate.entity.Book;
import pl.coderslab.Spring01hibernate.entity.Publisher;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookFormController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;


    public BookFormController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @ModelAttribute("publishers")
    public List<Publisher> allPublishers() {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> allAuthors() {
        return authorDao.findAll();
    }

    @GetMapping("/bookForm/list")
    public String showAll(Model model) {
        model.addAttribute("allBooks", bookDao.findAll());
        return "book/all";
    }

    @GetMapping("/bookForm/add")
    public String initAddFom(Model model) {
        model.addAttribute("book", new Book());
        return "book/form";
    }

    @PostMapping("/bookForm/add")
    public String persistBook(@Valid Book book, BindingResult result) {
        /*if (result.hasErrors()) {
            return "bookForm/add";
        }*/
            bookDao.saveBook(book);
            return "redirect:/bookForm/list";
    }

    @GetMapping("/bookForm/edit")
    public String prepareEdit(@RequestParam int idToEdit, Model model) {
        model.addAttribute("book", bookDao.findById(idToEdit));
        return "book/form";
    }

    @PostMapping("/bookForm/edit")
    public String merge(Book book) {
        bookDao.update(book);
        return "redirect:/bookForm/list";
    }

    @GetMapping("/bookForm/remove")
    public String prepareRemove(@RequestParam int toRemoveId, Model model) {
        model.addAttribute("book", bookDao.findById(toRemoveId));
        return "book/remove";
    }

    @PostMapping("/bookForm/remove")
    public String remove(@RequestParam String confirmed, @RequestParam int toRemoveId) {
        if ("yes".equals(confirmed)) {
            Book book = bookDao.findById(toRemoveId);
            bookDao.delete(book);
        }
        return "redirect:/bookForm/list";
    }


}
