package com.gop.contest.ascenseur.model;

import java.util.ArrayList;
import java.util.List;

import com.gop.contest.ascenseur.CommandEnum;

public class Ascenseur {
	private int id;
	private CommandEnum etat = CommandEnum.CLOSE;
	private CommandEnum etatPrecedent = CommandEnum.NOTHING;
	private CommandEnum etatPorte = CommandEnum.CLOSE;
	private boolean etatCalcule = false;
	private int etageMin;
	private int etageMax;
	private int etageEnCours;
	private int capaciteMax;
	private List<Personne> personnes = new ArrayList<Personne>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CommandEnum getEtat() {
		return etat;
	}

	public void setEtat(CommandEnum etat) {
		etatPrecedent = this.etat;
		this.etat = etat;
	}

	public CommandEnum getEtatPrecedent() {
		return etatPrecedent;
	}

	public void setEtatPrecedent(CommandEnum etatPrecedent) {
		this.etatPrecedent = etatPrecedent;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

	public int getEtageMin() {
		return etageMin;
	}

	public void setEtageMin(int etageMin) {
		this.etageMin = etageMin;
	}

	public int getEtageMax() {
		return etageMax;
	}

	public void setEtageMax(int etageMax) {
		this.etageMax = etageMax;
	}

	public int getEtageEnCours() {
		return etageEnCours;
	}

	public void setEtageEnCours(int etageEnCours) {
		this.etageEnCours = etageEnCours;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public boolean isEtatCalcule() {
		return etatCalcule;
	}

	public void setEtatCalcule(boolean etatCalcule) {
		this.etatCalcule = etatCalcule;
	}

	public CommandEnum getEtatPorte() {
		return etatPorte;
	}

	public void setEtatPorte(CommandEnum etatPorte) {
		this.etatPorte = etatPorte;
	}

	@Override
	public String toString() {
		return "Ascenseur [id=" + id + ", etat=" + etat + ", etatPrecedent="
				+ etatPrecedent + ", etatPorte=" + etatPorte + ", capaciteMax="
				+ capaciteMax + ", etageMin=" + etageMin + ", etageMax="
				+ etageMax + ", etageEnCours=" + etageEnCours
				+ ", Nb personnes=" + personnes.size() + "]";
	}
}
