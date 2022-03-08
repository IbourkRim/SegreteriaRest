package it.epicode.be.util;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.epicode.be.model.ArchivioDati;
import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.model.Studente;
import it.epicode.be.repository.CorsoLaureaRepository;
import it.epicode.be.repository.StudenteRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

	@Autowired
	StudenteRepository studenteRepo;

	@Autowired
	CorsoLaureaRepository corsodilaureaRepo;

	@Autowired
	ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {

		ArchivioDati data = ctx.getBean(ArchivioDati.class);

		initCorsiDiLaurea(data);
		initStudenti(data);

	}

	private void initStudenti(ArchivioDati data) {

		Studente studente = new Studente();
		studente.setMatricola("BA345");
		studente.setNome("Mario");
		studente.setCognome("Rossi");
		studente.setDataNascita(LocalDate.parse("1999-12-21"));
		studente.setIndirizzo("Via Nazionale, 35");
		studente.setCitta("Roma");
		studente.setEmail("m.rossi@email.em");
		studente.setCorsoDiLaurea(data.getCorsiDiLaurea().get("A234"));
		data.getStudenti().put(studente.getMatricola(), studente);
		log.info("Saved studente: " + studente.toString());
		studenteRepo.save(studente);

		studente = new Studente();
		studente.setMatricola("AT281");
		studente.setNome("Gianni");
		studente.setCognome("Verdone");
		studente.setDataNascita(LocalDate.parse("2001-10-11"));
		studente.setIndirizzo("Via Canne, 5");
		studente.setCitta("Latina");
		studente.setEmail("g.verdi@email.em");
		studente.setCorsoDiLaurea(data.getCorsiDiLaurea().get("D457"));
		data.getStudenti().put(studente.getMatricola(), studente);
		log.info("Saved studente: " + studente.toString());
		studenteRepo.save(studente);
	}

	private void initCorsiDiLaurea(ArchivioDati data) {

		CorsoDiLaurea corso = new CorsoDiLaurea();
		corso.setCodice("A234");
		corso.setNome("Informatica");
		corso.setIndirizzo("Tecnologie per il Web");
		corso.setNumeroEsami(20);
		data.getCorsiDiLaurea().put(corso.getCodice(), corso);
		log.info("Saved corso di laurea: " + corso.toString());
		// corsodilaureaRepo.save(corso); Commentato perché Cascade.PERSIST attivato nel model. (La entity viene salvata a cascata perché dipendente da Studente.)

		corso = new CorsoDiLaurea();
		corso.setCodice("D457");
		corso.setNome("Fisica");
		corso.setIndirizzo("");
		corso.setNumeroEsami(18);
		data.getCorsiDiLaurea().put(corso.getCodice(), corso);
		log.info("Saved corso di laurea: " + corso.toString());
	}

}
