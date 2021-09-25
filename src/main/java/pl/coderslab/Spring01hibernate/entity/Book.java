package pl.coderslab.Spring01hibernate.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 5)
    private String title;
    @Range(min = 1, max = 10)
    private int rating;
    @Size(max = 600)
    private String description;

    @ManyToOne
    private Category category;
    //pamiętać o dodaniu getterów i setterów do publishera.
    @ManyToOne
    @NotNull
    private Publisher publisher;

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", publisher=" + publisher +
                ", authorList=" + authorList +
                ", pages=" + pages +
                '}';
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany
    @NotEmpty
    private List<Author> authorList = new ArrayList<>();

    @Min(1)
    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }


    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
