package kit.pano.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pano
 */
public class BioServer {

    /**
     * 1. 创建线程池
     * 2.
     */

    public static void main(String[] args) throws IOException {

        ExecutorService service = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端启动啦! ");

        for (int i = 0; i < 5; i++) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端启动! ");
            service.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            clientHandler(clientSocket);
                        }
                    });
        }
        service.shutdown();
    }

    /**
     * 编写一个handler方法，和客户端通讯
     */
    public static void clientHandler(Socket clientSocket) {
        System.out.println("线程信息 =" + Thread.currentThread().getId() + Thread.currentThread().getName());
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("收到消息---" + line);
            }
        } catch (IOException e) {
            // ... handle IO exception
        }
    }
}
