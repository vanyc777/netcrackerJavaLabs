package ru.vsu.netcracker.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.vsu.netcracker.models.Car;
import ru.vsu.netcracker.sorts.BubbleSort;
import ru.vsu.netcracker.sorts.InsertionSort;
import ru.vsu.netcracker.sorts.QuickSort;

import static org.junit.Assert.*;

public class CarRepositoryTest {
    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;
    private Car car5;
    private Car car6;

    @Before
    public void setUp() throws Exception {
        car1 = new Car("volvo", "red", 100);
        car2 = new Car("mersedes", "black", 150);
        car3 = new Car("lada", "white", 83);
        car4 = new Car("toyota", "green", 90);
        car5 = new Car("volvo", "white", 80);
    }

    @After
    public void tearDown() throws Exception {
        car1 = null;
        car2 = null;
        car3 = null;
        car4 = null;
        car5 = null;
    }

    @Test
    public void add(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);

        Car[] arr = new Car[5];
        arr[0] = car1;
        arr[1] = car2;
        arr[2] = car3;
        arr[3] = car4;
        arr[4] = car5;

        assertArrayEquals(arr, carRepository.getRepository());
    }

    @Test
    public void delete(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);
        //int ind = car2.getId();
        carRepository.delete(car2);

        Car[] arr = new Car[5];
        arr[0] = car1;
        arr[1] = car3;
        arr[2] = car4;
        arr[3] = car5;

        assertArrayEquals(arr, carRepository.getRepository());
    }

    @Test
    public void deleteFalse(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);

        boolean bl = carRepository.delete(new Car());

        assertEquals(false, bl);
    }

    @Test
    public void sortById(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car5);
        carRepository.add(car4);
        carRepository.add(car3);
        carRepository.add(car2);
        carRepository.add(car1);
        carRepository.sortById(new BubbleSort());

        Car[] arr = new Car[5];
        arr[0] = car1;
        arr[1] = car2;
        arr[2] = car3;
        arr[3] = car4;
        arr[4] = car5;

        assertArrayEquals(arr, carRepository.getRepository());
    }

    @Test
    public void sortByModel(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car5);
        carRepository.add(car4);
        carRepository.add(car3);
        carRepository.add(car2);
        carRepository.add(car1);
        carRepository.sortByModel(new InsertionSort());

        Car[] arr = new Car[5];
        arr[0] = car3;
        arr[1] = car2;
        arr[2] = car4;
        arr[3] = car5;
        arr[4] = car1;

        assertArrayEquals(arr, carRepository.getRepository());
    }

    @Test
    public void sortByEnginePower(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);
        carRepository.sortByEnginePower(new BubbleSort()
        );

        Car[] arr = new Car[5];
        arr[0] = car5;
        arr[1] = car3;
        arr[2] = car4;
        arr[3] = car1;
        arr[4] = car2;

        assertArrayEquals(arr, carRepository.getRepository());
    }

    @Test
    public void getCarById(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);
        int index = car2.getId();

        Car actualCar = carRepository.getCarById(index);

        assertEquals(car2, actualCar);
    }

    @Test
    public void getCarByIdNull(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);

        Car actualCar = carRepository.getCarById(1000);

        assertEquals(null, actualCar);
    }

    @Test
    public void searchByModel(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);

        String model = "volvo";
        CarRepository actaul = carRepository.searchByModel(model);

        Car[] arr = new Car[2];
        arr[0] = car1;
        arr[1] = car5;

        assertArrayEquals(arr, actaul.getRepository());
    }

    @Test
    public void searchByColor(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);

        String color = "white";
        CarRepository actaul = carRepository.searchByColor(color);

        Car[] expected = new Car[2];
        expected[0] = car3;
        expected[1] = car5;

        assertArrayEquals(expected, actaul.getRepository());
    }

    @Test
    public void searchByEnginePower(){
        CarRepository carRepository = new CarRepository(5);
        carRepository.add(car1);
        carRepository.add(car2);
        carRepository.add(car3);
        carRepository.add(car4);
        carRepository.add(car5);

        double enginePower = 100;
        CarRepository actaul = carRepository.searchByEnginePower(enginePower);

        Car[] expected = new Car[1];
        expected[0] = car1;

        assertArrayEquals(expected, actaul.getRepository());
    }

}