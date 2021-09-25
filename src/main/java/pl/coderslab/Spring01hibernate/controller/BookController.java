package pl.coderslab.Spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Spring01hibernate.dao.AuthorDao;
import pl.coderslab.Spring01hibernate.dao.BookDao;
import pl.coderslab.Spring01hibernate.dao.PublisherDao;
import pl.coderslab.Spring01hibernate.entity.Author;
import pl.coderslab.Spring01hibernate.entity.Book;
import pl.coderslab.Spring01hibernate.entity.Category;
import pl.coderslab.Spring01hibernate.entity.Publisher;
import pl.coderslab.Spring01hibernate.repository.BookRepository;
import pl.coderslab.Spring01hibernate.repository.CategoryRepository;
import pl.coderslab.Spring01hibernate.repository.PublisherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, BookRepository bookRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/findByCategory")
    @ResponseBody
    public String findByCategory(){
        Category category = categoryRepository.getById(2L);
        List<Book>books = bookRepository.methodToFindBookByGivenCategory(category);
        return books.stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }

    @GetMapping("/findByPublisher")
    @ResponseBody
    public String findByPublisher(){
        Publisher publisher = publisherRepository.getById(2L);
        List<Book>books = bookRepository.methodToFindBooksByPublisher(publisher);
        return books.stream()
                .map(Book::toString)
                .collect(Collectors.joining("<br />"));
    }
    @GetMapping("/findFirstByCategory")
    @ResponseBody
    public String findByFirstCategory(){
        Category category = categoryRepository.getById(2L);
        Optional<Book>book = bookRepository.methodToFindFirstBookFromCategory(category);
        return book.map(Book::toString).orElse("Not found");
    }

    @GetMapping("/findByTitle")
    @ResponseBody
    String findByTitle(){
        Optional<Book> book = bookRepository.methodToFindBookByTitle("Zorro");
        return book.map(Book::toString).orElse("Not found");
    }
    @GetMapping("/findByRating")
    @ResponseBody
    String findByRating(){
        List<Book>books = bookRepository.findAllByRating(1);
        return books.toString();
    }

    @GetMapping("/findBetweenRating")
    @ResponseBody
    String findBetweenRating(){
        List<Book>books = bookRepository.methodToFindBooksByRating(1,3);
        return books.toString();
    }
/*    @GetMapping("/findByCategory")
    @ResponseBody
    String findByCategory(){
        Category category = new Category();
        Optional<Book> book = bookRepository.findFirstByTitle(category);
        return book.toString();
    }*/

    @GetMapping("/findByAuthor")
    @ResponseBody
    String findByAuthor(){
        Author author = authorDao.readAuthor(1);
        List<Book>books = bookRepository.findAllByAuthorList(author);
        return books.toString();
    }

    @GetMapping("/findByPublisherId")
    @ResponseBody
    String findByPublisherId(){
        List<Book>books = bookRepository.findAllByPublisher_Id(1);
        return books.toString();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Jest sukces";
    }

    @RequestMapping("/book/add")
    @ResponseBody
    public String add() {
        Publisher publisher = new Publisher();
        publisher.setName("EGMONT");
        publisherDao.createPublisher(publisher);
        List<Author>authors = new ArrayList<>();
        authors.add(authorDao.readAuthor(1));
        authors.add(authorDao.readAuthor(2));

        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setDescription("Książka o programowaniu w języku Java");
        book.setRating(10);
        book.setAuthorList(authors);
        book.setPublisher(publisher);

        bookDao.saveBook(book);
        return "Id dodanej książki to:"
                + book.getId();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/merge/{id}")
    @ResponseBody
    public String merge(@PathVariable("id") long id) {
        Book book = bookDao.findById(id);
        book.setTitle("Zaktualizowany tytual");
        bookDao.update(book);
        return "Zaktualizowano ksiazke o id " + id;
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }

    @RequestMapping("/book/all")
    @ResponseBody
    public String findAll(){
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br/>"));
    }
    @RequestMapping("/book/rating/{rating}")
    @ResponseBody
    public String findByRating(@PathVariable int rating){
        List<Book>allBooksByRating = bookDao.findAllByRating(rating);
        return allBooksByRating.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br/>"));
    }
    @RequestMapping("/book/publishers")
    @ResponseBody
    public String findAllWithPublisher(){
        List<Book>allBooksWithPublisher = bookDao.findAllWithPublisher();
        return allBooksWithPublisher.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br/>"));
    }
    @RequestMapping("/book/publisher/{publisher}")
    @ResponseBody
    public String findByPublisher(@PathVariable Publisher publisher){
        List<Book>allBooksByPublisher = bookDao.findAllByPublisher(publisher);
        return allBooksByPublisher.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("<br/>"));
    }
}
