package com.github.rozumek29.plantseekerpanel.ftp;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.PlantImage;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FTPUploader {

    private static FTPClient client;
    private static final Logger logger = LoggerFactory.getLogger(FTPUploader.class);

    public static GardenPlant uploadImage(MultiFileMemoryBuffer buffer, GardenPlant plant) {
        client = FTPConnector.getClient();
        List<PlantImage> images1 = new ArrayList<>();
        int i = 0;
        for (String file : buffer.getFiles()) {
            String fileName = "img_" + plant.getId() + "_" + i + "." + FilenameUtils.getExtension(file);
            InputStream inputStream = buffer.getInputStream(file);
            try {
                client.storeFile(fileName, inputStream);
                images1.add(new PlantImage(fileName));
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        plant.setImages(images1);
        logger.info("[FTP] IMAGE UPLOADED");
        FTPConnector.disconnect();
        return plant;
    }

    public static PottedPlant uploadImage(MultiFileMemoryBuffer buffer, PottedPlant plant) {
        client = FTPConnector.getClient();
        List<PlantImage> images2 = new ArrayList<>();
        int i = 0;
        for (String file : buffer.getFiles()) {
            String fileName = "img_" + plant.getId() + "_" + i + "." + FilenameUtils.getExtension(file);
            InputStream inputStream = buffer.getInputStream(file);
            try {
                client.storeFile(fileName, inputStream);
                images2.add(new PlantImage(fileName));
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        plant.setImages(images2);
        logger.info("[FTP] IMAGE UPLOADED");
        FTPConnector.disconnect();
        return plant;
    }

    public static PottedPlant reuploadImage(MultiFileMemoryBuffer buffer, PottedPlant plant){
        client = FTPConnector.getClient();
        List<PlantImage> images = plant.getImages();
        int i = plant.getImages().size();
        for (String file : buffer.getFiles()) {
            String fileName = "img_" + plant.getId() + "_" + i + "." + FilenameUtils.getExtension(file);
            InputStream inputStream = buffer.getInputStream(file);
            try {
                client.storeFile(fileName, inputStream);
                images.add(new PlantImage(fileName));
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        plant.setImages(images);
        logger.info("[FTP] IMAGE UPLOADED");
        FTPConnector.disconnect();
        return plant;
    }

    public static void remove(String img_name){
        client = FTPConnector.getClient();
        try {
            client.deleteFile(img_name);
            logger.info("[FTP] IMAGE " + img_name + " HAS BEEN DELETED");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FTPConnector.disconnect();
    }

}
