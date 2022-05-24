package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.articolo.ArticoloDAO;
import it.prova.gestioneordini.model.Articolo;

public interface ArticoloService {
	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public Articolo caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public void aggiorna(Articolo cdInstance) throws Exception;

	public void inserisciNuovo(Articolo cdInstance) throws Exception;

	public void rimuovi(Long idCd) throws Exception;

	public void setArticoloDAO(ArticoloDAO articoloDAO);
}
