package org.example;
// Напишите программу, которая использует Stream API для обработки списка чисел.
// Программа должна вывести на экран среднее значение всех четных чисел в списке.
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        MeanNumber meanNumber = new MeanNumber();
        List<Integer> integerList = meanNumber.fillList();
        //integerList.forEach(System.out::print);
        System.out.println("Случайный числовой ряд: ");
        integerList.forEach(number -> System.out.print(number + " "));

        /** получение среднего значения всех четных чисел в списке */
        double average = integerList.stream().filter(number -> number%2 == 0).mapToInt(Integer::valueOf).average().orElse(0.0);

        if (average != 0.0)
        {
            System.out.println("\nСреднее значение четных чисел: " + average);
        }
        else
        {
            throw new RuntimeException("Нет четных чисел в списке! ");
        }
    }
}