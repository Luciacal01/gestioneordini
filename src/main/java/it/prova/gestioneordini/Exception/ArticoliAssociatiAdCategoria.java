package it.prova.gestioneordini.Exception;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordini.dao.articolo.ArticoloDAO;
import it.prova.gestioneordini.model.Articolo;

public class ArticoliAssociatiAdCategoria extends RuntimeException {
	public ArticoliAssociatiAdCategoria(String stringaDiErrore) {
		super(stringaDiErrore);
	}
}