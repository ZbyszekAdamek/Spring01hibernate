package pl.coderslab.Spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Spring01hibernate.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {



}
