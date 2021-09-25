package pl.coderslab.Spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Spring01hibernate.dao.PublisherDao;
import pl.coderslab.Spring01hibernate.entity.Publisher;
import pl.coderslab.Spring01hibernate.repository.PublisherRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PublisherController {

    private final PublisherDao publisherDao;
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherDao publisherDao, PublisherRepository publisherRepository){
        this.publisherDao = publisherDao;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/findByNip")
    @ResponseBody
    String findByNip(){
        Optional<Publisher>publisher = publisherRepository.findByNip(12345);
        return publisher.toString();
    }
    @GetMapping("/findByRegon")
    @ResponseBody
    String findByRegon(){
        Optional<Publisher>publisher = publisherRepository.findByRegon(34555);
        return publisher.toString();
    }

    @RequestMapping("/publisher/persist/{name}")
    @ResponseBody
    public String persist(@PathVariable String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherDao.createPublisher(publisher);
        return "Zapisano wydawce o id: " + publisher.getId();
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String readPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.readPublisher(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/merge/{id}")
    @ResponseBody
    public String merge(@PathVariable("id") long id) {
        Publisher publisher = publisherDao.readPublisher(id);
        publisher.setName("Nowa nazwa");
        publisherDao.update(publisher);
        return "Zaktualizowano wydawce o id " + id;
    }

    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.readPublisher(id);
        publisherDao.delete(publisher);
        return "deleted";
    }
    @RequestMapping("/publisher/all")
    @ResponseBody
    public String findAll(){
        List<Publisher> allPublishers = publisherDao.findAll();
        return allPublishers.stream()
                .map(Publisher::getName)
                .collect(Collectors.joining("<br/>"));
    }
}
