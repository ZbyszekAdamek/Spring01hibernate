package pl.coderslab.Spring01hibernate.dao;


import org.springframework.stereotype.Repository;
import pl.coderslab.Spring01hibernate.entity.Author;
import pl.coderslab.Spring01hibernate.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public void createAuthor(Author author) {
        entityManager.persist(author);
    }

    public Author readAuthor(long id) {
        return entityManager.find(Author.class, id);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }

    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author));
    }
    public List<Author> findAll(){
        Query query = entityManager.createQuery("SELECT a from Author a");
        return query.getResultList();
    }
    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }
}


