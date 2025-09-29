package com.api.fiskereregister.repository;

import com.api.fiskereregister.model.Fish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FishRepositoryTests {
    @Autowired
    private FishRepository fishRepository;

    @Test
    public void FishRepository_SaveAll_ReturnsAllFishes()
    {
        //Arrange
        Fish fish = Fish.builder()
                .navn("Steinbiten")
                .art("Steinbit")
                .lengde(95.0)
                .vekt(9.5)
                .build();
        //Act
        Fish savedFish = fishRepository.save(fish);
        //Assert
        Assertions.assertNotNull(savedFish);
        Assertions.assertTrue(savedFish.getId() > 0);
    }

    @Test
    public void PokemonRepository_GetAll_ReturnMoreThanOneFish() {
        Fish fish = Fish.builder()
                .navn("Steinbiten")
                .art("Steinbit")
                .lengde(95.0)
                .vekt(9.5)
                .build();
        Fish fish2 = Fish.builder()
                .navn("Kveita")
                .art("Kveite")
                .lengde(150.0)
                .vekt(45.5)
                .build();

        fishRepository.save(fish);
        fishRepository.save(fish2);

        List<Fish> fishList = fishRepository.findAll();

        Assertions.assertNotNull(fishList);
        Assertions.assertTrue(fishList.size() >= 2);
    }



}
