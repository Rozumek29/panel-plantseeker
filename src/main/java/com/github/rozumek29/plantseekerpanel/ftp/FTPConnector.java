package com.github.rozumek29.plantseekerpanel.ftp;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.PrintWriter;

@Scope("singleton")
public class FTPConnector {

    protected static String host = "serwer1727017.home.pl";
    protected static String username = "kacper.wyrozumialski1@zspwrzesnia.pl";
    protected static String password = "VK6xW8EYyrKj";
    protected static String path = "/plants/database_img/";
    private static FTPClient client;


    private static final Logger logger = LoggerFactory.getLogger(FTPClient.class);

    private static FTPConnector instance = null;

    private FTPConnector() {
        client = new FTPClient();
        try {
            client.connect(this.host, 21);

            int reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                throw new IOException("Could not connect to FTP server");
            }

            client.login(this.username, this.password);
            client.enterLocalPassiveMode();

            client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            client.setFileType(FTP.BINARY_FILE_TYPE);

            client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

            client.changeWorkingDirectory(this.path);

            logger.info("CONNECTED TO FTP SERVER");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void disconnect() throws IOException {
        client.disconnect();
        logger.info("DISCONNECTED FROM FTP SERVER");
    }

    public static FTPConnector getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new FTPConnector();
            return instance;
        }
    }

    public FTPClient getClient() {
        if (instance != null) {
            return client;
        } else {
            throw new NullPointerException("Could not return FTPClient instance, because instance is null");
        }
    }
}
