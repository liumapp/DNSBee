package com.liumapp.DNSQueen.worker.tcp;

import com.liumapp.DNSQueen.worker.process.WokerEar;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liumapp on 7/14/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */

public class TCPSocketMonitor extends Thread {

    private Logger log = Logger.getLogger(this.getClass());

    private InetAddress addr;

    private int port;

    private ServerSocket serverSocket;

    @Autowired
    private WokerEar wokerEar;

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    public TCPSocketMonitor(String host, int port) {
        super();
        this.port = port;
        try {
            this.addr = Inet4Address.getByName(host);
            serverSocket = new ServerSocket(port, 128, addr);
        } catch (IOException e) {
            System.err.println("Start tcp monitor failed, check whether port " + port + " is taken. Maybe an instance is already running");
            log.error("Start tcp monitor failed, check whether port " + port + " is taken. Maybe an instance is already running",e);
            System.exit(-1);
        }
        this.setDaemon(true);
    }

    @Override
    public void run() {

        log.info("Starting TCP socket monitor on address "
                + getAddressAndPort());

        while (true) {
            try {

                final Socket socket = serverSocket.accept();

                log.debug("TCP connection from "
                        + socket.getRemoteSocketAddress());
                if (!socket.getInetAddress().getHostAddress()
                        .equals("127.0.0.1")) {
                    continue;
                }
                executorService.execute(new TCPConnection(socket, wokerEar));

            } catch (SocketException e) {

                // This is usally thrown on shutdown
                log.debug("SocketException thrown from TCP socket on address "
                        + getAddressAndPort() + ", " + e);
                break;

            } catch (IOException e) {

                log.info("IOException thrown by TCP socket on address "
                        + getAddressAndPort() + ", " + e);
            }
        }
        log.info("TCP socket monitor on address " + getAddressAndPort()
                + " shutdown");
    }

    public InetAddress getAddr() {

        return addr;
    }

    public int getPort() {

        return port;
    }

    public ServerSocket getServerSocket() {

        return serverSocket;
    }

    public void closeSocket() throws IOException {

        log.info("Closing TCP socket monitor on address " + getAddressAndPort()
                + "...");

        this.serverSocket.close();
    }

    public String getAddressAndPort() {

        return addr.getHostAddress() + ":" + port;
    }
}
