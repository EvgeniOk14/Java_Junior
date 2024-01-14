package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.interfaces.LoadFromFile;
import java.io.FileReader;
import java.util.List;

public class JsonStudentLoader implements LoadFromFile
{

    private static final String STUDENT_FILE_JSON = "studentFile.json";
    private static final String LIST_OF_STUDENT_FILE_JSON = "listOfStudent.json";

    @Override
    public Student studentLoad() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileReader reader = new FileReader(STUDENT_FILE_JSON))
        {
            return objectMapper.readValue(reader, Student.class);
        }
    }

    @Override
    public List<Student> listOfStudentsLoad() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class);
        try (FileReader reader = new FileReader(LIST_OF_STUDENT_FILE_JSON))
        {
            return objectMapper.readValue(reader, listType);
        }
    }
}

