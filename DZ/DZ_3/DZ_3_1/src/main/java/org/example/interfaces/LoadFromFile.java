package org.example.interfaces;

import org.example.Student;

import java.util.List;

public interface LoadFromFile
{
    public Student studentLoad() throws Exception;
    public List<Student> listOfStudentsLoad() throws Exception;
}
