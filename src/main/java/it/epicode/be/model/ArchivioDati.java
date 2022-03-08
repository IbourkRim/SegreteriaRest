package it.epicode.be.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArchivioDati {

	private Map<String, Studente> studenti = new HashMap<>();
	private Map<String, CorsoDiLaurea> corsiDiLaurea = new HashMap<>();

	public Map<String, Studente> getStudenti() {
		return studenti;
	}

	public Optional<Studente> getStudenteByMatricola(String matricola) {
		Optional<Studente> studente = Optional.ofNullable(studenti.get(matricola));
		return studente;
	}

	public boolean addStudente(Studente studente) {
		if (!studenti.containsKey(studente.getMatricola())) {
			studenti.put(studente.getMatricola(), studente);
			return true;
		} else
			return false;
	}

	public boolean aggiornaStudente(Studente studente) {
		if (studenti.containsKey(studente.getMatricola())) {
			studenti.put(studente.getMatricola(), studente);
			return true;
		} else
			return false;
	}

	public boolean deleteStudente(String matricola) {
		if (studenti.containsKey(matricola)) {
			studenti.remove(matricola);
			return true;
		} else
			return false;
	}

	public Map<String, CorsoDiLaurea> getCorsiDiLaurea() {
		return corsiDiLaurea;
	}

	public Optional<CorsoDiLaurea> getCorsoDiLaureaByCodice(String codice) {
		Optional<CorsoDiLaurea> corsoDiLaurea = Optional.ofNullable(corsiDiLaurea.get(codice));
		return corsoDiLaurea;
	}

	public boolean addCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea) {
		if (!corsiDiLaurea.containsKey(corsoDiLaurea.getCodice())) {
			corsiDiLaurea.put(corsoDiLaurea.getCodice(), corsoDiLaurea);
			return true;
		} else
			return false;
	}

	public boolean aggiornaCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea) {
		if (corsiDiLaurea.containsKey(corsoDiLaurea.getCodice())) {
			corsiDiLaurea.put(corsoDiLaurea.getCodice(), corsoDiLaurea);
			return true;
		} else
			return false;
	}

	public boolean deleteCorsoDiLaurea(String codice) {
		if (corsiDiLaurea.containsKey(codice)) {
			corsiDiLaurea.remove(codice);
			return true;
		} else
			return false;
	}
}
