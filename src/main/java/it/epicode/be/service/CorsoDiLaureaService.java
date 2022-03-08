package it.epicode.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.repository.CorsoLaureaRepository;

@Service
public class CorsoDiLaureaService {

	@Autowired
	CorsoLaureaRepository corsodilaureaRepo;

	public CorsoDiLaurea save(CorsoDiLaurea corso) {
		corsodilaureaRepo.save(corso);
		return corso;
	}

	public void delete(Long id) {
		corsodilaureaRepo.deleteById(id);
	}

	public CorsoDiLaurea update(CorsoDiLaurea corso, Long id) {
		Optional<CorsoDiLaurea> corsoResult = corsodilaureaRepo.findById(id);
		if (corsoResult.isPresent()) {
			CorsoDiLaurea corsoUpdate = corsoResult.get();
			corsoUpdate.setNome(corso.getNome());
			corsoUpdate.setCodice(corso.getCodice());
			corsoUpdate.setIndirizzo(corso.getIndirizzo());
			corsoUpdate.setNumeroEsami(corso.getNumeroEsami());
			corsoUpdate.setStudenti(corso.getStudenti());
			return corsodilaureaRepo.save(corsoUpdate);

		}
		return null; // TODO: Implementare Exception.
	}

	public Page<CorsoDiLaurea> findAll(Pageable pageable) {
		return corsodilaureaRepo.findAll(pageable);
	}

	public Optional<CorsoDiLaurea> findById(Long id) {
		return corsodilaureaRepo.findById(id);
	}

}
