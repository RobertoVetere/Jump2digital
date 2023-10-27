package com.roberv.skin.modelsTests;

import com.roberv.skin.models.Skin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SkinModelTests {

	private Skin skin;

	@BeforeEach
	public void setUp() {
		skin = new Skin("Skin 1", "Type 1", 10.99, "Red", "Description for Skin 1");
	}

	@Test
	public void testSkinInitialization() {
		assertNotNull(skin);
	}

	@Test
	public void testSkinName() {
		assertEquals("Skin 1", skin.getName());
	}

	@Test
	public void testSkinType() {
		assertEquals("Type 1", skin.getType());
	}

	@Test
	public void testSkinPrice() {
		assertEquals(10.99, skin.getPrice(), 0.01);
	}

	@Test
	public void testSkinColor() {
		assertEquals("Red", skin.getColor());
	}

	@Test
	public void testSkinDescription() {
		assertEquals("Description for Skin 1", skin.getDescription());
	}

	@Test
	public void testSkinIdIsNullInitially() {
		assertNull(skin.getId());
	}

}