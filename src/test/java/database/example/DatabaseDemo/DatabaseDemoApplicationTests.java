package database.example.DatabaseDemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DatabaseDemoApplicationTests {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Test
	void checkIfDirectionalFlowIsWorking(){
		final Address ad = new Address("Chorzów","Przyjemna","40-533");
		final Person person = new Person("Dawid","Jamka");
		person.setAddress(ad);
		ad.setPerson(person);
		personRepository.save(person);
		addressRepository.save(ad);
		Person foundPerson = personRepository.findById(person.getId()).get();
		assertThat(foundPerson.getAddress().getCity()).isEqualTo("Chorzów");
		Address foundAddress = foundPerson.getAddress();
		assertThat(foundAddress.getPerson().getFirstName()).isEqualTo("Dawid");


	}

}
