package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** Создайте абстрактный класс "Animal" с полями "name" и "age".
    Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
    Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
    Выведите на экран информацию о каждом объекте.
    Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует. **/
public class Main
{
    public static void main(String[] args)
    {
        /**  Создаем массив объектов типа "Animal" **/
        Animal[] animals = new Animal[2];
        animals[0] = new Dog("Бобик", 3, "овчарка");
        animals[1] = new Cat("Мурзик", 5, "серый");

        /** Используем Reflection API для вывода информации и вызова метода **/
        for (Animal animal: animals) {
            Class<?> clazz = animal.getClass();

            /** Вывод информации о полях **/
            System.out.println("Информация о классе: " + clazz.getSimpleName() + ":");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields)
            {
                try
                {
                    field.setAccessible(true);
                    System.out.println("уникальное поле " + field.getName() +": " +  field.get(animal));
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            /** Вызов метода makeSound() **/

            try {
                System.out.println("Метод makesound  может делать звук: ");
                Method makeSoundMethod = clazz.getDeclaredMethod("makeSound");
                makeSoundMethod.setAccessible(true);
                makeSoundMethod.invoke(animal);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("----------------------------------------------------------------------");
    }
}