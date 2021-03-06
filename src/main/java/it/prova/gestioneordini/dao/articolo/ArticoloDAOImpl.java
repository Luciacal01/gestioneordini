package it.prova.gestioneordini.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("problema valore in input");
		}

		articoloInstance = entityManager.merge(articoloInstance);

	}

	@Override
	public void insert(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("problema valore in input");
		}
		entityManager.persist(articoloInstance);
	}

	@Override
	public void delete(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("problema valore in input");
		}
		entityManager.remove(entityManager.merge(articoloInstance));
	}

	public Articolo findByIdFetchingGeneri(Long id) throws Exception {
		TypedQuery<Articolo> query = entityManager.createQuery(
				"select a from Articoli left join fetch a.categorie c where a.id = :idArticolo", Articolo.class);
		query.setParameter("idArticolo", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public Long sumOfAllPriceLegatiAdUnaCategoria(Categoria categoriaInstance) throws Exception {
		TypedQuery<Long> query = entityManager.createQuery(
				"select SUM(a.prezzoSingolo) from Articolo a join a.categorie c where c.id= :idCat", Long.class);
		query.setParameter("idCat", categoriaInstance.getId());
		return query.getSingleResult();

	}

	@Override
	public Long sumOfAllPriceSpeditiAMarioRossi() throws Exception {
		TypedQuery<Long> query = entityManager.createQuery(
				"select SUM(a.prezzoSingolo) from Articolo a join a.ordine o where o.nomeDestinatario= 'Mario Rossi'",
				Long.class);
		return query.getSingleResult();
	}

}
