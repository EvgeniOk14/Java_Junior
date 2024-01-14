package org.example;

import org.example.interfaces.LoadFromFile;
import org.example.interfaces.SaveToFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**   Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
        Обеспечьте поддержку сериализации для этого класса.
        Создайте объект класса Student и инициализируйте его данными.
        Сериализуйте этот объект в файл.
        Десериализуйте объект обратно в программу из файла.
        Выведите все поля объекта, включая GPA, и ответьте на вопрос,
        почему значение GPA не было сохранено/восстановлено.  **/

public class Main
{
    public static void main(String[] args) throws Exception
    {
        /** создание студентов и их список **/
        List<Student> students = new ArrayList<>();
        Student student = new Student("Николай", 23, 4.5);
        Student student2 = new Student("Антон", 22, 5.0);
        Student student3 = new Student("Алексей", 21, 4.0);
        Student student4 = new Student("Михаил", 24, 4.2);
        Student student5 = new Student("Дмитрий", 20, 4.3);
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        /** сериализация объекта класса Student в файл с именем: studentFile.bin в формате bin **/
        SaveToFile studentSaver = new StudentSaverBinFormatte();
        try
        {
            studentSaver.saveStudent(student);
            System.out.println("1. Студент был успешно сохранён в файл studentFile.bin формата bin");
            System.out.println("-------------------------------------------------------------------------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /** дессириализация объекта клааса Student из файла studentFile.bin bin формата **/

        try
        {
            LoadFromFile studentLoader = new StudentLoaderBinFormatte();
            Student studentLoad = studentLoader.studentLoad();
            System.out.println("2. Студент был успешно считан из файла studentFile.bin формата bin:");
            System.out.println("имя студента: " + studentLoad.getName() + "\n" +
                    "возраст студента: " + studentLoad.getAge() + "\n" +
                    "средний балл студента: " + studentLoad.getGPA());
            System.out.println("-------------------------------------------------------------------------------------");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }



        /** сериализыция списка студентов в файла ListOfStudentsFile.bin в формате bin**/
        try
        {
            studentSaver.saveListOfStudents(students);
            System.out.println("3. список студентов был успешно сохранён в файл ListOfStudentsFile.bin в формате bin!");
            System.out.println("----------------------------------------------------------------------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /**дессириализация ссписка студентов из файла ListOfStudentsFile.bin в формате bin **/
        try
        {
            LoadFromFile studentLoader = new StudentLoaderBinFormatte();
            List<Student> studentListLoder = studentLoader.listOfStudentsLoad();
            System.out.println("4. Дессириализация спика студентов из файла ListOfStudentsFile.bin bin формата:\n ");
            System.out.println(studentListLoder.toString());
            System.out.println("----------------------------------------------------------------------------------");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }



        /**---------------------------сохранение/чтение из текстового формата--------------------------------------**/



        /** сериализация объекта класса Student в файл с именем: studentFile.txt в формате txt  **/

        try {
            SaveToFile studentSaveTextFormatte = new StudentSaverTextFormatte();
            studentSaveTextFormatte.saveStudent(student);
            System.out.println("5. сериализация студента в текстовый формат прошла успешно! Создан файл studentFile.txt\n");
            System.out.println("----------------------------------------------------------------------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        /** дессириализация объекта класса Student из файла с именем: studentFile.txt в формате txt **/
        try {
            LoadFromFile studentLoadTextFormatte = new StudentLoderTextFormatte();
            Student studentLoad = studentLoadTextFormatte.studentLoad();
            System.out.println("6. дессириализация студента из файла с именем: studentFile.txt текстового формата: \n");
            System.out.println("имя студента: " + studentLoad.getName() + "\n" +
                    "возраст студента: " + studentLoad.getAge() + "\n" +
                    "средний балл студента: " + studentLoad.getGPA());
            System.out.println("----------------------------------------------------------------------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        /** сериализыция списка студентов В файл ListOfStudentsFile.txt в формате txt**/

        try {
            SaveToFile listOfStudentsSaveTextFormatte = new StudentSaverTextFormatte();
            listOfStudentsSaveTextFormatte.saveListOfStudents(students);
            System.out.println("7. Сериализация студентов в файл тектового формата прошла успешно!");
            System.out.println("----------------------------------------------------------------------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        /**дессириализация ссписка студентов из файла ListOfStudentsFile.txt в формате txt **/
        try
        {
            LoadFromFile listOfStudentsTextFormatte = new StudentLoderTextFormatte();
            List<Student> studentList = listOfStudentsTextFormatte.listOfStudentsLoad();
            System.out.println("8. Чтение из файла ListOfStudentsFile.txt списка студентов в формате txt");
            System.out.println("Список студентов из текстового формата: " + studentList.toString());
            System.out.println("----------------------------------------------------------------------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}