package ru.vsu.netcracker.config;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.FileInputStream;
import java.util.Properties;

public class Configurator {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private static Configurator instance;

    private Configurator(){}

    public Class getSorter(){
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        String sorter = "";


        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            sorter = prop.getProperty("sorter");
        } catch (Exception e) {

            e.printStackTrace();
        }

        Class name = null;
        try {
            name = Class.forName("ru.vsu.netcracker.sorts.sortsImpl." + sorter + "Sort");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static Configurator getInstance(){
        if (instance == null){
            instance =  new Configurator();
        }
        return instance;
    }
}
