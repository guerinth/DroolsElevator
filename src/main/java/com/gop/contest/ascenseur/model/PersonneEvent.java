package com.gop.contest.ascenseur.model;

public class PersonneEvent {

	private int cabin;
	private int floorToGo;

	public PersonneEvent(int cabin) {
		super();
		this.cabin = cabin;
	}

	public int getCabin() {
		return cabin;
	}

	public void setCabin(int cabin) {
		this.cabin = cabin;
	}

	public int getFloorToGo() {
		return floorToGo;
	}

	public void setFloorToGo(int floorToGo) {
		this.floorToGo = floorToGo;
	}

	@Override
	public String toString() {
		return "PersonneEvent [cabin=" + cabin + ", floorToGo=" + floorToGo
				+ "]";
	}

}
