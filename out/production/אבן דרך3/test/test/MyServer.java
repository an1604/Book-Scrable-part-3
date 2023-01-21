//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    private final int port;
    private final ClientHandler ch;
    private volatile boolean stop;

    public MyServer(int port, ClientHandler ch) {
        this.port = port;
        this.ch = ch;
    }

    public void start() {
        this.setStop(false);
        (new Thread(() -> {
            this.startServer();
        })).start();
    }

    private void startServer() {
        ServerSocket server = null;

        try {
            server = new ServerSocket(this.getPort());
            server.setSoTimeout(1000);

            while(!this.isStop()) {
                try {
                    Socket client = server.accept();
                    this.ch.handleClient(client.getInputStream(), client.getOutputStream());
                    this.ch.close();
                    client.close();
                } catch (SocketTimeoutException var3) {
                }
            }

            server.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public void close() {
        this.setStop(true);
    }

    public int getPort() {
        return this.port;
    }

    public ClientHandler getCh() {
        return this.ch;
    }

    public boolean isStop() {
        return this.stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
