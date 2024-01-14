package org.example;

import org.example.interfaces.SaveToFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class StudentSaverTextFormatte implements SaveToFile<Student, List<Student>>
{
    private static final String STUDENT_FILE = "studentFile.txt";
    private static final String LIST_OF_STUDENT_FILE = "ListOfStudent.txt";

    @Override
    public void saveStudent(Student student) throws Exception
    {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE)))
        {
            writer.write(studentToString(student));
        }
    }


    @Override
    public void saveListOfStudents(List<Student> students) throws Exception
    {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIST_OF_STUDENT_FILE)))
        {
            for (Student student: students)
            {
                writer.write(studentToString(student));
                writer.newLine(); // добавление новой строки между студентами
            }
        }
    }

    private String studentToString(Student student) {
        return String.format("Name: %s, Age: %d, GPA: %.2f", student.getName(), student.getAge(), student.getGPA());
    }
}
