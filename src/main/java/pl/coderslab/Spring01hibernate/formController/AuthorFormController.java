package pl.coderslab.Spring01hibernate.formController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.Spring01hibernate.dao.AuthorDao;
import pl.coderslab.Spring01hibernate.entity.Author;

@Controller
public class AuthorFormController {

    private final AuthorDao authorDao;

    public AuthorFormController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/authorForm/list")
    public String showAll(Model model) {
        model.addAttribute("allAuthors", authorDao.findAll());
        return "author/all";
    }

    @GetMapping("/authorForm/add")
    public String initAddFom(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }

    @PostMapping("/authorForm/add")
    public String persistBook(Author author) {
        authorDao.createAuthor(author);
        return "redirect:/authorForm/list";
    }

    @GetMapping("/authorForm/edit")
    public String prepareEdit(@RequestParam int idToEdit, Model model) {
        model.addAttribute("author", authorDao.findById(idToEdit));
        return "author/form";
    }

    @PostMapping("/authorForm/edit")
    public String merge(Author author) {
        authorDao.update(author);
        return "redirect:/authorForm/list";
    }

    @GetMapping("/authorForm/remove")
    public String prepareRemove(@RequestParam int toRemoveId, Model model) {
        model.addAttribute("author", authorDao.findById(toRemoveId));
        return "author/remove";
    }

    @PostMapping("/authorForm/remove")
    public String remove(@RequestParam String confirmed, @RequestParam int toRemoveId) {
        if ("yes".equals(confirmed)) {
            Author author = authorDao.findById(toRemoveId);
            authorDao.delete(author);
        }
        return "redirect:/authorForm/list";
    }

}
