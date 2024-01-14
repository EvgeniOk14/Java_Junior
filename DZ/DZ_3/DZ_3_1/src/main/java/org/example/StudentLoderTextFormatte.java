package org.example;

import org.example.interfaces.LoadFromFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentLoderTextFormatte implements LoadFromFile
{
    private static final String STUDENT_FILE = "studentFile.txt";
    private static final String LIST_OF_STUDENT_FILE = "listOfStudent.txt";

    @Override
    public Student studentLoad() throws Exception
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE)))
        {
            String line = reader.readLine();
            return studentFromString(line);
        }
    }

    @Override
    public List<Student> listOfStudentsLoad() throws Exception
    {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LIST_OF_STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(studentFromString(line));
            }
        }
        return students;
    }

    private Student studentFromString(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        String name = tokenizer.nextToken().trim();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.startsWith("Age: ")) {
                int age = Integer.parseInt(token.substring(5).trim());
                double GPA = Double.parseDouble(tokenizer.nextToken().substring(5).trim());
                return new Student(name, age, GPA);
            }
        }

        return null; // Вернуть null в случае неудачи
    }

//    private Student studentFromString(String line)
//    {
//        StringTokenizer tokenizer = new StringTokenizer(line, ",");
//        String name = tokenizer.nextToken().trim();
//        int age = Integer.parseInt(tokenizer.nextToken().trim());
//        double GPA = Double.parseDouble(tokenizer.nextToken().trim());
//        return new Student(name, age, GPA);
//    }
}
