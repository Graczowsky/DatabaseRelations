package database.example.DatabaseDemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findAll();
}
