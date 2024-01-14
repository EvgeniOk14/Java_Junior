package org.example;

import java.io.Serializable;

public class Student implements Serializable
{
   public String name;
   public int age;
   public transient double GPA;

   @Override
   public String toString()
   {
      return "Student{" +
              "name='" + name + '\'' +
              ", age=" + age +
              ", GPA=" + GPA +
              '}';
   }



   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getAge()
   {
      return age;
   }

   public void setAge(int age)
   {
      this.age = age;
   }

   public double getGPA()
   {
      return GPA;
   }

   public void setGPA(double GPA)
   {
      this.GPA = GPA;
   }

   public Student(String name, int age, double GPA)
   {
      this.name = name;
      this.age = age;
      this.GPA = GPA;
   }
   public Student()
   {
      // default constructor
   }
}
