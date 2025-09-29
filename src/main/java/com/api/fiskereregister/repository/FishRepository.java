package com.api.fiskereregister.repository;

import com.api.fiskereregister.model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Integer> {
}
