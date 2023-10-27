package com.roberv.skin.service;

import com.roberv.skin.models.Skin;
import com.roberv.skin.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinService {

    @Autowired
    SkinRepository skinRepository;
    public List<Skin> findAll() {
        return skinRepository.findAll();
    }

    public void buySkin(Skin skin) {

    }
}
