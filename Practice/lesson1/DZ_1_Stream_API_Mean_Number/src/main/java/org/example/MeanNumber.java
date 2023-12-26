package org.example;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MeanNumber
{
    Random random = new Random();
    public List<Integer> fillList()
    {
        return IntStream.range(0, 10).mapToObj(i -> random.nextInt(0,15)).toList();
    }
}



