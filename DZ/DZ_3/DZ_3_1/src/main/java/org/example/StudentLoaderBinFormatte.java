package org.example;

import org.example.interfaces.LoadFromFile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class StudentLoaderBinFormatte implements LoadFromFile
{
    @Override
    public Student studentLoad() throws Exception
    {
        try
                (
            FileInputStream fileInputStream = new FileInputStream("studentFile.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                )
        {
            return (Student) objectInputStream.readObject();
        }
    }
    @Override
    public List<Student> listOfStudentsLoad() throws Exception
    {
        try
                (
                        FileInputStream fileInputStream = new FileInputStream("ListOfStudentsFile.bin");
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                )
        {
            return (List<Student>) objectInputStream.readObject();
        }
    }
}
