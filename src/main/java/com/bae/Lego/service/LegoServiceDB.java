package com.bae.Lego.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.Lego.data.Lego;
import com.bae.Lego.data.LegoRepo;

@Service // future functionality - don't use
@Primary // tells Spring just to make this one
public class LegoServiceDB implements LegoKitService {

	private LegoRepo repo;

	public LegoServiceDB(LegoRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Lego createLegoKit(Lego legoKit) {
		return this.repo.save(legoKit);
	}

	@Override
	public List<Lego> getAllLegoKits() {
		return this.repo.findAll();
	}

	@Override
	public List<Lego> getByKitName(String kitName) {
		return this.repo.findByKitNameIgnoreCase(kitName);

	}

	@Override
	public Lego getLegoKit(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public Lego replaceLegoKit(int id, Lego newlegoKit) {
		// TODO Auto-generated method stub
		Lego found = this.repo.findById(id).get();

		System.out.println("FOUND: " + found);

		// modify record
		found.setSeriesName(newlegoKit.getSeriesName());
		found.setKitNumber(newlegoKit.getKitNumber());
		found.setKitName(newlegoKit.getKitName());
		found.setReleaseYear(newlegoKit.getReleaseYear());

		System.out.println("FOUND AFTER UPDATE: " + found);
		// save it back to overwrite it
		Lego updated = this.repo.save(found);
		System.out.println("UPDATED: " + updated);
		return updated;

	}

	@Override
	public String deleteLegoKit(int id) {
		this.repo.deleteById(id);

		if (this.repo.existsById(id)) {
			return "Not Deleted: " + id;

		} else {
			return "Deleted: " + id;
		}

	}

}