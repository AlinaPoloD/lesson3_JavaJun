package ru.geekbrains.lesson3.HW;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.geekbrains.lesson3.HW.AppStudent.*;
import static ru.geekbrains.lesson3.HW.AppStudent.FILE_BIN;
import static ru.geekbrains.lesson3.HW.AppStudent.FILE_JSON;
import static ru.geekbrains.lesson3.HW.AppStudent.FILE_XML;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*разработайте класс Student с полями String name, int age, transient double GPA(средний балл) Обеспечте поддержку сериализации для этого класса. Создайте объект класа студени и инициализируйте его данными. Сериализзуйте этот оъект в файл. Десериализуйте его обратно в программу из файла, выведите все поля объекта включая GPA
Выполнить эту задачу используя другие типы сериализаторов в xml и json документы*/


       /* Student student = new Student("Maria", 25, 4.4);
       try(FileOutputStream file = new FileOutputStream("student.bin")) {
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
               objectOutputStream.writeObject(student);
           System.out.println("Сериализация прошла успешно!");

       }
        try(FileInputStream fileInput = new FileInputStream("person.bin")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInput);
            student =  (Student)objectInputStream.readObject();
            System.out.println("Десериализация прошла успешно!");

        }
        System.out.println("Имя: " + student.getName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("Средний балл: " + student.getGPA());*/

        List<Student> students = new ArrayList<>();
        File f = new File(FILE_JSON);

        AppStudent.saveListToFile(FILE_JSON, students);
        AppStudent.saveListToFile(FILE_BIN, students);
        AppStudent.saveListToFile(FILE_XML, students);
        listStudents(students);
        Scanner scanner = new Scanner(System.in);

        while (true) {


            System.out.println("Выберите действие:");
            System.out.println("1. Добавить студента");
            System.out.println("2.Прочитать из файла");
            System.out.println("3. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewStudents(scanner, students);
                    break;
                case "2":
                    System.out.println("Введите название файла: ");
                    String fileName = scanner.nextLine();
                    List<Student> studentsfromFile = AppStudent.loadStudentsFromFile(fileName);
                    listStudents(studentsfromFile);
                    break;
                case "3":
                    saveListToFile(FILE_JSON, students);
                    saveListToFile(FILE_BIN, students);
                    saveListToFile(FILE_XML, students);
                    System.out.println("Список студентов сохранен.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    break;
            }
            //listStudents(students);
        }









    }







}
