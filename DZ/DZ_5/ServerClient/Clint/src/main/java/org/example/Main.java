package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        try
        {

            // Выводим приглашение для ввода имени
            System.out.print("Введите своё имя: ");

            // Считываем введенное имя пользователя
            String name = scanner.nextLine();

            // Получаем локальный IP-адрес машины
            InetAddress address = InetAddress.getLocalHost();

            // Устанавливаем соединение с сервером, используя локальный адрес и порт 5000
            Socket socket = new Socket(address, 5000);

            // Создаем объект Client, который обрабатывает взаимодействие с сервером
            Client client = new Client(socket, name);

            // Получаем IP-адрес удаленной машины
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);

            // Получаем строковое представление удаленного IP-адреса
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);

            // Получаем локальный порт, к которому привязан сокет
            System.out.println("LocalPort:" + socket.getLocalPort());

            // Запускаем метод, который слушает входящие сообщения от сервера
            client.listenForMessage();

            // Запускаем метод, который отправляет сообщения серверу
            client.sendMessage();

        }
        catch (UnknownHostException ex)
        {
            throw new RuntimeException(ex);
        }
        catch (IOException e)
        {
            new RuntimeException(e);
        }
    }
}