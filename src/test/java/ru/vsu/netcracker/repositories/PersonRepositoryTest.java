package ru.vsu.netcracker.repositories;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.vsu.netcracker.comparators.comparatorImpl.ComparePersonById;
import ru.vsu.netcracker.models.Person;
import ru.vsu.netcracker.sorts.BubbleSort;
import ru.vsu.netcracker.sorts.InsertionSort;
import ru.vsu.netcracker.sorts.QuickSort;

import static org.junit.Assert.*;

public class PersonRepositoryTest {
    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;
    private Person person5;
    private Person person6;

    @Before
    public void setUp() throws Exception {
        person1 = new Person("Ivanov", new LocalDate("1997-10-12"));
        person2 = new Person("Petrov", new LocalDate("1997-06-08"));
        person3 = new Person("Filatov", new LocalDate("1972-10-03"));
        person4 = new Person("Alekseeva", new LocalDate("2004-05-29"));
        person5 = new Person("Anokhin", new LocalDate("1996-07-20"));
        person6 = new Person("Smirnov", new LocalDate("1996-09-14"));
    }

    @After
    public void tearDown() throws Exception {
        person1 = null;
        person2 = null;
        person3 = null;
        person4 = null;
        person5 = null;
        person6 = null;
    }

    @Test
    public void add() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(3);
        actualPersonRepository.add(person1);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person4);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person6);

        Person[] expectedPersonRepository = new Person[6];
        expectedPersonRepository[0] = person1;
        expectedPersonRepository[1] = person2;
        expectedPersonRepository[2] = person3;
        expectedPersonRepository[3] = person4;
        expectedPersonRepository[4] = person5;
        expectedPersonRepository[5] = person6;

        assertArrayEquals(expectedPersonRepository, actualPersonRepository.getRepository());
    }

    @Test
    public void getCurrentSize() throws Exception {
        PersonRepository personRepository = new PersonRepository(3);
        personRepository.add(person1);
        personRepository.add(person2);
        personRepository.add(person3);
        personRepository.add(person4);
        personRepository.add(person5);
        personRepository.add(person6);

        int expectedSize = 6;
        int actualSize = personRepository.getCurrentSize();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void deletePerson() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(3);
        actualPersonRepository.add(person1);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person4);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person6);

        actualPersonRepository.delete(person6);
        actualPersonRepository.delete(person4);
        actualPersonRepository.delete(person1);

        PersonRepository expectedPersonRepository = new PersonRepository(6);
        expectedPersonRepository.add(person2);
        expectedPersonRepository.add(person3);
        expectedPersonRepository.add(person5);

        Object[] actPers = actualPersonRepository.getRepository();
        Object[] expPers = expectedPersonRepository.getRepository();

        assertArrayEquals(expPers, actPers);
    }

    @Test
    public void deletePersonFalse() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(3);
        actualPersonRepository.add(person1);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person4);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person6);
        boolean del = actualPersonRepository.delete(new Person());

        assertEquals(false, del);
    }

    @Test
    public void sortByFio() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(6);
        actualPersonRepository.add(person1);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person4);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person6);
        actualPersonRepository.sortByFio(new QuickSort());

        PersonRepository expectedPersonRepository = new PersonRepository(6);
        expectedPersonRepository.add(person4);
        expectedPersonRepository.add(person5);
        expectedPersonRepository.add(person3);
        expectedPersonRepository.add(person1);
        expectedPersonRepository.add(person2);
        expectedPersonRepository.add(person6);

        Object[] actPers = actualPersonRepository.getRepository();
        Object[] expPers = expectedPersonRepository.getRepository();

        assertArrayEquals(expPers, actPers);
    }

    @Test
    public void sortByDateOfBirth() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(6);
        actualPersonRepository.add(person1);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person4);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person6);
        actualPersonRepository.sortByDateOfBirth(new BubbleSort());

        PersonRepository expectedPersonRepository = new PersonRepository(6);
        expectedPersonRepository.add(person3);
        expectedPersonRepository.add(person5);
        expectedPersonRepository.add(person6);
        expectedPersonRepository.add(person2);
        expectedPersonRepository.add(person1);
        expectedPersonRepository.add(person4);

        Object[] actPers = actualPersonRepository.getRepository();
        Object[] expPers = expectedPersonRepository.getRepository();

        assertArrayEquals(expPers, actPers);
    }

    @Test
    public void sortById() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(6);
        actualPersonRepository.add(person6);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person4);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person1);
        actualPersonRepository.sortById(new InsertionSort());

        PersonRepository expectedPersonRepository = new PersonRepository(6);
        expectedPersonRepository.add(person1);
        expectedPersonRepository.add(person2);
        expectedPersonRepository.add(person3);
        expectedPersonRepository.add(person4);
        expectedPersonRepository.add(person5);
        expectedPersonRepository.add(person6);

        Object[] actPers = actualPersonRepository.getRepository();
        Object[] expPers = expectedPersonRepository.getRepository();

        assertArrayEquals(expPers, actPers);
    }

    @Test
    public void getPersonById() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(6);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person6);
        actualPersonRepository.add(person1);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person4);
        int index = person5.getId();

        Person actPers = actualPersonRepository.getPersonById(index);
        Person expPers = person5;

        assertEquals(expPers, actPers);
    }

    @Test
    public void getPersonByIdNull() throws Exception {
        PersonRepository actualPersonRepository = new PersonRepository(6);
        actualPersonRepository.add(person5);
        actualPersonRepository.add(person2);
        actualPersonRepository.add(person6);
        actualPersonRepository.add(person1);
        actualPersonRepository.add(person3);
        actualPersonRepository.add(person4);

        Person actPers = actualPersonRepository.getPersonById(100);
        Person expPers = null;

        assertEquals(expPers, actPers);
    }

}