package com.bae.Lego.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.Lego.data.Lego;
import com.bae.Lego.data.LegoRepo;

@SpringBootTest
@ActiveProfiles("test")
public class LegoServiceDBUnitTest {

	@Autowired // injects the actual service from the context
	private LegoServiceDB service;

	@MockBean // tells Spring to make a 'fake' LegoRepo that we can program
	private LegoRepo repo;

	@Test
	void testCreate() {

		Lego newLego = new Lego("Friends", 9845, "Beach Car", 2019);

		Lego savedLego = new Lego(1, "Friends", 9845, "Beach Car", 2019);

		Mockito.when(this.repo.save(newLego)).thenReturn(savedLego);

		assertThat(this.service.createLegoKit(newLego)).isEqualTo(savedLego);

		Mockito.verify(this.repo, Mockito.times(1)).save(newLego);

	}

	@Test
	void testGetAll() {

		List<Lego> testLego = List.of(new Lego(1, "Friends", 9844, "Beach House", 2020));

		Mockito.when(this.repo.findAll()).thenReturn(testLego);

		assertThat(this.service.getAllLegoKits()).isEqualTo(testLego);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();

	}

	@Test
	void testGetAllByKitName() {

		List<Lego> testLego = List.of(new Lego(1, "Friends", 9844, "Beach House", 2020));

		String search = "Beach House";

		Mockito.when(this.repo.findByKitNameIgnoreCase(search)).thenReturn(testLego);

		assertThat(this.service.getByKitName(search)).isEqualTo(testLego);

		Mockito.verify(this.repo, Mockito.times(1)).findByKitNameIgnoreCase(search);

	}

	@Test
	void testUpdate() {
		// GIVEN
		int id = 1;

		Lego testLego = new Lego(id, "Friends", 9844, "Beach House", 2020); // returned by FindById
		Lego testNewLego = new Lego(id, "Ninjago", 3776, "Karate Fight", 2017); // new kit date

		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testLego)); // don't worry about this for now tbh
		Mockito.when(this.repo.save(new Lego(id, "Ninjago", 3776, "Karate Fight", 2017))).thenReturn(testNewLego);

		Lego actual = this.service.replaceLegoKit(id, testNewLego);
		// THEN
		assertThat(actual).isEqualTo(testNewLego);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Lego(id, "Ninjago", 3776, "Karate Fight", 2017));
	}

	@Test
	void testDeleteSucceeds() {
		int id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteLegoKit(id)).isEqualTo("Deleted: " + id);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

	@Test
	void testDeleteFails() {
		int id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(true);

		assertThat(this.service.deleteLegoKit(id)).isEqualTo("Not Deleted: " + id);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

}
