package com.api.fiskereregister.service;

import com.api.fiskereregister.dto.FishDto;
import com.api.fiskereregister.dto.FishResponse;

public interface FishService {
    FishDto createFish(FishDto fishDto);
    FishResponse getAllFish(int pageNo, int pageSize );
    //FishDto getFishById(int id);
    FishDto updateFish(int id, FishDto fishDto);
    void deleteFish(int id);
}
