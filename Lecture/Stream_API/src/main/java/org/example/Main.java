package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("Привет", "мир", "!", "Я", "родился", "!");

//       List<String> list1 =  list.stream().filter(str -> str.length() > 4).collect(Collectors.toList());
//       list1.stream().forEach(System.out::println);

        /** фильтрует елементы списка по заданному условию .filter(str -> str.length() > 4) **/
        list.stream().filter(str -> str.length() > 4).forEach(System.out::println);
        System.out.println("-------------------------------------------");

        /** фильтрует елементы списка по заданному условию .filter(str -> str.length() > 4).filter(str -> str.contains("о")) **/
        list.stream().filter(str -> str.length() > 4).filter(str -> str.contains("о")).forEach(System.out::println);
        System.out.println("--------------------------------------------");

        /** Преобразует одно число в другое map(chislo -> chislo * chislo) т.е. возводит его в квадрат **/
        Arrays.asList(1,2,3,4,5).stream().map(chislo -> chislo * chislo).forEach(System.out::println);
        System.out.println("--------------------------------------------");

        /** Преобразует одно число в другое map(str -> "число: " + str + ". Квадрат числа - " + str*str) **/
        Arrays.asList(1,2,3,4,5).stream().map(str -> "число: " + str + ". Квадрат числа - " + str*str).forEach(System.out::println);
        System.out.println("--------------------------------------------");

        /** сортирует по возрастанию  .sorted() **/
        Arrays.asList(5,2,7,3,1).stream().sorted().forEach(System.out::println);
        System.out.println("--------------------------------------------");

        /** сортирует в обратном порядке .sorted(Comparator.reverseOrder()) **/
        Arrays.asList(1,2,3,4,5).stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("--------------------------------------------");

        /** убирает повторяющиеся значения .distinct() и сортирует по возрастанию  .sorted() **/
        Arrays.asList(1,10,0,5,7,5,10,7).stream().sorted().distinct().forEach(System.out::println);
        System.out.println("--------------------------------------------");

        /** убирает повторяющиеся значения .distinct() и сортирует в обратном порядке .sorted(Comparator.reverseOrder()) **/
        Arrays.asList(1,10,0,5,7,5,10,7).stream().sorted(Comparator.reverseOrder()).distinct().forEach(System.out::println);
        System.out.println("--------------------------------------------");

        /** находит первый элемент .findFirst().get() в отсортированном в обратном порядке списке **/
        System.out.println( "Первый элемент: " + Arrays.asList(1,10,0,5,7,5,10,7).stream().sorted(Comparator.reverseOrder()).distinct().findFirst().get());
    }
}