package com.epam.hplus.controller.commands.util;

import com.epam.hplus.util.resources.ConfigurationManger;
import jakarta.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageProcessor.class);

    private ImageProcessor() {
    }

    public static String uploadFile(Part part, String fileName) {
        String uploadDir = ConfigurationManger.getProperty("dir.uploads");
        try {
            Path path = Paths.get(uploadDir);
            if (Files.notExists(path)) {
                Files.createDirectories(path);
            }
            part.write(uploadDir + fileName);
            return fileName;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
