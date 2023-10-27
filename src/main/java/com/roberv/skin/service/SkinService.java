package com.roberv.skin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberv.skin.models.Skin;
import com.roberv.skin.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class SkinService {

    @Autowired
    SkinRepository skinRepository;
    public List<Skin> readSkinsFromJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource("skins.json");
        InputStream inputStream = resource.getInputStream();
        List<Skin> skins = objectMapper.readValue(inputStream, new TypeReference<>() {
        });
        return skins;
    }

    public void buySkin(Skin skin) {

    }
}
