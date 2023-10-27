package com.roberv.skin.repository;

import com.roberv.skin.models.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Long> {

    Skin findByName(String testSkin);

    List<Skin> findByType(String type1);


}
