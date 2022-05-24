package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineService {

	public List<OrdineService> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public Ordine caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public void aggiorna(Ordine cdInstance) throws Exception;

	public void inserisciNuovo(Ordine cdInstance) throws Exception;

	public void rimuovi(Long idCd) throws Exception;
	
	public void setOrdineDAO(OrdineDAO ordineDAO);
}
