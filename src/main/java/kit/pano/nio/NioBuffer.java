package kit.pano.nio;

import java.nio.ByteBuffer;

/**
 * 熟悉NIO Buffer的基本结构和操作
 *
 * @author pano
 */
public class NioBuffer {

    public static void main(String[] args) {
        //初始化 byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("origin -> capacity = " + byteBuffer.capacity());
        System.out.println("origin -> limit = " + byteBuffer.limit());
        System.out.println("origin -> position = " + byteBuffer.position());
        System.out.println("origin -> mark = " + byteBuffer.mark());

        //执行put()，写入数据
        String name = "Hibernate Pano";
        byteBuffer.put(name.getBytes());
        System.out.println("put -> capacity = " + byteBuffer.capacity());
        System.out.println("put -> limit = " + byteBuffer.limit());
        System.out.println("put -> position = " + byteBuffer.position());
        System.out.println("put -> mark = " + byteBuffer.mark());

        //执行filp()，转换成读模式
        byteBuffer.flip();
        System.out.println("flip -> capacity = " + byteBuffer.capacity());
        System.out.println("flip -> limit = " + byteBuffer.limit());
        System.out.println("flip -> position = " + byteBuffer.position());
        System.out.println("flip -> mark = " + byteBuffer.mark());

        //设置limit长度的数据，读取数据
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("get -> String = " + new String(bytes));

        System.out.println("get -> capacity = " + byteBuffer.capacity());
        System.out.println("get -> limit = " + byteBuffer.limit());
        System.out.println("get -> position = " + byteBuffer.position());
        System.out.println("get -> mark = " + byteBuffer.mark());

        //清空缓冲区，转换成写模式 ---> 数据没有真正被清空，只是被遗忘掉了
        byteBuffer.clear();
        System.out.println("clear -> capacity = " + byteBuffer.capacity());
        System.out.println("clear -> limit = " + byteBuffer.limit());
        System.out.println("clear -> position = " + byteBuffer.position());
        System.out.println("clear -> mark = " + byteBuffer.mark());
    }
}
