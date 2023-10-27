package com.roberv.skin.controller;

import com.roberv.skin.models.Skin;
import com.roberv.skin.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping(value = "/skins")
public class SkinController {

    @Autowired
    SkinService skinService;

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Skin> findAll() {
        return skinService.findAll();
    }


}
