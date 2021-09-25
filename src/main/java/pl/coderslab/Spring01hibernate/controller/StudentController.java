package pl.coderslab.Spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Spring01hibernate.entity.Student;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }

    @ModelAttribute("languages")
    public List<String> languages() {
        return Arrays.asList("Java", "PHP", "Python", "Ruby");
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("Programowanie", "Muzyka", "Sport", "Inne");
    }

    @GetMapping(value = "/student/form")
    public String form(Model model){
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    @PostMapping("/student/form")
    @ResponseBody
    public String save(Student student){
        return "Dane studenta: "
                + "Imię: " + student.getFirstName()
                + "Nazwisko: " + student.getLastName()
                + "Płeć: " + student.getGender()
                + "Kraj pochodzenia: " + student.getCountry()
                + "obsługiwane języki programowania: " + student.getProgrammingSkills()
                + "notatki: " + student.getNotes()
                + "hobby: " + student.getHobbies()
                + "zgoda na otrzymywanie newslettera: " + student.isMailingList();
    }


}
