package com.qpm.learn.jdknio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * NIO DEMO
 *
 */
public class ServerDemo {

    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
    private Selector selector;


    public ServerDemo() throws IOException {
        // 开启服务端
        /**
         * {@link java.nio.channels.spi.SelectorProvider 内部使用的是这个单例模式提供的 {@link ServerSocketChannel}
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 绑定端口
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("listening on port 8080");

        // 构建一个 Selector
        this.selector = Selector.open();

        // 向 Selector 监听自己感兴趣的时间
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException {
        new ServerDemo().go();
    }

    private void go() throws IOException {
        while (selector.select() > 0) {
            // selector 会主动去轮询每一个 Channel 是否有可用的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                // 新连接
                if (selectionKey.isAcceptable()) {
                    System.out.println("isAcceptable");
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                    // 注册新的channel
                    SocketChannel socketChannel = server.accept();
                    if (socketChannel == null) {
                        continue;
                    }

                    socketChannel.configureBlocking(false);

                    // 向 Selector 注册自己感兴趣的 Key
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    buffer.put("hi, new channel".getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);
                }


                // 假如获得了可读消息
                if (selectionKey.isReadable()) {
                    System.out.println("isReadable");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    readBuffer.clear();
                    socketChannel.read(readBuffer);     // 从 Channel 中读取字节数据
                    readBuffer.flip();

                    String receiveData = Charset.forName("UTF-8").decode(readBuffer).toString();
                    System.out.println("receiveData:" + receiveData);

                    // 把读到的数据绑定到 key 中
                    selectionKey.attach("server message echo:" + receiveData);
                }

                if (selectionKey.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    String message = (String) selectionKey.attachment();
                    if (message == null) {
                        continue;
                    }

                    selectionKey.attach(null);

                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();

                    while (writeBuffer.hasRemaining()) {
                        socketChannel.write(writeBuffer);
                    }
                }
            }
        }
    }

}
