package ru.geekbrains.lesson3.HW;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppStudent {

    public static final String FILE_JSON = "students.json";
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_XML = "students.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final XmlMapper xmlMapper = (XmlMapper) new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static void addNewStudents(Scanner scanner, List<Student> students) {
        System.out.println("Введите имя студента:");
        String name = scanner.nextLine();
        System.out.println("Введите возраст студента:");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите средний балл студента:");
        double GPA = scanner.nextDouble();
        scanner.nextLine();
        students.add(new Student(name, age, GPA));



        saveListToFile(FILE_JSON, students);
        saveListToFile(FILE_BIN, students);
        saveListToFile(FILE_XML, students);
        System.out.println("Новый студент добавлен.");
    }

    public static void saveListToFile(String fileName, List<Student> students) {
        try {
            if (fileName.endsWith(".json")) {

                objectMapper.writeValue(new File(fileName), students);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(students);
                }
            } else if (fileName.endsWith(".xml")) {

                xmlMapper.writeValue(new File(fileName), students);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudentsFromFile(String fileName) {
        List<Student> students = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    students = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));


                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        students = (List<Student>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    students = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return students;
    }


    public static void listStudents(List<Student> students) {
       System.out.println("Список студентов:");
        for (Student student : students) {
            System.out.println(student);
        }
    }



}
