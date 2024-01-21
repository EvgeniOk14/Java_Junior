package org.example;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable
{

    //region Поля
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static ArrayList<ClientManager> clients = new ArrayList<>();
    //endregion


    //region Constructor
    public ClientManager(Socket socket)
    {
        try
        {
            // Здесь просто устанавливается соединение для данного объекта ClientManager с клиентским сокетом, который передается конструктору.
            this.socket = socket;

            // Создается BufferedWriter, связанный с выходным потоком данного сокета. Этот BufferedWriter используется для отправки сообщений клиенту.
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Создается BufferedReader, связанный с входным потоком данного сокета. Этот BufferedReader используется для чтения сообщений от клиента.
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Происходит чтение строки из BufferedReader, которая ожидается от клиента.
            name = bufferedReader.readLine(); // первым клиент вводит имя, поэтому первое что считываеться это имя клиента
            clients.add(this); // Добавление текущего объекта ClientManager в коллекцию клиентов

            // Выводится сообщение в консоль о том, что клиент подключился к чату, используя свое имя.
            System.out.println(name + " подключился к чату.");

            //Вызывается метод broadcastMessage(), который, видимо, отправляет сообщение всем клиентам в чате о том, что новый клиент подключился.
            broadcastMessage("Server: " + name + " подключился к чату."); // Отправка сообщения всем клиентам
        }
        catch (IOException e)
        {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    //endregion


    /** это метод который будет выполняться в в классе clientManager **/
    @Override
    public void run()
    {
        String massageFromClient;

        while (socket.isConnected())
        {
            try
            {
                // Чтение данных
                massageFromClient = bufferedReader.readLine();
                if (massageFromClient == null)
                {
                    // для  macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                // Отправка данных всем слушателям
                broadcastMessage(massageFromClient);
            }
            catch (IOException e)
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }

    }


    /**
     * Отправка сообщения всем слушателям
     *
     * @param message сообщение
     */
    private void broadcastMessage(String message)
    {

        if (message.startsWith("private")) // если сообщение будет начинаться с ключевого слова private
        {
            String[] partsOfMessage = message.split(" ", 3); // разделяем сообщение на три части через пробелы
            if (partsOfMessage.length == 3) // проверка: действительно ли сообщение разбито на три части
            {
                String keyWords = partsOfMessage[0];
                String privateClientName = partsOfMessage[1]; // определяем имя пользователя
                String privateMessage = partsOfMessage[2]; // определяем само сообщение

                for (ClientManager client : clients)
                {
                    try
                    {
                        if (client.name.equalsIgnoreCase(privateClientName)) // если имя клиента совпадает с именем из приватного сообщения
                        {
                           client.bufferedWriter.write( keyWords +  " сообщение от клиенту: " + privateClientName + privateMessage);
                           client.bufferedWriter.newLine();
                           client.bufferedWriter.flush();
                           break;
                        }
                    }
                    catch (IOException e)
                    {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }
        else
        {

            for (ClientManager client : clients) // обычная рассылка
            {
                try
                {
                    if (!client.name.equals(name) && message != null)
                    {
                        client.bufferedWriter.write(message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                }
                catch (IOException e)
                {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }




    /**
     * Удаление клиента из коллекции
     */
    private void removeClient()
    {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }





    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        // Удаление клиента из коллекции
        removeClient();
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
            // Закрытие соединения с клиентским сокетом
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
