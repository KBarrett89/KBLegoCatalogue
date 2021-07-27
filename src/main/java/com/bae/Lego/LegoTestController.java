package com.bae.Lego;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}