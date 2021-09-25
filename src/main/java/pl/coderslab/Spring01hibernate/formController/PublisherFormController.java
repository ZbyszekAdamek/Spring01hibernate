package pl.coderslab.Spring01hibernate.formController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.Spring01hibernate.dao.PublisherDao;
import pl.coderslab.Spring01hibernate.entity.Publisher;

@Controller
public class PublisherFormController {

    private final PublisherDao publisherDao;

    public PublisherFormController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping("/publisherForm/list")
    public String showAll(Model model) {
        model.addAttribute("allPublishers", publisherDao.findAll());
        return "publisher/all";
    }

    @GetMapping("/publisherForm/add")
    public String initAddFom(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/form";
    }

    @PostMapping("/publisherForm/add")
    public String persistBook(Publisher publisher) {
        publisherDao.createPublisher(publisher);
        return "redirect:/publisherForm/list";
    }

    @GetMapping("/publisherForm/edit")
    public String prepareEdit(@RequestParam int idToEdit, Model model) {
        model.addAttribute("publisher", publisherDao.readPublisher(idToEdit));
        return "publisher/form";
    }

    @PostMapping("/publisherForm/edit")
    public String merge(Publisher publisher) {
        publisherDao.update(publisher);
        return "redirect:/publisherForm/list";
    }

    @GetMapping("/publisherForm/remove")
    public String prepareRemove(@RequestParam int toRemoveId, Model model) {
        model.addAttribute("publisher", publisherDao.readPublisher(toRemoveId));
        return "publisher/remove";
    }

    @PostMapping("/publisherForm/remove")
    public String remove(@RequestParam String confirmed, @RequestParam int toRemoveId) {
        if ("yes".equals(confirmed)) {
            Publisher author = publisherDao.readPublisher(toRemoveId);
            publisherDao.delete(author);
        }
        return "redirect:/publisherForm/list";
    }


}
