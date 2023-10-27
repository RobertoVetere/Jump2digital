package com.roberv.skin.controller;

import com.roberv.skin.models.Skin;
import com.roberv.skin.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/skins")
public class SkinController {

    @Autowired
    SkinService skinService;

    @GetMapping("/avaible")
    @ResponseStatus(HttpStatus.OK)
    public List<Skin> findAll() {
        return skinService.findAll();
    }

    @PostMapping("/buy")
    public ResponseEntity<Skin> buySkin(@RequestBody Skin skin) {
        skinService.buySkin(skin);
        return ResponseEntity.ok(skin);
    }



}
