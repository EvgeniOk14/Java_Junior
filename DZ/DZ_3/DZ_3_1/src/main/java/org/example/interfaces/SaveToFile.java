package org.example.interfaces;

import org.example.Student;

import java.util.List;

public interface SaveToFile<T extends Student, L extends List<Student>>
{
    void saveStudent(T t) throws Exception;
    void saveListOfStudents(L l) throws Exception;
}
