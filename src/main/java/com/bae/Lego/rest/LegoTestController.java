package com.bae.Lego.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.Lego.data.Lego;
import com.bae.Lego.service.LegoKitService;

@RestController
@CrossOrigin
public class LegoTestController {

	private LegoKitService service;

	public LegoTestController(LegoKitService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/createLegoKit")
	public ResponseEntity<Lego> createLegoKit(@RequestBody Lego LegoKit) {
		Lego created = this.service.createLegoKit(LegoKit);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getAllLegoKits")
	public List<Lego> getAllLegoKits() {
		return this.service.getAllLegoKits();
	}

	@GetMapping("/getByKitName/{kitName}")
	public List<Lego> getByKitName(@PathVariable String kitName) {
		return this.service.getByKitName(kitName);
	}

	@GetMapping("/getLegoKit/{id}")
	public Lego getLegoKit(@PathVariable int id) {
		return this.service.getLegoKit(id);
	}

	@PutMapping("/replaceLegoKit/{id}")
	public ResponseEntity<Lego> replaceLegoKit(@PathVariable int id, @RequestBody Lego newLegoKit) {
		Lego body = this.service.replaceLegoKit(id, newLegoKit);
		return new ResponseEntity<Lego>(body, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteLegoKit/{id}")
	public ResponseEntity<String> deleteLegoKit(@PathVariable int id) {
		String body = this.service.deleteLegoKit(id);
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);

	}
}