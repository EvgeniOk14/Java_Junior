package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            // Создание серверного сокета, прослушивающего порт 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            // Создание объекта сервера, передача ему серверного сокета
            Server server = new Server(serverSocket);
            // Запуск сервера
            server.runServer();
        }
        catch (IOException e)
        {
            // Обработка ошибки ввода-вывода и выбрасывание RuntimeException в случае ошибки
            throw new RuntimeException(e);
        }
    }

}
