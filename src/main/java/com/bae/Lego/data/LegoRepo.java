package com.bae.Lego.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegoRepo extends JpaRepository<Lego, Integer> {

	List<Lego> findByKitNameIgnoreCase(String kitName);

}
