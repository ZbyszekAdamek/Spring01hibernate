package pl.coderslab.Spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Spring01hibernate.dao.PersonDao;
import pl.coderslab.Spring01hibernate.entity.Person;
import pl.coderslab.Spring01hibernate.entity.PersonDetails;

@Controller
public class PersonController {
    private final PersonDao personDao;
    public PersonController(PersonDao personDao){
        this.personDao = personDao;
    }

    @RequestMapping("/person/add")
    @ResponseBody
    public String createPerson() {
        Person person = new Person();

        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Zbigniew");
        personDetails.setLastName("Adamek");
        personDetails.setCity("Kraków");
        personDetails.setStreet("Krakowska");
        personDetails.setStreetNumber(2);

        person.setPersonDetails(personDetails);
        person.setLogin("Zbyszek");
        person.setEmail("Zbyszek@gmail.com");
        person.setPassword("tralala");
        personDao.savePerson(person);

        return "Id dodanej osoby:"
                + person.getId();
    }

    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String readPerson(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @RequestMapping("/person/update/{id}/{login}/{password}/{email}")
    @ResponseBody
    public String updatePerson(@PathVariable long id,
                             @PathVariable String login,
                             @PathVariable String password,
                             @PathVariable String email) {

        Person person = personDao.findById(id);
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        personDao.update(person);
        return person.toString();
    }

    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return "deleted";
    }

    @GetMapping(value = "/person/form")
    public String test(Model model){
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @PostMapping("/person/form")
    @ResponseBody
    public String save(Person person){
        personDao.savePerson(person);
        return "Udało się zapisać";
    }
}
