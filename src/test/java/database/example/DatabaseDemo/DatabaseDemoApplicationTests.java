

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
	void checkAssignment() {
		Address address = new Address("Katowice", "Sławka", "40-833");
		Address address2 = new Address("Chorzów", "Sławka", "40-833");
		Address address3 = new Address("Bytom", "Sławka", "40-833");
		Address address4 = new Address("Żywiec", "Sławka", "40-833");
		Address address5 = new Address("Sosnowiec", "Sławka", "40-833");
		Person person = new Person("Dawid", "Jamka");
		Person person2 = new Person("Damian", "Petrus");
		Person person3 = new Person("Marcin", "Butora");
		Person person4 = new Person("Adam", "Dominik");
		Person person5 = new Person("Łukasz", "Gryziewicz");
		Person person6 = new Person("Patryk", "Żak");
		Person person7 = new Person("Jeremiasz", "Górniak");
		address.addPerson(person);
		address.addPerson(person4);
		address2.addPerson(person2);
		address4.addPerson(person3);
		address5.addPerson(person6);
		address5.addPerson(person7);
		address3.addPerson(person5);
		addressRepository.save(address);
		addressRepository.save(address2);
		addressRepository.save(address3);
		addressRepository.save(address4);
		addressRepository.save(address5);

		Optional<Address> foundCity = addressRepository.findByCity("Katowice");
		Long countFound = personRepository.countByAddress(foundCity.get());

		assertThat(countFound).isEqualTo(2);

	}
}
