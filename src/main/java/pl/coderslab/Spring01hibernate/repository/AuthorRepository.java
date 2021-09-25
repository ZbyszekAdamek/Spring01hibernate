package pl.coderslab.Spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.Spring01hibernate.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a where a.email like :email%")
    List<Author>methodToFindAuthorsWithEmail(@Param("email") String email);

    @Query("select a from Author a where a.pesel like :pesel%")
    List<Author>methodToFindAuthorsWithPesel(@Param("pesel") String pesel);

}
