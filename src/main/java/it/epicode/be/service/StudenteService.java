package it.epicode.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Studente;
import it.epicode.be.repository.StudenteRepository;

@Service
public class StudenteService {

	@Autowired
	StudenteRepository studenteRepo;

	public Studente save(Studente studente) {
		studenteRepo.save(studente);
		return studente;

	}

	public void delete(Long id) {
		studenteRepo.deleteById(id);
	}

	public Studente update(Studente studente, Long id) {
		Optional<Studente> studenteResult = studenteRepo.findById(id);
		if (studenteResult.isPresent()) {
			Studente studenteUpdate = studenteResult.get();
			studenteUpdate.setNome(studente.getNome());
			studenteUpdate.setCognome(studente.getCognome());
			studenteUpdate.setCitta(studente.getCitta());
			studenteUpdate.setDataNascita(studente.getDataNascita());
			studenteUpdate.setEmail(studente.getEmail());
			studenteUpdate.setIndirizzo(studente.getIndirizzo());
			studenteUpdate.setMatricola(studente.getMatricola());
			studenteUpdate.setCorsoDiLaurea(studente.getCorsoDiLaurea());
			return studenteRepo.save(studenteUpdate);
			
		}
		return null; // TODO: Implementare Exception.
	}

	public Page<Studente> findAll(Pageable pageable) {

		return studenteRepo.findAll(pageable);

	}

	public Optional<Studente> findById(Long id) {
		return studenteRepo.findById(id);
	}
}
