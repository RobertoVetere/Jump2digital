package com.roberv.skin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberv.skin.dtos.SkinDTO;
import com.roberv.skin.exceptions.EmptyColorException;
import com.roberv.skin.exceptions.SkinNotFoundException;
import com.roberv.skin.exceptions.SkinPurchaseException;
import com.roberv.skin.models.Skin;
import com.roberv.skin.repository.SkinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkinService {

    @Autowired
    SkinRepository skinRepository;

    /**
     * Busca una skin por nombre en la lista de skins cargada desde un archivo JSON.
     *
     * @param targetName El nombre de la skin a buscar.
     * @return Una skin opcional encontrada por nombre.
     * @throws SkinNotFoundException Si la skin no se encuentra en la lista de skins.
     */
    public Optional<Skin> findSkinByNameOnJson(String targetName) {
        List<Skin> skins = loadSkinsFromJson();
        return skins.stream()
                .filter(skin -> skin.getName().equalsIgnoreCase(targetName))
                .findFirst();
    }

    /**
     * Obtiene una lista de las skins disponibles desde un archivo JSON.
     *
     * @return Una lista de skins disponibles.
     */
    public List<SkinDTO> getAvailableSkins() {
        List<Skin> skins = loadSkinsFromJson();
        List<SkinDTO> skinDTOs = new ArrayList<>();

        for (Skin skin : skins) {
            SkinDTO skinDTO = convertToSkinDTO(skin);
            skinDTOs.add(skinDTO);
        }

        return skinDTOs;
    }

    private List<Skin> loadSkinsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Skin>> typeReference = new TypeReference<>() {
        };

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("skins.json")) {
            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new SkinNotFoundException("Esta Skin no existe en la lista de Skins");
        }
    }

    /**
     * Compra una nueva skin por nombre.
     *
     * @param name El nombre de la skin que se quiere comprar.
     * @return La nueva skin comprada en un SkinDTO con la información necesaria.
     * @throws SkinNotFoundException Si la skin no se encuentra.
     * @throws SkinPurchaseException Si ocurre un error en la compra.
     */
    public SkinDTO buySkin(String name) {
        Optional<Skin> foundSkin = findSkinByNameOnJson(name);

        if (foundSkin.isPresent()) {
            Skin savedSkin = foundSkin.get();
            Skin newSkin = createNewSkin(savedSkin);
            saveSkin(newSkin);
            return convertToSkinDTO(newSkin);
        } else {
            throw new SkinNotFoundException("Skin no encontrada, no hay una skin disponible con ese nombre");
        }
    }

    /**
     * Crea una nueva skin con los datos de una skin existente.
     *
     * @param savedSkin La skin existente.
     * @return La nueva skin creada.
     */
    private Skin createNewSkin(Skin savedSkin) {
        return new Skin(
                savedSkin.getName(),
                savedSkin.getType(),
                savedSkin.getPrice(),
                savedSkin.getColor(),
                savedSkin.getDescription()
        );
    }

    /**
     * Guarda una skin en el repositorio.
     *
     * @param newSkin La nueva skin a guardar.
     * @throws SkinPurchaseException Si ocurre un error al guardar la skin.
     */
    private void saveSkin(Skin newSkin) {
        try {
            skinRepository.save(newSkin);
        } catch (Exception e) {
            throw new SkinPurchaseException("Error en la compra de la skin, no se pudo guardar la nueva skin", e);
        }
    }

    /**
     * Convierte una Skin en un SkinDTO.
     *
     * @param skin La skin a convertir.
     * @return El SkinDTO resultante.
     */
    private SkinDTO convertToSkinDTO(Skin skin) {
        return new SkinDTO.Builder()
                .name(skin.getName())
                .type(skin.getType())
                .price(skin.getPrice())
                .color(skin.getColor())
                .description(skin.getDescription())
                .build();
    }


    /**
     * Obtiene una lista de todas las skins del usuario.
     *
     * @return Una lista de skins.
     * @throws SkinNotFoundException Si ocurre un error al obtener las skins del usuario.
     */
    public List<SkinDTO> getAllMySkins() {
        try {
            List<Skin> skinsList = skinRepository.findAll();
            List<SkinDTO> skinDTOs = new ArrayList<>();

            for (Skin skin : skinsList) {
                SkinDTO skinDTO = convertToSkinDTO(skin);
                skinDTO.setId(skin.getId());
                skinDTOs.add(skinDTO);
            }
            return skinDTOs;
        } catch (Exception e) {
            throw new SkinNotFoundException("Error al obtener las skins del usuario", e);
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
        Optional<Skin> skin = getSkinOrThrowException(skinId);

        if (skin.isPresent()) {
            return skin.get();
        } else {
            throw new SkinNotFoundException("Skin no encontrada");
        }
    }

    /**
     * Cambia el color de una skin por su ID.
     *
     * @param skinId   El ID de la skin a modificar.
     * @param newColor El nuevo color de la skin.
     * @return La skin modificada en un SkinChangeColorDTO para generar transacciones mas eficientes.
     * @throws SkinNotFoundException Si la skin no se encuentra.
     * @throws EmptyColorException   Si el nuevo color es vacío.
     */
    public SkinDTO changeSkinColor(Long skinId, String newColor) {
        Optional<Skin> optionalSkin = getSkinOrThrowException(skinId);
        Skin skin = optionalSkin.get();

        if (newColor != null && !newColor.isEmpty()) {
            skin.setColor(newColor);
            skinRepository.save(skin);
            return new SkinDTO.Builder()
                    .id(skin.getId())
                    .name(skin.getName())
                    .color(skin.getColor())
                    .build();
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
