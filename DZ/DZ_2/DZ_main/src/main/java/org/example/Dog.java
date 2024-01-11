package org.example;

public class Dog extends Animal
{
    String bread;

    public Dog(String name, int age, String bread) {
        super(name, age);
        this.bread = bread;
    }

    @Override
    public void makeSound()
    {
        System.out.println("Гав");
    }
}
