package kit.pano.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author pano
 */
public class NioServer {

    private ServerSocketChannel server = null;
    private Selector selector = null;
    int port = 9999;

    public void initServer() throws IOException {
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(port));
        server.configureBlocking(false);

        selector = Selector.open();
        //服务端注册到Selector
        server.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws IOException {
        initServer();

    }


}
