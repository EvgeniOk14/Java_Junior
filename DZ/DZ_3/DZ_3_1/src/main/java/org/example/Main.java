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

        /** сериализация объекта класса Student в файл с именем: studentFile **/
        SaveToFile studentSaver = new StudentSaver();
        try
        {
            studentSaver.saveStudent(student);
            System.out.println("Студент был успешно сохранён в файл");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /** дессириализация объекта  **/

        try
        {
            LoadFromFile studentLoader = new StudentLoader();
            Student studentLoad = studentLoader.studentLoad();
            System.out.println("имя студента: " + studentLoad.getName() + "\n" +
                    "возраст студента: " + studentLoad.getAge() + "\n" +
                    "средний балл студента: " + studentLoad.getGPA());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        /** сериализыция списка студентов **/
        try
        {
            studentSaver.saveListOfStudents(students);
            System.out.println("список студентов был успешно сохранён!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /**дессириализация ссписка студентов **/
        try
        {
            LoadFromFile studentLoader = new StudentLoader();
            List<Student> studentListLoder = studentLoader.listOfStudentsLoad();
            System.out.println(studentListLoder.toString());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}