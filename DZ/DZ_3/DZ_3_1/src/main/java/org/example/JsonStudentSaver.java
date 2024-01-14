package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.interfaces.SaveToFile;

import java.io.FileWriter;
import java.util.List;

public class JsonStudentSaver implements SaveToFile<Student, List<Student>>
{

    private static final String STUDENT_FILE_JSON = "studentFile.json";
    private static final String LIST_OF_STUDENT_FILE_JSON = "listOfStudent.json";

    @Override
    public void saveStudent(Student student) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(STUDENT_FILE_JSON))
        {
            objectMapper.writeValue(writer, student);
        }
    }

    @Override
    public void saveListOfStudents(List<Student> students) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(LIST_OF_STUDENT_FILE_JSON))
        {
            objectMapper.writeValue(writer, students);
        }
    }
}

