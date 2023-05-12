package com.petkpetk.service.config.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ImageLocalRepositoryTest {

    private final String ORIGINAL_FILE_NAME = "test.txt";
    private final String DELETE_FILE_NAME = "delete.txt";

    @Value("${itemImageLocation}")
    private String itemImageLocation;

    @Autowired
    private ImageLocalRepository imageLocalRepository;

    @Test
    @DisplayName("파일 업로드 테스트")
    void testUploadFile() throws Exception {
        // Given
        byte[] fileData = "test file data".getBytes();

        // When
        String savedFileName = imageLocalRepository.saveFiles(itemImageLocation, ORIGINAL_FILE_NAME, fileData);

        // Then
        assertThat(savedFileName).isNotNull();
    }


    @Test
    @DisplayName("파일 삭제 테스트")
    void testDeleteFile() throws Exception {
        // Given
        String filePath = itemImageLocation+DELETE_FILE_NAME;

        // When
        copyFile(filePath, filePath + ".backup");        // 파일 복사
        imageLocalRepository.deleteFile(itemImageLocation,DELETE_FILE_NAME);

        // Then
        assertThat(new File(filePath).exists()).isFalse();

        restoreFile(filePath+".backup", filePath);
    }



    private void copyFile(String source, String target) throws IOException {
        Path sourcePath = Paths.get(source);
        Path targetPath = Paths.get(target);
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private void restoreFile(String source, String target) throws IOException {
        Path sourcePath = Paths.get(source);
        Path targetPath = Paths.get(target);
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }


}
