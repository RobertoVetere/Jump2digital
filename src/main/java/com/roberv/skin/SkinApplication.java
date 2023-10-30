package com.roberv.skin;

import com.roberv.skin.models.Skin;
import com.roberv.skin.repository.SkinRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Rest Skins", version = "1", description = "API de prueba para Jump2Digital"))
public class SkinApplication implements CommandLineRunner {

    @Autowired
    SkinRepository skinRepository;

    public static void main(String[] args) {
        SpringApplication.run(SkinApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Skin> skins = Arrays.asList(
                new Skin("Neon Ninja", "Rare", 9.99, "Neon Green", "Strike from the shadows with a neon twist."),
                new Skin("Phantom Sorcerer", "Legendary", 19.99, "Dark Red", "Master the arcane arts with this sorcerer's skin."),
                new Skin("Frostbite Samurai", "Epic", 14.99, "Ice Blue", "Channel the power of ice and steel as a frostbite samurai."),
                new Skin("Rainforest Explorer", "Rare", 9.99, "Green Camo", "Venture into the heart of the jungle with this explorer's skin."),
                new Skin("Pixel Paladin", "Epic", 14.99, "Pixelated", "Defend the realm with retro-style pixel art armor.")
        );

        skinRepository.saveAll(skins);
    }
}
