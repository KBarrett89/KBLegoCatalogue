package com.bae.Lego.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.Lego.data.Lego;

@Service

public class LegoServiceList implements LegoKitService {
	private List<Lego> legoKits = new ArrayList<>();

	@Override
	public Lego createLegoKit(Lego lego) {
		this.legoKits.add(lego);
		return this.legoKits.get(this.legoKits.size() - 1);
	}

	@Override
	public List<Lego> getAllLegoKits() {
		return this.legoKits;
	}

	@Override
	public Lego getLegoKit(int id) {
		Lego found = this.legoKits.get(id);
		return found;
	}

	@Override
	public Lego replaceLegoKit(int id, Lego newLegoKit) {
		return this.legoKits.set(id, newLegoKit);
	}

	@Override
	public String deleteLegoKit(int id) {
		this.legoKits.remove(id);

		return "Deleted Lego Kit at index: " + id;
	}

	@Override
	public List<Lego> getByKitName(String kitName) {
		// TODO Auto-generated method stub
		return null;
	}

}