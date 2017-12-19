package ru.vsu.netcracker.sorts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.vsu.netcracker.comparators.IComparator;

import static org.junit.Assert.*;

public class SortsTest {
    private int n;
    private Integer[] expectedArray;
    private Integer[] actualArray;

    @Before
    public void setUp() throws Exception {
        n = 10;
        expectedArray = new Integer[n];
        actualArray = new Integer[n];
    }

    @After
    public void tearDown() {
        actualArray = null;
        expectedArray = null;
    }

    @Test
    public void bubbleSort() {
        for(int i = 0; i < n; i++){
            expectedArray[i] = i + 1;
            actualArray[i] = n - i;
        }

        BubbleSort bubbleSort = new BubbleSort();

        actualArray = bubbleSort.sort(actualArray, new IComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int i1 = (int)o1;
                int i2 = (int)o2;
                if (i1 > i2){
                    return 1;
                } else if (i1 < i2){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void quickSort(){
        for(int i = 0; i < n; i++){
            expectedArray[i] = i + 1;
            actualArray[i] = n - i;
        }

        QuickSort quickSort = new QuickSort();
        actualArray = quickSort.sort(actualArray, new IComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                    int i1 = (int)o1;
                    int i2 = (int)o2;
                    if (i1 > i2){
                        return 1;
                    } else if (i1 < i2){
                        return -1;
                    } else {
                        return 0;
                    }
                }

        });
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void insertionSort(){
        for(int i = 0; i < n; i++){
            expectedArray[i] = i + 1;
            actualArray[i] = n - i;
        }

        InsertionSort insertionSort = new InsertionSort();
        actualArray = insertionSort.sort(actualArray, new IComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int i1 = (int)o1;
                int i2 = (int)o2;
                if (i1 > i2){
                    return 1;
                } else if (i1 < i2){
                    return -1;
                } else {
                    return 0;
                }
            }

        });
        assertArrayEquals(expectedArray, actualArray);
    }
}