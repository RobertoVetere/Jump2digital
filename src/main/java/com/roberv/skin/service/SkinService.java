package com.roberv.skin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberv.skin.models.Skin;
import com.roberv.skin.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class SkinService {

    @Autowired
    SkinRepository skinRepository;

    public ResponseEntity<String> getAvaiableSkins() {
        try {
            Resource resource = new ClassPathResource("skins.json");
            InputStream inputStream = resource.getInputStream();
            String jsonContent = new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(jsonContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al leer el archivo JSON");
        }
    }

    public ResponseEntity<String> buySkin(String name) {
        try {
            Skin savedSkin = findSkin(name).get();
            skinRepository.save(savedSkin);
            return ResponseEntity.ok("Skin comprada y guardada con éxito. ID de la skin: " + savedSkin.getId());
        } catch (Exception e) {
            // Maneja cualquier error que pueda ocurrir durante la compra o guardado
            return ResponseEntity.status(500).body("Error al comprar y guardar la skin: " + e.getMessage());
        }
    }

    public Optional<Skin> findSkin(String targetName) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Skin>> typeReference = new TypeReference<>() {
        };

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("skins.json")) {
            List<Skin> skins = objectMapper.readValue(inputStream, typeReference);

            return skins.stream()
                    .filter(skin -> skin.getName().equalsIgnoreCase(targetName))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
