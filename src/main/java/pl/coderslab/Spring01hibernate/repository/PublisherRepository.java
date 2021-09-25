package pl.coderslab.Spring01hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Spring01hibernate.entity.Publisher;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

Optional<Publisher>findByRegon(int regon);

Optional<Publisher>findByNip(int nip);

}
