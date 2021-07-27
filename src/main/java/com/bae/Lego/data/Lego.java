package com.bae.Lego.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // tells spring that this class represents a table in the database
public class Lego {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String seriesName;
	private int kitNumber;
	private String kitName;
	private int releaseYear;

	public Lego(String seriesName, int kitNumber, String kitName, int releaseYear) {
		super();
		this.seriesName = seriesName;
		this.kitNumber = kitNumber;
		this.kitName = kitName;
		this.releaseYear = releaseYear;
	}

	public Lego(int id, String seriesName, int kitNumber, String kitName, int releaseYear) {
		super();
		this.id = id;
		this.seriesName = seriesName;
		this.kitNumber = kitNumber;
		this.kitName = kitName;
		this.releaseYear = releaseYear;

	}

	@Override
	public int hashCode() {
		return Objects.hash(id, kitName, kitNumber, releaseYear, seriesName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lego other = (Lego) obj;
		return id == other.id && Objects.equals(kitName, other.kitName) && kitNumber == other.kitNumber
				&& releaseYear == other.releaseYear && Objects.equals(seriesName, other.seriesName);
	}

	public Lego() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public int getKitNumber() {
		return kitNumber;
	}

	public void setKitNumber(int kitNumber) {
		this.kitNumber = kitNumber;
	}

	public String getKitName() {
		return kitName;
	}

	public void setKitName(String kitName) {
		this.kitName = kitName;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	@Override
	public String toString() {
		return "Lego [Series Name=" + seriesName + ", Kit Number=" + kitNumber + ", Kit Name=" + kitName
				+ ", Release Year=" + releaseYear + "]";

	}
}