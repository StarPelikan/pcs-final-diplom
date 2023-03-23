import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
            System.out.println(engine.search(",банан"));

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    String request = in.readLine();
                    String answer = engine.search(request).toString();
                    out.println(answer);
                }
            }
        } catch (IOException e) {
            System.out.println("Нет возможности поключения");
            e.printStackTrace();
        }
    }
}