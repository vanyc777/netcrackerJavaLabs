package ru.vsu.netcracker.repositories;

import ru.vsu.netcracker.checker.IChecker;
import ru.vsu.netcracker.checker.checkerImpl.ColorCarChecker;
import ru.vsu.netcracker.checker.checkerImpl.EnginePowerChecker;
import ru.vsu.netcracker.checker.checkerImpl.ModelCarChecker;
import ru.vsu.netcracker.comparators.comparatorImpl.CompareCarById;
import ru.vsu.netcracker.comparators.IComparator;
import ru.vsu.netcracker.models.Car;
import ru.vsu.netcracker.sorts.ISort;


/**
 * Class for storing instances of {@link Car} with fields:
 * @author Ivan Chelyadinov
 * @version 1.0
 */
public class CarRepository extends Repository<Car> {



    /**
     * @see CarRepository#CarRepository(int)
     */
    public CarRepository(){
        maxSize = 1;
        list = new Car[1];
        tail = -1;
    }

    /**
     * Constructor
     * @param size
     */
    public CarRepository(int size) {
        maxSize = size;
        list = new Car[maxSize];
        tail = -1;
    }

    /**
     * method for sorting list by id with sort from config
     *@see CarRepository#sort(ISort, IComparator)
     */
    public void sortById(ISort Sort){

            sort(Sort, new CompareCarById());

    }

    /**
     * method for sorting list by model
     * @param Sort sorting method
     *@see CarRepository#sort(ISort, IComparator)
     */
    public void sortByModel(ISort Sort){

            sort(Sort, (o1, o2) -> {
                return ((Car)o1).getModel().compareTo(((Car)o2).getModel());
            });

    }

    /**
     * method for sorting list by engine power
     * @param Sort sorting method
     *@see CarRepository#sort(ISort, IComparator)
     */
    public void sortByEnginePower(ISort Sort) {

            sort(Sort, (o1, o2) -> {
               return ((Car)o1).getEnginePower().compareTo(((Car)o2).getEnginePower()) ;
            });

    }

    /**
     * @param id
     * @return car if it exist in list, else null
     */
    public Car getCarById(int id) {

        for (int i = 0; i <= tail; i++){
            if (list[i].getId() == id){
                return list[i];
            }
        }
        return null;
    }

    /**
     *@param checker
     * @param obj  - parametre for compare
     * @return CarRepository with car which satisfies the comparison conditions
     */
    private CarRepository search(IChecker checker, Object obj){
        CarRepository result = new CarRepository();

        for (Car car: list) {
            if (checker.check(car, obj)){
                result.add(car);
            }
        }
        return result;
    }

    /**
     * @param model  - parametre for compare
     * @return CarRepository with car which has equal model to parameter
     */
    public CarRepository searchByModel(String model) {
        return search(new ModelCarChecker(), model);
    }

    /**
     * @param color  - parametre for compare
     * @return CarRepository with car which has equal color to parameter
     */
    public CarRepository searchByColor(String color) {
        return search(new ColorCarChecker(), color);
    }

    /**
     * @param enginePower  - parametre for compare
     * @return CarRepository with car which has equal engine power to parameter
     */
    public CarRepository searchByEnginePower(double enginePower) {
        return search(new EnginePowerChecker(), enginePower);
    }

    @Override
    public void clear() {
        for(int i = 0; i < tail; i++){
            list[i] = null;
        }
    }
}
