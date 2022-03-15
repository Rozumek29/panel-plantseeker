package com.github.rozumek29.plantseekerpanel.ftp;

import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@SpringComponent
public class FTPConnector {

    protected static String host = "serwer1727017.home.pl";
    protected static String username = "kacper.wyrozumialski1@zspwrzesnia.pl";
    protected static String password = "VK6xW8EYyrKj";
    protected static String path = "/plants/database_img/";
    private static FTPClient client;


    private static final Logger logger = LoggerFactory.getLogger(FTPClient.class);

    public FTPConnector() throws IOException {
        client = new FTPClient();
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
        
//        client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        client.changeWorkingDirectory(this.path);

        logger.info("CONNECTED TO FTP SERVER");
    }

    @PreDestroy
    public static void disconnect() throws IOException {
        client.disconnect();
        logger.info("DISCONNECTED FROM FTP SERVER");
    }

    public static FTPClient getClient() {
        return client;
    }
}
