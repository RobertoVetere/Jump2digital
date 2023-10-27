package com.roberv.skin.repositoryTests;

import com.roberv.skin.models.Skin;
import com.roberv.skin.repository.SkinRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SkinRepositoryTests {

	@Autowired
	private SkinRepository skinRepository;

	@BeforeEach
	public void setUp() {

		Skin existingSkin = skinRepository.findByName("TestSkin");
		if (existingSkin == null) {
			Skin skin = new Skin("TestSkin", "Type", 10.99, "Red", "Description");
			skinRepository.save(skin);
		}
	}

	@Test
	public void testFindByName() {
		Skin foundSkin = skinRepository.findByName("TestSkin");

		assertNotNull(foundSkin);
		assertEquals("TestSkin", foundSkin.getName());
	}

	@Test
	public void testFindByType() {
		Skin skin1 = new Skin("Skin1", "Type1", 10.99, "Red", "Description");
		Skin skin2 = new Skin("Skin2", "Type2", 15.99, "Blue", "Description");
		skinRepository.saveAll(List.of(skin1, skin2));

		List<Skin> skins = skinRepository.findByType("Type1");

		assertEquals(1, skins.size());
		assertEquals("Skin1", skins.get(0).getName());
	}

	@AfterEach
	public void tearDown() {
		skinRepository.deleteAll();
	}
}
