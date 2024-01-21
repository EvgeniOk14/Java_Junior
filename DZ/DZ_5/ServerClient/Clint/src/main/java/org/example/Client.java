package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client
{

    //region Поля
    // Сокет для соединения с сервером
    private final Socket socket;
    // Поток для чтения данных из сокета
    private BufferedReader bufferedReader;
    // Поток для записи данных в сокет
    private BufferedWriter bufferedWriter;
    // Имя клиента
    private final String name;
    //endregion

    //region Constructor
    public Client(Socket socket, String name)
    {
        this.socket = socket;
        this.name = name;
        try
        {
            // Создание потоков для чтения и записи данных через сокет
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            // В случае ошибки при отправке сообщения, закрываем все ресурсы
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    //endregion

    /**
     * Отправить сообщение
     */
    public void sendMessage()
    {

        try
        {
            // Отправляем имя клиента на сервер
            bufferedWriter.write(name); // Эта строка записывает имя клиента (name) в BufferedWriter. BufferedWriter представляет собой буферизированный поток для записи символов в выходной поток. В данном случае, имя клиента записывается в поток для отправки на сервер.
            bufferedWriter.newLine(); //  Эта строка добавляет новую строку в поток. Это важно для того, чтобы сервер мог правильно интерпретировать входящие сообщения и различать их.
            bufferedWriter.flush(); //  Эта строка вызывает метод flush(), который выталкивает буферизированные данные в выходной поток (в данном случае, отправляет данные на сервер). Это гарантирует, что все записанные данные будут немедленно отправлены, а не останутся в буфере.

            // Читаем сообщения с консоли и отправляем на сервер
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) //  Начинается бесконечный цикл, который будет выполняться, пока сокет (канал связи с сервером) подключен. Это гарантирует, что клиент будет ожидать ввода пользователя и отправлять сообщения на сервер, пока соединение активно.
            {
                // Читаем строку с консоли
                String message = scanner.nextLine();
                // Отправляем сообщение на сервер
                bufferedWriter.write(name + ": " + message);
                // ввод пустой строки для различения сообщений
                bufferedWriter.newLine();
                // выталкивание буфферизированного потока на сервер
                bufferedWriter.flush();
            }
        }
        catch (IOException e)
        {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    /**
     * Слушатель для входящих сообщений
     */
    public void listenForMessage()
    {
        // Создание нового потока для асинхронного прослушивания входящих сообщений
        /**  создает новый поток, который начинает выполнение с кода,
         *  определенного в блоке run(). Вызов start() запускает выполнение потока,
         *  вызывая метод run() внутри этого потока. Это делает код внутри run() выполняемым в отдельном потоке,
         *  а не в основном потоке программы.
         **/
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String message; // Переменная для хранения входящего сообщения

                while (socket.isConnected())
                {
                    try
                    {
                        message = bufferedReader.readLine(); // Чтение строки из буферизированного потока ввода
                        System.out.println(message);  // Вывод принятого сообщения в консоль
                    }
                    catch (IOException e)
                    {
                        // В случае ошибки ввода-вывода, закрытие ресурсов и завершение работы потока
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }

        }
        ).start(); // Запуск выполнения потока
    }

    /** закрыть всё **/
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        try
        {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null)
            {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null)
            {
                bufferedWriter.close();
            }
            // Завершаем работу клиентского сокета
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException e)
        {
            // В случае ошибки при закрытии ресурсов, выводим стек трейс ошибки
            e.printStackTrace();
        }
    }

}
