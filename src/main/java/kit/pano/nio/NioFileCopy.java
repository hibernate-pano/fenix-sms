package kit.pano.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * 通过Channel复制文件
 *
 * @author pano
 */
public class NioFileCopy {

    public static void main(String[] args) throws IOException {
        String fileIn = "/Users/pano/Pictures/4o5dy7.jpg";
        String fileOut1 = "/Users/pano/Pictures/out1.jpg";
        String fileOut2 = "/Users/pano/Pictures/out2.jpg";
        String fileOut3 = "/Users/pano/Pictures/out3.jpg";
        String fileOut4 = "/Users/pano/Pictures/out4.jpg";
        copyFileThroughStream(fileIn, fileOut1);
        copyFileThroughFileChannel(fileIn, fileOut2);
        copyFileThroughFileChannelAndBuffer(fileIn, fileOut3);
        copyFileThroughAddressMap(fileIn, fileOut4);
    }


    /**
     * 通过普通输入输出流复制文件
     */
    private static void copyFileThroughStream(String fileIn, String fileOut) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileIn);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
        int len;
        byte[] buffer = new byte[4096];
        while ((len = Objects.requireNonNull(fileInputStream).read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, len);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 通过FileChannel复制文件
     */
    private static void copyFileThroughFileChannel(String fileIn, String fileOut) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileIn);
        FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    /**
     * 通过FileChannel复制文件
     */
    private static void copyFileThroughFileChannelAndBuffer(String fileIn, String fileOut) throws IOException {
        FileChannel inChannel = new FileInputStream(fileIn).getChannel();
        FileChannel outChannel = new FileOutputStream(fileOut).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (inChannel.read(byteBuffer) > 0) {
            //转换到读模式
            byteBuffer.flip();
            //写入outChannel
            outChannel.write(byteBuffer);
            //清楚缓存，切换到写模式
            byteBuffer.clear();
        }
        inChannel.close();
        outChannel.close();
    }

    /**
     * 使用内存映射文件的方式实现文件复制的功能(直接操作缓冲区)：
     */
    private static void copyFileThroughAddressMap(String fileIn, String fileOut) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get(fileIn), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(fileOut), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

        //设置数组长度读取buffer数据
        byte[] dst = new byte[inBuffer.limit()];
        inBuffer.get(dst);
        outBuffer.put(dst);

        inChannel.close();
        outBuffer.clear();
    }
}
