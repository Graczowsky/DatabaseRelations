package database.example.DatabaseDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OneToOneBeDirectionalTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Test
    void shouldCheckIfPersonAndAddressExist(){
        //given
        final Person person= new Person( "Damian", "Petrus") ;
        final Address address= new Address("Chorzów","Przyjemna","41-500");

         address.setPerson(person);

         addressRepository.save(address);
         Person foundPerson= personRepository.findById(person.getId()).get();
         assertThat(foundPerson.getAddress().getCity()).isEqualTo("Chorzów");
         Address foundAddress= foundPerson.getAddress();
         assertThat(foundAddress.getPerson().getFirstName()).isEqualTo("Damian");

        //then


    }
}