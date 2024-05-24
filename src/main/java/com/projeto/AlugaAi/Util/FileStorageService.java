package com.projeto.AlugaAi.Util;

import com.projeto.AlugaAi.Exeptions.NoImagesProvidedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path rootLocation;

    public FileStorageService() {
        this.rootLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            // Cria o diretório se ele não existir
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new NoImagesProvidedException("Pelo menos uma imagem deve ser fornecida.");
            }
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao armazenar arquivo.", e);
        }
    }

    public boolean exists(String imagem) {
        Path imagePath = this.rootLocation.resolve(imagem);
        return Files.exists(imagePath);
    }
}
