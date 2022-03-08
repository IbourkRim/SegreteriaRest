package it.epicode.be.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Studente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "specificare la matricola")
	private String matricola;

    @NotEmpty(message = "specificare il nome")
	private String nome;
	
    @NotEmpty(message = "specificare il cognome")
	private String cognome;
	
    //@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascita;
	
    @NotEmpty(message = "specificare l'indirizzo di residenza")
	private String indirizzo;
	
    @NotEmpty(message = "specificare la città di residenza")
	private String citta;
	
    @NotEmpty(message = "specificare l'indirizzo email")
	@Email
	private String email;
    
    @NotNull
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Corso di laurea "eredita" il salvataggio da Studente che lo richiama.
    
    private CorsoDiLaurea corsoDiLaurea;

}
