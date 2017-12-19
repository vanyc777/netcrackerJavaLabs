package ru.vsu.netcracker.repositories;

import org.joda.time.LocalDate;
import ru.vsu.netcracker.checker.IChecker;
import ru.vsu.netcracker.checker.checkerImpl.AgeChecker;
import ru.vsu.netcracker.checker.checkerImpl.DateOfBirthChacker;
import ru.vsu.netcracker.checker.checkerImpl.FioPersonChecker;
import ru.vsu.netcracker.comparators.comparatorImpl.CompareByDateOfBirth;
import ru.vsu.netcracker.comparators.comparatorImpl.CompareByFIO;
import ru.vsu.netcracker.comparators.comparatorImpl.ComparePersonById;
import ru.vsu.netcracker.comparators.IComparator;

import ru.vsu.netcracker.config.Configurator;
import ru.vsu.netcracker.models.Person;
import ru.vsu.netcracker.sorts.*;

/**
 * Class for storing instances of {@link Person} with fields:
 * @author Ivan Chelyadinov
 * @version 1.0
 */
public class PersonRepository extends Repository<Person>{



//Class Sort= Configurator.getInstance().getSorter();
    /**
     * @see PersonRepository#PersonRepository(int)
     */
    public PersonRepository(){
        maxSize = 1;
        list = new Person[1];
        tail = -1;
    }

    /**
     * Constructor
     * @param size
     */
    public PersonRepository(int size) {
        maxSize = size;
        list = new Person[maxSize];
        tail = -1;
    }


    /**
     * method for sorting list by FIO
     * @param Sort sorting method
     *@see PersonRepository#sort(ISort, IComparator)
     */
    public void sortByFio(ISort Sort){

            sort(Sort, new CompareByFIO());

    }

    /**
     * method for sorting list by BirthDate with sort from config
     * @param Sort sorting method
     *@see PersonRepository#sort(ISort, IComparator)
     */
    public void sortByDateOfBirth(ISort Sort){

        sort(Sort, new CompareByDateOfBirth());

    }


    /**
     * method for sorting list by Id with sort from config
     * @param Sort sorting method
     *@see PersonRepository#sort(ISort, IComparator)
     */
    public void sortById(ISort Sort){

        sort(Sort, new ComparePersonById());

    }

    /**
     * @param id
     * @return person if it exist in list, else null
     */
    public Person getPersonById(int id) {

        for (int i = 0; i <= tail; i++){
            if (list[i].getId() == id){
                return list[i];
            }
        }
        return null;
    }

    /**
     * @param checker
     * @param obj  - parametre for compare
     * @return PersonRepository with persons who satisfies the comparison conditions
     */
    private PersonRepository search(IChecker checker, Object obj){
        PersonRepository result = new PersonRepository();

        for (Person person: list) {
            if (checker.check(person, obj)){
                result.add(person);
            }
        }
        return result;
    }

    /**
     * @param fio  - parametre for compare
     * @return PersonRepository with persons who has equal last name to fio
     */
    public PersonRepository searchByFio(String fio) {
        return search(new FioPersonChecker(), fio);
    }

    /**
     * @param dateOfBirth  - parametre for compare
     * @return PersonRepository with persons who has equal date of birth to parametre
     */
    public PersonRepository searchByDateOfBirth(LocalDate dateOfBirth) {
        return search(new DateOfBirthChacker(), dateOfBirth);
    }

    /**
     * @param age  - parametre for compare
     * @return PersonRepository with persons who has equal age to parametre
     */
    public PersonRepository searchByAge(Integer age) {
        return search(new AgeChecker(), age);
    }

    @Override
    public void clear() {
        for (int i = 0; i < tail; i++)
            list[i] = null;
    }
}
