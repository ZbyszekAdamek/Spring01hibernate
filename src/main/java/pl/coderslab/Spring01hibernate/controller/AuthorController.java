package pl.coderslab.Spring01hibernate.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Spring01hibernate.dao.AuthorDao;
import pl.coderslab.Spring01hibernate.entity.Author;
import pl.coderslab.Spring01hibernate.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorController {

    private final AuthorDao authorDao;
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorDao authorDao, AuthorRepository authorRepository){
        this.authorDao = authorDao;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/emailAuthors")
    @ResponseBody
    public String findByEmailFormat(){
        List<Author>authors = authorRepository.methodToFindAuthorsWithEmail("Jan");
        return authors.stream()
                .map(Author::toString)
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/peselAuthors")
    @ResponseBody
    public String findByPeselFormat(){
        List<Author>authors = authorRepository.methodToFindAuthorsWithPesel("83");
        return authors.stream()
                .map(Author::toString)
                .collect(Collectors.joining("<br />"));
    }

    @RequestMapping("/author/persist/{firstName}/{lastName}")
    @ResponseBody
    public String persist(@PathVariable String firstName, @PathVariable String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.createAuthor(author);
        return "Zapisano autora o id: " + author.getId();
    }

    @RequestMapping("/author/findById/{id}")
    @ResponseBody
    public String findById(@PathVariable("id") long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @RequestMapping("/author/merge/{id}")
    @ResponseBody
    public String merge(@PathVariable("id") long id) {
        Author author = authorDao.findById(id);
        author.setFirstName("Nowe imie");
        authorDao.update(author);
        return "Zaktualizowano autora o id " + id;
    }

    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.readAuthor(id);
        authorDao.delete(author);
        return "deleted";
    }
    @RequestMapping("/author/all")
    @ResponseBody
    public String findAll(){
        List<Author> allAuthors = authorDao.findAll();
        return allAuthors.stream()
                .map(Author::getLastName)
                .collect(Collectors.joining("<br/>"));
    }
}
