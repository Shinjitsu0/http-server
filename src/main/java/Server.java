import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int PORT;

    public Server(int PORT){
        this.PORT = PORT;
    }

    public Server listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            int MAX_POOL = 64;
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            ExecutorService threadPool = Executors.newFixedThreadPool (MAX_POOL);
            threadPool.execute(clientHandler);
        }
    }
}