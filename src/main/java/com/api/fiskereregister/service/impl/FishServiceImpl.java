package com.api.fiskereregister.service.impl;

import com.api.fiskereregister.dto.FishDto;
import com.api.fiskereregister.dto.FishResponse;
import com.api.fiskereregister.exceptions.FishNotFoundException;
import com.api.fiskereregister.model.Fish;
import com.api.fiskereregister.repository.FishRepository;
import com.api.fiskereregister.service.FishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FishServiceImpl implements FishService {

    private FishRepository fishRepository;

    public FishServiceImpl(FishRepository fishRepository) { this.fishRepository = fishRepository;    }

    @Override
    public FishDto createFish(FishDto fishDto) {
        Fish fish = new Fish();
        fish.setNavn(fishDto.getNavn());
        fish.setArt(fishDto.getArt());
        fish.setLengde(fishDto.getLengde());
        fish.setVekt(fishDto.getVekt());

        Fish newFish = fishRepository.save(fish);

        FishDto fishResponse = new FishDto();
        fishResponse.setId(newFish.getId());
        fishResponse.setNavn(newFish.getNavn());
        fishResponse.setArt(newFish.getArt());
        fishResponse.setLengde(newFish.getLengde());
        fishResponse.setVekt(newFish.getVekt());


        return fishResponse;
    }

    @Override
    public FishResponse getAllFish(int pageNO, int pageSize) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);
        Page<Fish> fish = fishRepository.findAll(pageable);
        List<Fish> listOfFish = fish.getContent();
        List<FishDto> content = listOfFish.stream().map(this::mapToDto).collect(Collectors.toList());

        //Custom mapping
        FishResponse fishResponse = new FishResponse();
        fishResponse.setFishList(content);
        fishResponse.setPageNo(pageNO);
        fishResponse.setPageSize(pageSize);
        fishResponse.setTotalElements(fish.getTotalElements());
        fishResponse.setTotalPages(fish.getTotalPages());
        fishResponse.setLast(fish.isLast());

        return fishResponse;
    }

    /*
    @Override
    public FishDto getFishById(int id) {
        Fish fish = fishRepository.findById(id).orElseThrow(()-> new FishNotFoundException("Fish could not be found by id ${id}"));
        return mapToDto(fish);
    }
    */

    @Override
    public FishDto updateFish(int id, FishDto fishDto) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow( ()-> new FishNotFoundException("Fish could not be updated") );
        fish.setNavn(fishDto.getNavn());
        fish.setArt(fishDto.getArt());
        fish.setLengde(fishDto.getLengde());
        fish.setVekt(fishDto.getVekt());
        Fish updatdFish = fishRepository.save(fish);

        return mapToDto(updatdFish);
    }

    @Override
    public void deleteFish(int id) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow( () -> new FishNotFoundException("Fish could not be deleted") );
        fishRepository.delete(fish);
    }

    //Mappings Model --> Dto
    private FishDto mapToDto(Fish fish) {
        FishDto fishDto = new FishDto();

        fishDto.setId(fish.getId());
        fishDto.setNavn(fish.getNavn());
        fishDto.setArt(fish.getArt());
        fishDto.setLengde(fish.getLengde());
        fishDto.setVekt(fish.getVekt());

        return fishDto;
    }
}


