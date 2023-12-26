package org.example;

public class Main
{
    public static void main(String[] args)
    {
//        PlainInterface plainInterface = new PlainInterface()
//        {
//            @Override
//            public String action(int x, int y)
//            {
//                return String.valueOf(x + y);
//            }
//        };

        //PlainInterface plainInterface = (x,y) -> x+y;
        PlainInterface plainInterface = Integer:: sum;
        System.out.println(plainInterface.action(5,3));

        System.out.println("---------------------------------------------------------");
        //PlainInterface plainInterface1 = (x,y) -> Integer.compare(x, y);
        PlainInterface plainInterface1 = Integer::compare;
        System.out.println(plainInterface1.action(1, 8));

        System.out.println("---------------------------------------------------------");

        PlainInterface plainInterface2 = Integer::divideUnsigned;
        System.out.println(plainInterface2.action(8,4));

        System.out.println("---------------------------------------------------------");



    }
}