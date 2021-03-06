package it.prova.gestioneordini.dao.ordine;

import java.util.List;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {
	public Ordine findByIdFetchingArticolo(Long id) throws Exception;

	public List<Ordine> findAllByCategoria(Categoria categoriaInstance) throws Exception;
	
	public Ordine ordinePi├╣RecenteInTerminiDiDataSpedizione(Categoria categoriaInstance) throws Exception;
	
	public List<String> findAllIndirizziByStringaCheContiene(String stringaInput) throws Exception;
}
