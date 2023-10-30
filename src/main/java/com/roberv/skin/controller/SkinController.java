package com.roberv.skin.controller;

import com.roberv.skin.dtos.SkinDTO;
import com.roberv.skin.models.Skin;
import com.roberv.skin.service.SkinService;
import com.roberv.skin.service.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/skins")
public class SkinController {

    @Autowired
    SkinService skinService;

    /**
     * Obtiene una lista de las skins disponibles.
     *
     * @return Una respuesta HTTP que contiene el contenido JSON de las skins disponibles.
     */
    @GetMapping("/avaible")
    public List<SkinDTO> getAvailableSkins() {
        return skinService.getAvailableSkins();
    }

    /**
     * Compra una nueva skin por nombre.
     *
     * @param name El nombre de la skin que se quiere comprar.
     * @return Una respuesta HTTP que contiene la nueva skin comprada en un SkinDTO para generar una transacción mas ligera .
     */
    @PostMapping("/buy")
    public ResponseEntity<SkinDTO> buySkin(@RequestBody String name) {
        SkinDTO savedSkin = skinService.buySkin(name);
        return ResponseEntity.ok(savedSkin);
    }

    /**
     * Obtiene una lista de todas las skins del usuario.
     *
     * @return Una lista de skins.
     */
    @GetMapping("/myskins")
    public List<SkinDTO> getMySkins() {
        return skinService.getAllMySkins();
    }

    /**
     * Cambia el color de una skin por su ID.
     *
     * @param id       El ID de la skin a modificar.
     * @param newColor El nuevo color de la skin (opcional).
     * @return Una respuesta HTTP que contiene la skin modificada en un SkinChangeColorDTO.
     */
    @PutMapping("/color/{id}")
    public ResponseEntity<SkinDTO> changeSkinColor(
            @PathVariable("id") Long id,
            @RequestParam(name = "newColor", required = false) String newColor) {

        SkinDTO result = skinService.changeSkinColor(id, newColor);
        return ResponseEntity.ok(result);
    }

    /**
     * Elimina una skin por su ID.
     *
     * @param id El ID de la skin a eliminar.
     * @return Una respuesta HTTP con un mensaje indicando el resultado de la operación.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSkin(@PathVariable("id") String id) {
        if (!ValidationUtils.isValidNumericId(id)) {
            return ResponseEntity.badRequest().body("El ID debe ser un valor numérico válido.");
        }

        Long skinId = Long.parseLong(id);
        String result = skinService.deleteSkin(skinId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una skin por su ID.
     *
     * @param id El ID de la skin a obtener.
     * @return Una respuesta HTTP que contiene la skin con el ID especificado.
     */
    @GetMapping("/getskin/{id}")
    public ResponseEntity<Skin> getSkin(@PathVariable("id") String id) {
        Skin skin = skinService.getSkin(id);
        return ResponseEntity.ok(skin);
    }
}
