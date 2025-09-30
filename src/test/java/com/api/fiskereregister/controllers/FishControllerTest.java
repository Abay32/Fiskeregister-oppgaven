package com.api.fiskereregister.controllers;

import com.api.fiskereregister.dto.FishDto;
import com.api.fiskereregister.dto.FishResponse;
import com.api.fiskereregister.model.Fish;
import com.api.fiskereregister.service.FishService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FishController.class)
@AutoConfigureMockMvc(addFilters = false)
class FishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FishService fishService;

    @Autowired
    private ObjectMapper objectMapper;

    private Fish fish;
    private FishDto fishDto;

    @BeforeEach
    void init() {
        fish = Fish.builder()
                .navn("Steinbiten")
                .art("Steinbit")
                .lengde(95.0)
                .vekt(9.5)
                .build();

        fishDto = FishDto.builder()
                .navn("Steinbiten")
                .art("Steinbit")
                .lengde(95.0)
                .vekt(9.5)
                .build();
    }

    @Test
    public void createFish_ReturnsCreatedFish() throws Exception {
        given(fishService.createFish(any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/v1/fish/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fishDto)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.navn", CoreMatchers.is(fishDto.getNavn())))
                .andExpect(jsonPath("$.art", CoreMatchers.is(fishDto.getArt())))
                .andExpect(jsonPath("$.lengde", CoreMatchers.is(fishDto.getLengde())))
                .andExpect(jsonPath("$.vekt", CoreMatchers.is(fishDto.getVekt())));
    }

    @Test
    public void getAllFish_ReturnResponseDto() throws Exception {
        FishResponse responseDto = FishResponse.builder().pageSize(10).last(true).pageNo(1).fishList(Arrays.asList(fishDto)).build();
        when(fishService.getAllFish(1,10, "navn", "asc")).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/v1/fish")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo","1")
                .param("pageSize", "10")
                .param("sortBy", "navn")
                .param("sortDir", "asc"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fishList.size()", CoreMatchers.is(responseDto.getFishList().size())));
    }

    @Test
    public void updateFish_ReturnsUpdatedFishDto() throws Exception {
        // given
        int fishId = 52;
        given(fishService.updateFish(eq(fishId), any(FishDto.class)))
                .willReturn(fishDto);

        // when
        ResultActions response = mockMvc.perform(put("/api/v1/fish/{id}/update", fishId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fishDto)));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.navn", CoreMatchers.is(fishDto.getNavn())))
                .andExpect(jsonPath("$.art", CoreMatchers.is(fishDto.getArt())))
                .andExpect(jsonPath("$.lengde", CoreMatchers.is(fishDto.getLengde())))
                .andExpect(jsonPath("$.vekt", CoreMatchers.is(fishDto.getVekt())));
    }

    @Test
    public void deleteFish_ReturnString() throws Exception {
        int fishId = 1;
        doNothing().when(fishService).deleteFish(fishId);

        ResultActions response = mockMvc.perform(delete("/api/v1/fish/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
