package com.roberv.skin.controllersTests;

import com.roberv.skin.controller.SkinController;
import com.roberv.skin.service.SkinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class SkinControllerTests {

	@InjectMocks
	private SkinController skinController;

	@Mock
	private SkinService skinService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAvailableSkins() {
		// Mock del servicio para devolver una respuesta simulada
		String mockResponse = "Lista de skins disponibles";
		when(skinService.getAvaiableSkins()).thenReturn(ResponseEntity.ok(mockResponse));

		// Llama al controlador y verifica la respuesta
		ResponseEntity<String> response = skinController.getAvailableSkins();
		assertEquals(mockResponse, response.getBody());
	}
/*
	@Test
	public void testBuySkin() {
		// Mock del servicio para devolver una respuesta simulada
		String mockResponse = "Skin comprada con éxito.";
		when(skinService.buySkin(anyString())).thenReturn(ResponseEntity.ok(mockResponse));

		// Llama al controlador y verifica la respuesta
		ResponseEntity<String> response = skinController.buySkin("SkinName");
		assertEquals(mockResponse, response.getBody());
	}

 */
}
