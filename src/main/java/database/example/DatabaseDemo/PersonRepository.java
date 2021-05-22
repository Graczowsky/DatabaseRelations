package database.example.DatabaseDemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);
}
