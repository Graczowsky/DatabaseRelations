package database.example.DatabaseDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

//    @BeforeEach
    private void init() {
        personRepository.save(new Person( "Damian", "Petrus", new Address("Chorzów","Przyjemna","41-506") ));
        personRepository.save(new Person( "Dawid", "Jamka", new Address("Katowice","Testowa","41-400") ));

    }
    @Test
    void shouldCheckIfAddPerson(){
        //given
        final Address ad = addressRepository.save(new Address("Chorzów", "Przyjemna", "41-506"));
        final Person person = personRepository.save(new Person("Damian", "Petrus"));

        //when
        person.setAddress(ad);
        personRepository.save(person);

        //then
        assertThat(person.getAddress()).isEqualTo(ad);

    }
    @Test
    void shouldCheckIfAddPersonWithAddress() {
        //given
         Person person=personRepository.save(new Person("Katarzyna","Petrus",new Address("Katowice","Rolna","40-500")));
        //when
        //then
        assertThat(personRepository.findAll().size()).isEqualTo(1);
        assertThat(addressRepository.findAll().size()).isEqualTo(1);
    }

}