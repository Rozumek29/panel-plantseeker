package com.github.rozumek29.plantseekerpanel.ftp;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.PlantImage;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FTPUploader{

    private static final FTPConnector connector = FTPConnector.getInstance();
    private static final FTPClient client = connector.getClient();

    public static GardenPlant uploadImage(MultiFileMemoryBuffer buffer, GardenPlant plant){
        List<PlantImage> images1 = new ArrayList<>();
        int i = 0;
        for (String file : buffer.getFiles()){
            String fileName = "img_"+plant.getId()+"_"+i+"."+FilenameUtils.getExtension(file);
            InputStream inputStream = buffer.getInputStream(file);
            try {
                client.storeFile(fileName, inputStream);
                images1.add(new PlantImage(fileName));
                i++;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        plant.setImages(images1);
        return plant;
    }

    public static PottedPlant uploadImage(MultiFileMemoryBuffer buffer, PottedPlant plant){
        List<PlantImage> images2 = new ArrayList<>();
        int i = 0;
        for (String file : buffer.getFiles()){
            String fileName = "img_"+plant.getId()+"_"+i+"."+FilenameUtils.getExtension(file);
            InputStream inputStream = buffer.getInputStream(file);
            try {
                client.storeFile(fileName, inputStream);
                images2.add(new PlantImage(fileName));
                i++;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        plant.setImages(images2);
        return plant;
    }

}
