package it.prova.gestioneordini.dao.articolo;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	public Articolo findByIdFetchingGeneri(Long id) throws Exception;

	public Long sumOfAllPriceLegatiAdUnaCategoria(Categoria categoriaInstance) throws Exception;
	
	public Long sumOfAllPriceSpeditiAMarioRossi() throws Exception;
}
