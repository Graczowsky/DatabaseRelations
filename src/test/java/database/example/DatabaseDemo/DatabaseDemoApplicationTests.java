package database.example.DatabaseDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DatabaseDemoApplicationTests {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PersonRepository personRepository;
	@Test
	void contextLoads() {
	}
//	@BeforeEach
//	private void init(){
//		personRepository.save(new Person("Dawid","Jamka",
//				new Address("Katowice","Sławka","40-833")));
//		personRepository.save(new Person("Damian","Petrus",
//				new Address("Chorzów","Przyjemna","40-533")));
//	}

	@Test
	void oneToOneWorks(){
		final Address ad = new Address("Chorzów","Przyjemna","40-533");
		final Person person = new Person("Dawid","Jamka");
		person.setAddress(ad);
		personRepository.save(person);
		List<Person> personList = personRepository.findAll();
		assertThat(personList).isNotEmpty();
		assertThat(person.getAddress()).isEqualTo(ad);
	}

	@Test
	void checkIfAddPersonWithAddress(){
		personRepository.save(new Person("Dawid","Jamka",
				new Address("Katowice","Sławka","40-833")));
		List<Person> personList = personRepository.findAll();
		List<Address> addressList = addressRepository.findAll();

		assertThat(personList.size()).isEqualTo(1);
		assertThat(addressList.size()).isEqualTo(1);
	}

	@Test
	void checkIfAddressWasChanged(){
		Person person = personRepository.save(new Person("Dawid","Jamka",
				new Address("Katowice","Sławka","40-833")));
		Address ad = new Address("Chorzów","Przyjemna","40-533");
		person.setAddress(ad);
		personRepository.save(person);
		Person foundPerson = personRepository.findById(person.getId()).get();

		assertThat(foundPerson.getAddress()).isEqualTo(ad);

	}


}
