package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public Ordine caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public void aggiorna(Ordine cdInstance) throws Exception;

	public void inserisciNuovo(Ordine cdInstance) throws Exception;

	public void rimuovi(Long idCd) throws Exception;

	public void rimuoviArticolo(Ordine ordineInstance, Articolo articoloInstance) throws Exception;

	public void setOrdineDAO(OrdineDAO ordineDAO);

	public void aggiungiArticolo(Ordine ordineInstance, Articolo articoloInstance) throws Exception;
}
