package com.roberv.skin.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberv.skin.models.Skin;
import com.roberv.skin.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/skins")
public class SkinController {

    @Autowired
    SkinService skinService;

    @GetMapping("/available")
    public String getAvailableSkins() throws IOException {
        Resource resource = new ClassPathResource("skins.json");
        InputStream inputStream = resource.getInputStream();

        return new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);
    }

    @PostMapping("/buy")
    public ResponseEntity<Skin> buySkin(@RequestBody Skin skin) {
        skinService.buySkin(skin);
        return ResponseEntity.ok(skin);
    }



}
