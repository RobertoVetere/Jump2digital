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

    /**
     * Obtiene una lista de las skins disponibles desde un archivo JSON.
     *
     * @return Una respuesta HTTP que contiene el contenido JSON de las skins disponibles.
     */
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

    /**
     * Compra una nueva skin por nombre.
     *
     * @param name El nombre de la skin que se quiere comprar.
     * @return La nueva skin comprada.
     * @throws SkinNotFoundException Si la skin no se encuentra.
     * @throws SkinPurchaseException Si ocurre un error en la compra.
     */
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
            throw new SkinPurchaseException("Error en la compra de la skin, no hay una skin disponible con ese nombre", e);
        }
    }

    /**
     * Este método encuentra una skin por su nombre en una lista simulada de skins desde un archivo JSON.
     *
     * @param targetName El nombre de la skin a buscar.
     * @return Una skin opcional encontrada por nombre.
     * @throws SkinNotFoundException Si la skin no se encuentra en la lista simulada de skins.
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

    /**
     * Obtiene una lista de todas las skins del usuario.
     *
     * @return Una lista de skins.
     */
    public List<Skin> getAllMySkins() {
        try {
            return skinRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Elimina una skin por su ID.
     *
     * @param id El ID de la skin a eliminar.
     * @return Un mensaje indicando el resultado de la operación.
     */
    public String deleteSkin(Long id) {
        return skinRepository.findById(id)
                .map(skin -> {
                    skinRepository.delete(skin);
                    return "Skin eliminada con éxito";
                })
                .orElse("Skin no encontrada en tu lista");
    }

    /**
     * Obtiene una skin por su ID.
     *
     * @param id El ID de la skin a obtener.
     * @return La skin con el ID especificado.
     * @throws SkinNotFoundException Si la skin no se encuentra.
     * @throws NumberFormatException Si el ID no es un valor numérico válido.
     */
    public Skin getSkin(String id) {
        if (!ValidationUtils.isValidNumericId(id)) {
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

    /**
     * Cambia el color de una skin por su ID.
     *
     * @param skinId El ID de la skin a modificar.
     * @param newColor El nuevo color de la skin.
     * @return La skin modificada.
     * @throws SkinNotFoundException Si la skin no se encuentra.
     * @throws EmptyColorException Si el nuevo color es vacío.
     */
    public Skin changeSkinColor(Long skinId, String newColor) {
        Optional<Skin> optionalSkin = getSkinOrThrowException(skinId);
        Skin skin = optionalSkin.get();

        if (newColor != null && !newColor.isEmpty()) {
            skin.setColor(newColor);
            skinRepository.save(skin);
            return skin;
        } else {
            throw new EmptyColorException("El nuevo color no puede estar vacío.");
        }
    }

    private Optional<Skin> getSkinOrThrowException(Long id) {
        Optional<Skin> skin = skinRepository.findById(id);

        if (skin.isPresent()) {
            return skin;
        } else {
            throw new SkinNotFoundException("Skin no encontrada");
        }
    }
}
