package database.example.DatabaseDemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class DatabaseDemoApplicationTests {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Test
	void checkAssignment(){
		Address address = new Address("Katowice", "Sławka", "40-833");
		Person person = new Person("Dawid", "Jamka");
		Person person2 = new Person("Damian", "Petrus");
		Person person3 = new Person("Marcin", "Butora");
		address.addPerson(person);
		address.addPerson(person2);
		address.addPerson(person3);
		addressRepository.save(address);
		assertThat(address.getPerson().contains(person)).isTrue();


		Address foundAddress = addressRepository.findById(address.getId()).get();
		assertThat(foundAddress.getPerson().contains(person));
		assertThat(foundAddress.getPerson().contains(person2));
		assertThat(foundAddress.getPerson().contains(person3));


		Person foundPerson = foundAddress.getPerson().stream()
				.filter(f -> f.getFirstName().equals("Dawid")&&f.getLastName().equals("Jamka"))
				.findFirst().get();
		foundPerson.setFirstName("Daniel");
		addressRepository.save(address);
		assertThat(personRepository.findPersonByFirstNameAndLastName("Dawid", "Jamka")).isEmpty();
		assertThat(personRepository.findPersonByFirstNameAndLastName("Daniel", "Jamka")).isPresent();



	}
}