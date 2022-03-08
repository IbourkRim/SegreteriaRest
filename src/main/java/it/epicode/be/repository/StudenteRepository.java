package it.epicode.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Long>{

}
