package com.roberv.skin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberv.skin.exceptions.EmptyColorException;
import com.roberv.skin.exceptions.SkinNotFoundException;
import com.roberv.skin.exceptions.SkinPurchaseException;
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
import java.util.ArrayList;
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

    public Skin buySkin(String name) {

        Optional<Skin> foundSkin = findSkinOnJson(name);

        try {
            if (foundSkin.isPresent()) {
                Skin savedSkin = foundSkin.get();
                Skin newSkin = new Skin(
                        savedSkin.getName(),
                        savedSkin.getType(),
                        savedSkin.getPrice(),
                        savedSkin.getColor(),
                        savedSkin.getDescription()
                );
                skinRepository.save(newSkin);
                return newSkin;
            }
            throw new SkinNotFoundException("Skin no encontrada");
        } catch (Exception e) {
            throw new SkinPurchaseException("Error en la compra de la skin", e);
        }
    }

    /**
     * findSkin(String targetName)
     * Este metodo encuentra una skin por su nombre de una lista dada skins.json
     * , simulando el consumo de una API externa
     */
    public Optional<Skin> findSkinOnJson(String targetName) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Skin>> typeReference = new TypeReference<>() {
        };

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("skins.json")) {
            List<Skin> skins = objectMapper.readValue(inputStream, typeReference);

            return skins.stream()
                    .filter(skin -> skin.getName().equalsIgnoreCase(targetName))
                    .findFirst();
        } catch (IOException e) {
            throw new SkinNotFoundException("Está Skin no existe en la lista de Skins");
        }
    }


    public List<Skin> getAllMySkins() {
        try {
            return skinRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String deleteSkin(Long id) {
        Optional<Skin> skin = skinRepository.findById(id);

        if (skin.isPresent()) {
            skinRepository.delete(skin.get());
            return "Skin eliminada con éxito";
        } else {
            return "Skin no encontrada en tu lista";
        }
    }

    public Skin getSkin(String id) {
        if (!id.matches("\\d+")) {
            throw new SkinNotFoundException("El ID debe ser un valor numérico válido.");
        }
            Long skinId = Long.parseLong(id);
            Optional<Skin> skin = skinRepository.findById(skinId);
            if (skin.isPresent()) {
                return skin.get();
            } else {
                throw new SkinNotFoundException("Skin no encontrada");
            }
    }

    public Skin changeSkinColor(Long skinId, String newColor) {
        Optional<Skin> optionalSkin = skinRepository.findById(skinId);

        if (optionalSkin.isPresent()) {
            Skin skin = optionalSkin.get();

            if (newColor != null && !newColor.isEmpty()) {
                skin.setColor(newColor);
                skinRepository.save(skin);
                return skin;
            } else {
                throw new EmptyColorException("El nuevo color no puede estar vacío.");
            }
        } else {
            throw new SkinNotFoundException("No se pudo encontrar una skin con el ID proporcionado: " + skinId);
        }
    }


}
