package com.gop.contest.ascenseur.model;

import com.gop.contest.ascenseur.DirectionEnum;

public class Personne {
	private int id;
	private int etageAppel;
	private DirectionEnum directionCible;
	private Ascenseur ascenseur;
	private Integer etageCible;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ascenseur getAscenseur() {
		return ascenseur;
	}

	public void setAscenseur(Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}

	public Integer getEtageCible() {
		return etageCible;
	}

	public void setEtageCible(Integer etageCible) {
		this.etageCible = etageCible;
	}

	public int getEtageAppel() {
		return etageAppel;
	}

	public void setEtageAppel(int etageAppel) {
		this.etageAppel = etageAppel;
	}

	public DirectionEnum getDirectionCible() {
		return directionCible;
	}

	public void setDirectionCible(DirectionEnum directionCible) {
		this.directionCible = directionCible;
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", etageAppel=" + etageAppel
				+ ", directionCible=" + directionCible + ", ascenseur="
				+ ascenseur + ", etageCible=" + etageCible + "]";
	}

}
