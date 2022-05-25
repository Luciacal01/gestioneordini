package it.prova.gestioneordini.dao.categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		categoriaInstance = entityManager.merge(categoriaInstance);
	}

	@Override
	public void insert(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(categoriaInstance);
	}

	@Override
	public void delete(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(categoriaInstance));
	}

	public Categoria findByIdFetchingArticoli(Long id) throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery(
				"select c from Categoria c left join fetch c.articoli a where c.id = :idArticolo", Categoria.class);
		query.setParameter("idArticolo", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<Categoria> findAllByOrdine(Ordine ordineInput) throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery(
				"select distinct c from Categoria c join c.articoli a join a.ordine o where o.id= :idOrdine",
				Categoria.class);
		query.setParameter("idOrdine", ordineInput.getId());
		return query.getResultList();
	}

	@Override
	public List<String> findAllCodiciByOrdiniEffettutatiAFebraio() throws Exception {
		TypedQuery<String> query = entityManager.createQuery(
				"select distinct c.codice from Categoria c join c.articoli a join a.ordine o where o.dataSpedizione like '2022-02%' ",
				String.class);
		return query.getResultList();
	}

}
