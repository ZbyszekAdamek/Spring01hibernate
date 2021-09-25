package pl.coderslab.Spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.Spring01hibernate.entity.Author;
import pl.coderslab.Spring01hibernate.entity.Book;
import pl.coderslab.Spring01hibernate.entity.Category;
import pl.coderslab.Spring01hibernate.entity.Publisher;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.title = :givenTitle")
    Optional<Book>methodToFindBookByTitle(@Param("givenTitle") String title);

    @Query("select b from Book b where b.category=:givenCategory")
    List<Book>methodToFindBookByGivenCategory(@Param("givenCategory")Category category);

    @Query("select b from Book b where b.rating between :firstParam and :secondParam ")
    List<Book>methodToFindBooksByRating(@Param("firstParam")int rating, @Param("secondParam")int rating1);

    @Query("select b from Book b where b.publisher = :Publisher")
    List<Book>methodToFindBooksByPublisher(@Param("Publisher") Publisher publisher);

    @Query(value = "select * from books where category_id = :Category order by title limit 1", nativeQuery = true)
    Optional<Book>methodToFindFirstBookFromCategory(@Param("Category")Category category);

    Optional<Book>findByTitleIgnoringCase(String title);

    List<Book>findAllByCategory(Category category);

    List<Book>findAllByCategory_Id(long id);

    List<Book>findAllByAuthorList(Author author);

    List<Book>findAllByPublisher_Id(long id);

    List<Book>findAllByRating(int rating);

    Optional<Book>findFirstByTitle(Category category);
}
