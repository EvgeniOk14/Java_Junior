package org.example;

import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<User> userList = Arrays.asList(
                new User("Антон", 25),
                new User("Николай", 30),
                new User("Василий", 40),
                new User("Михаил", 45),
                new User("Александр", 20)
        );

        /** печатаем спсок пользователей **/
        userList.stream().forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------");

        /** печатаем спсок пользователей и уменьшаем им возраст на 5 **/
        userList.stream().map(user -> new User(user.name, user.age - 5)).forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------");

        /** печатаем спсок пользователей и уменьшаем им возраст на 5 и выводем только тех у кого возраст меньше либо равен 25 **/
        userList.stream().map(user -> new User(user.name, user.age - 5)).filter(user -> user.age <= 25).forEach(System.out::println);
    }
}