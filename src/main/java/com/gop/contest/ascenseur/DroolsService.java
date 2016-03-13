package com.gop.contest.ascenseur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.DefaultRuleRuntimeEventListener;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.gop.contest.ascenseur.model.Ascenseur;
import com.gop.contest.ascenseur.model.Personne;
import com.gop.contest.ascenseur.model.PersonneEvent;
import com.gop.contest.ascenseur.model.Tick;

public class DroolsService {

	private boolean trace = true;
	private KieSession ksession;
	private static final DroolsService instance = new DroolsService();

	private DroolsService() {
		KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
		ksession = kc.newKieSession("ascenseurKS");

		if (trace) {
			ksession.addEventListener(new DefaultAgendaEventListener() {

				@Override
				public void afterMatchFired(final AfterMatchFiredEvent event) {
					super.afterMatchFired(event);
					System.out.println("  Règle déclenchée : "
							+ event.getMatch().getRule().getName());
				}
			});
			ksession.addEventListener(new DefaultRuleRuntimeEventListener() {

				@Override
				public void objectInserted(final ObjectInsertedEvent event) {
					super.objectInserted(event);
					System.err.println("   Objet inséré : "
							+ event.getObject().toString());
				}

				@Override
				public void objectUpdated(final ObjectUpdatedEvent event) {
					super.objectUpdated(event);
					System.err.println("   Objet modifié : "
							+ event.getObject().toString());
				}
			});

		}
	}

	public static DroolsService getInstance() {
		return instance;
	}

	public void reset(int lowerFloor, int higherFloor, int cabinSize,
			int cabinCount) {
		for (FactHandle handle : ksession.getFactHandles()) {
			ksession.delete(handle);
		}
		for (int cabin = 1; cabin <= cabinCount; cabin++) {
			Ascenseur ascenseur = new Ascenseur();
			ascenseur.setId(cabin);
			ascenseur.setCapaciteMax(cabinSize);
			ascenseur.setEtageMax(higherFloor);
			ascenseur.setEtageMin(lowerFloor);
			ksession.insert(ascenseur);
		}
	}

	public void call(Integer atFloor, DirectionEnum to) {
		Personne personne = new Personne();
		personne.setEtageAppel(atFloor);
		personne.setDirectionCible(to);
		ksession.insert(personne);
	}

	public void go(Integer floorToGo, Integer cabin) {
		EntryPoint ep = this.ksession.getEntryPoint("ChoixEtageStream");
		PersonneEvent userEvt = new PersonneEvent(cabin);
		userEvt.setFloorToGo(floorToGo);
		ep.insert(userEvt);
	}

	public void userHasEntered(int cabin) {
		EntryPoint ep = this.ksession.getEntryPoint("EntreeStream");
		PersonneEvent userEvt = new PersonneEvent(cabin);
		ep.insert(userEvt);
	}

	public void userHasExited(int cabin) {
		EntryPoint ep = this.ksession.getEntryPoint("SortieStream");
		PersonneEvent userEvt = new PersonneEvent(cabin);
		ep.insert(userEvt);
	}

	public String nextCommand() {
		StringBuilder result = new StringBuilder();
		List<Ascenseur> ascenseurs = new ArrayList<Ascenseur>();

		EntryPoint ep = this.ksession.getEntryPoint("NextCommandStream");
		ep.insert(new Tick());

		this.ksession.fireAllRules();

		QueryResults queryResults = this.ksession
				.getQueryResults("QuelSontlesOrdres");
		for (QueryResultsRow ordre : queryResults) {
			ascenseurs.add((Ascenseur) ordre.get("$ascenseur"));
			// System.out.println("--> Ordre : " + ordre.get("$souhait"));
		}

		Collections.sort(ascenseurs, new Comparator<Ascenseur>() {
			public int compare(Ascenseur s1, Ascenseur s2) {
				return s1.getId() - s2.getId();
			}
		});

		for (Ascenseur asc : ascenseurs) {
			if (result.length() > 0)
				result.append('\n');
			result.append(asc.getEtat().toString());
		}
		if (result.length() == 0) {
			result.append(CommandEnum.NOTHING.toString());
		}

		return result.toString();
	}
}
