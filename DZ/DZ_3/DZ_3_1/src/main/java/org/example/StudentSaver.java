package org.example;

import org.example.interfaces.SaveToFile;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class StudentSaver implements SaveToFile<Student, List<Student>>
{
    @Override
    public void saveStudent(Student student) throws Exception
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream("studentFile.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(student);
        }
    }
    @Override
    public void saveListOfStudents(List<Student> students) throws Exception
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream("ListOfStudentsFile.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(students);
        }
    }


}
