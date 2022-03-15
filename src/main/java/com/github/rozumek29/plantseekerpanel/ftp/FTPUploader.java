package com.github.rozumek29.plantseekerpanel.ftp;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.PlantImage;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.vaadin.flow.component.upload.GeneratedVaadinUpload;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FTPUploader{

    public static GardenPlant uploadImage(MultiFileMemoryBuffer buffer, GardenPlant plant){
        FTPClient client = FTPConnector.getClient();
        List<PlantImage> images = new ArrayList<>();
        int i = 0;
        for (String file : buffer.getFiles()){
            String fileName = "img_"+plant.getId()+"_"+i+"."+FilenameUtils.getExtension(file);
            InputStream inputStream = buffer.getInputStream(file);
            try {
                client.storeFile(fileName, inputStream);
                images.add(new PlantImage(fileName));
                i++;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        plant.setImages(images);
        return plant;
    }

    public static PottedPlant uploadImage(MultiFileMemoryBuffer buffer, PottedPlant plant){
        FTPClient client = FTPConnector.getClient();
        List<PlantImage> images = new ArrayList<>();
        int i = 0;
        for (String file : buffer.getFiles()){
            String fileName = "img_"+plant.getId()+"_"+i+"."+FilenameUtils.getExtension(file);
            InputStream inputStream = buffer.getInputStream(file);
            try {
                client.storeFile(fileName, inputStream);
                images.add(new PlantImage(fileName));
                i++;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        plant.setImages(images);
        return plant;
    }

}
