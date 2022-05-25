package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.categoria.CategoriaDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface CategoriaService {
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public Categoria caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Long idCategoria) throws Exception;

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	public void aggiungiArticolo(Articolo articoloInstance, Categoria categoriaIntsance) throws Exception;

	public List<String> cercaCodiciDelleCategorieCheHannoOrdiniEffettutatiAFebraio() throws Exception;

	public List<Categoria> cercaCategoriePerOrdini(Categoria categoriaInstance, Ordine ordineInstance) throws Exception;
}
