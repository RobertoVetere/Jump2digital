package com.roberv.skin.controller;

import com.roberv.skin.models.Skin;
import com.roberv.skin.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/skins")
public class SkinController {

    @Autowired
    SkinService skinService;

    @GetMapping("/avaible")
    public ResponseEntity<String> getAvailableSkins() {
        return skinService.getAvaiableSkins();
    }

    @PostMapping("/buy")
    public ResponseEntity<Skin> buySkin(@RequestBody String name) {
        Skin savedSkin = skinService.buySkin(name);
        return ResponseEntity.ok(savedSkin);
    }

    @GetMapping("/myskins")
    public List<Skin> getMySkins() {
        return skinService.getMySkins();
    }

    @PutMapping("/color/{id}")
    public ResponseEntity<Skin> changeSkinColor(
            @PathVariable("id") Long id,
            @RequestParam(name = "newColor", required = false) String newColor) {

        Skin result = skinService.changeSkinColor(id, newColor);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSkin(@PathVariable("id") String id) {
        if (!id.matches("\\d+")) {
            return ResponseEntity.badRequest().body("El ID debe ser un valor numérico válido.");
        }

        Long skinId = Long.parseLong(id);
        String result = skinService.deleteSkin(skinId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getskin/{id}")
    public ResponseEntity<Skin> getSkin(@PathVariable("id") Long id) {
        Skin skin = skinService.getSkin(id);

        if ("Skin no encontrada".equals(skin.getName())) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(skin);
        }
    }

}
