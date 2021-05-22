package database.example.DatabaseDemo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String street;
    private String postalCode;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> personList= new ArrayList<>();

    public Address() {
    }

    public Address(String city, String street, String postalCode) {
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }
    public void addPerson(Person person){
        personList.add(person);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Person> getPerson() {
        return personList;
    }

    public void setPerson(Person person) {
        this.personList = personList;
    }

    public void setPerson(List<Person> person) {
        this.personList = personList;
    }
}
