package kit.pano.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author pano
 */
public class NioSocket {

    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9999));
        //设置为非阻塞模式
        server.configureBlocking(false);

        //一直执行
        while (true) {
            Thread.sleep(1000);
            SocketChannel client = server.accept();
            System.out.println(System.currentTimeMillis());
            if (Objects.isNull(client)) {
                System.out.println("null....");
            } else {
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("Client.....Port = " + port);
                clients.add(client);
            }
            System.out.println("Reach Here " + clients.size());

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
            for (SocketChannel channel : clients) {
                int num = channel.read(byteBuffer);
                if (num > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.limit()];
                    byteBuffer.get(bytes);

                    String info = new String(bytes);
                    System.out.println("info = " + info);
                    System.out.println("Client.....Port = " + channel.socket().getPort() + ":" + info);
                    byteBuffer.clear();
                }
            }
        }

    }
}
