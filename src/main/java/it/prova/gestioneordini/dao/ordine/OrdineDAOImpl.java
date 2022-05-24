package it.prova.gestioneordini.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("form Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	public Ordine findByIdFetchingArticolo(Long id) throws Exception {
		TypedQuery<Ordine> query = entityManager
				.createQuery("select o from  Ordine o join fetch o.articoli a where o.id= :idOrdine ", Ordine.class);
		query.setParameter("idOrdine", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public void update(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("problema valore in input");
		}

		ordineInstance = entityManager.merge(ordineInstance);

	}

	@Override
	public void insert(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("problema valore in input");
		}

		entityManager.persist(ordineInstance);

	}

	@Override
	public void delete(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("problema valore in input");
		}

		entityManager.remove(entityManager.merge(ordineInstance));

	}

}
