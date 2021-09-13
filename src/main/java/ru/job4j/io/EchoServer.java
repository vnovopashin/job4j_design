package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс реализует программу для обмена данными
 * с использованием протокола TCP/IP, данный протокол имеет возможность
 * передать данные и подтвердить, что они успешно дошли до получателя.
 * В качестве клиента используется программа cURL (так же можно использовать браузер),
 * сервером является данный класс, он реализован посредством
 * java.net.Socket и позволяет получить сообщение и передать его.
 * При выполнении запроса http://localhost:9000/?msg=Hello, сервер отвечает "Hello",
 * в случае любого другого запроса, например http://localhost:9000/?msg=Java отвечает "What?",
 * в случае запроса http://localhost:9000/?msg=Bye, сервер завершает свою работу.
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    StringBuilder sb = new StringBuilder();

                    String str;
                    while ((str = in.readLine()) != null && !str.isEmpty()) {
                        sb.append(str).append("\r\n");
                    }

                    if (sb.toString().contains("msg=Hello")) {
                        out.write("Hello\r\n\r\n".getBytes());
                    } else if (sb.toString().contains("msg=Exit")) {
                        server.close();
                        break;
                    } else {
                        out.write("What?\r\n\r\n".getBytes());
                    }
                    System.out.println(sb);
                    out.flush();
                }
            }
        }
    }
}
