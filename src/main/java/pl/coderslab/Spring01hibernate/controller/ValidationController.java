package pl.coderslab.Spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Spring01hibernate.entity.Book;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

@Controller
public class ValidationController {

    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }


    @GetMapping("/validate")
    public String validate(Model model){
        Book book = new Book();
        book.setTitle("qwe");
        book.setRating(15);
        book.setDescription("blablabla");
        book.setPages(0);
        Set<ConstraintViolation<Book>> validationResult = validator.validate(book);
        /*if(!validationResult.isEmpty()){
            for(ConstraintViolation<Book>singleError:validationResult){
                System.out.println("BŁAD " + singleError.getPropertyPath() + " " + singleError.getMessage());
            }
            return "Encja jest niepoprawna";
        }
        else{
            return "Encja jest poprawna";
        }*/
        model.addAttribute("book", book);
        model.addAttribute("allErrors", validationResult);
        return "validation";
    }
}
