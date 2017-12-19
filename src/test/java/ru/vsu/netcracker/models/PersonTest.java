package ru.vsu.netcracker.models;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    Person person;
    @Before
    public void setUp(){
        Person person = new Person("Ivanov", new LocalDate("1997-12-21"));
    }
    @After
    public void tearsDown(){
        person=null;

    }
    @Test
    public void getAge() throws Exception {
        int expectedAge = 19;
        int age = person.getAge();

        assertEquals(expectedAge, age);
    }

    @Test
    public void getAge1() throws Exception {
        int expectedAge = 20;
        int age = person.getAge();

        assertEquals(expectedAge, age);
    }
}