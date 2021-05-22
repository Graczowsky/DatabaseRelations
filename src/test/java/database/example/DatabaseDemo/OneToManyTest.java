package database.example.DatabaseDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OneToManyTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Test
    void shouldCheckIfAddressCanHaveManyPeople(){
        //given

        final Address address= new Address("Chorzów","Przyjemna","41-500");
         Person person1= new Person( "Damian", "Petrus") ;
         Person person2= new Person( "Katarzyna", "Petrus") ;
         Person person3= new Person( "Wojtek", "Petrus") ;
         Person person4= new Person( "Jan", "Petrus") ;

        address.addPerson(person1);
        address.addPerson(person2);
        address.addPerson(person3);
        address.addPerson(person4);

        addressRepository.save(address);

        assertThat(address.getPerson().contains(person1)).isTrue();
        assertThat(address.getPerson().contains(person2)).isTrue();
        assertThat(address.getPerson().contains(person3)).isTrue();
        assertThat(address.getPerson().contains(person4)).isTrue();

        //then


    }
}