package com.api.fiskereregister.controllers;

import com.api.fiskereregister.dto.FishDto;
import com.api.fiskereregister.dto.FishResponse;
import com.api.fiskereregister.service.FishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class FishController {

    private final FishService fishService;

    @Autowired
    public FishController(FishService fishService) { this.fishService = fishService;  }


    //Få liste
    @GetMapping("fish")
    public ResponseEntity<FishResponse> getFishs(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "Navn", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return new ResponseEntity<>(fishService.getAllFish(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    // Få fish data med Id
    /*
    @GetMapping("fish/{id}")
    public ResponseEntity<FishDto> getFish(@PathVariable("id") int fishId) {
        return ResponseEntity.ok(fishService.getFishById(fishId));
    }
    */

    //create
    @PostMapping("fish/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FishDto> createFish(@Valid @RequestBody FishDto fishDto) {
        return new ResponseEntity<>(fishService.createFish(fishDto), HttpStatus.CREATED);
    }

    //Update
    @PutMapping("fish/{id}/update")
    public ResponseEntity<FishDto> updateFish(@RequestBody FishDto fishDto, @PathVariable("id") int fishId) {
        return new ResponseEntity<>(fishService.updateFish(fishId, fishDto), HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("fish/{id}/delete")
    public ResponseEntity<String> deleteFish(@PathVariable("id") int fishId) {
        fishService.deleteFish(fishId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
