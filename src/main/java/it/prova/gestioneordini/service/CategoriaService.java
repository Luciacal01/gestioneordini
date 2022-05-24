package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.categoria.CategoriaDAO;
import it.prova.gestioneordini.model.Categoria;

public interface CategoriaService {
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public Categoria caricaSingoloElementoEagerGeneri(Long id) throws Exception;

	public void aggiorna(Categoria cdInstance) throws Exception;

	public void inserisciNuovo(Categoria cdInstance) throws Exception;

	public void rimuovi(Long idCd) throws Exception;

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);
}
