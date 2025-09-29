package com.api.fiskereregister.service;

import com.api.fiskereregister.dto.FishDto;
import com.api.fiskereregister.model.Fish;
import com.api.fiskereregister.repository.FishRepository;
import com.api.fiskereregister.dto.FishResponse;
import com.api.fiskereregister.service.impl.FishServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FishServiceTests {

    @Mock
    private FishRepository fishRepository;

    @InjectMocks
    private FishServiceImpl fishService; //use the concrete implementation

    @Test
    void getAllFish_ReturnsResponseDto() {
        // given
        Page<Fish> fishes = Mockito.mock(Page.class);
        when(fishRepository.findAll(Mockito.any(Pageable.class))).thenReturn(fishes);

        // when
        FishResponse response = fishService.getAllFish(1, 10);

        // then
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    public void updatePokemon_ReturnFishDto() {
        int fishId = 1;
        Fish fish = Fish.builder()
                .id(1)
                .navn("fish 1")
                .art("art 1")
                .lengde(1224.45)
                .vekt(34.4)
                .build();

        FishDto fishDto = FishDto.builder()
                .id(1)
                .navn("fish 1")
                .art("art 1")
                .lengde(1224.45)
                .vekt(34.4)
                .build();

        when(fishRepository.findById(fishId)).thenReturn(Optional.ofNullable(fish));
        when(fishRepository.save(fish)).thenReturn(fish);

        FishDto updateReturn = fishService.updateFish(fishId, fishDto);

        Assertions.assertThat(updateReturn).isNotNull();
    }

    @Test
    public void deleteFishById_ReturnVoid() {
        int fishId = 1;
        Fish fish = Fish.builder()
                .id(1)
                .navn("fish 1")
                .art("art 1")
                .lengde(1224.45)
                .vekt(34.4)
                .build();

        when(fishRepository.findById(fishId)).thenReturn(Optional.ofNullable(fish));
        doNothing().when(fishRepository).delete(fish);

        assertAll(() -> fishService.deleteFish(fishId));
    }
}
