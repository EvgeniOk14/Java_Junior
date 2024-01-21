package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    //region Поля
    /**
     * Серверный сокет
     */
    private final ServerSocket serverSocket;
    //endregion


    //region Constructor
    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }
    //endregion


    /** запуск сервера **/
    public void runServer()
    {
        try
        {
            while (!serverSocket.isClosed())
            {
                // Ожидание и принятие подключения от клиента
                Socket socket = serverSocket.accept();

                // Вывод сообщения о подключении нового клиента
                System.out.println("Подключен новый клиент!");

                // Создание объекта для управления клиентом и запуск соответствующего потока
                ClientManager clientManager = new ClientManager(socket);
                Thread thread = new Thread(clientManager);
                thread.start();

            }
        }
        catch (IOException e)
        {
            // Обработка ошибки ввода-вывода и закрытие серверного сокета
            closeSocket();
        }
    }


    /** закрытие сокета **/
    private void closeSocket()
    {
        try
        {
            if (serverSocket != null)
            {
                serverSocket.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
