package org.example;

import org.example.interfaces.SaveToFile;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class StudentSaverBinFormatte implements SaveToFile<Student, List<Student>>
{
    @Override
    public void saveStudent(Student student) throws Exception
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream("studentFile.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(student);
        }
    }
    @Override
    public void saveListOfStudents(List<Student> students) throws Exception
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream("ListOfStudentsFile.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(students);
        }
    }


}
