package it.prova.gestioneordini.dao.ordine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.crypto.Data;

import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
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

	public List<Ordine> findAllByCategoria(Categoria categoriaInstance) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery(
				"select o from Ordine o join o.articoli a join a.categorie c where c.id= :idCategoria ", Ordine.class);
		query.setParameter("idCategoria", categoriaInstance.getId());
		return query.getResultList();
	}

	@Override
	public Ordine ordinePiùRecenteInTerminiDiDataSpedizione(Categoria categoriaInstance) throws Exception {
		// TypedQuery<Date> data=
		// entityManager.createQuery("select(max(o.dataSpedizione)) from Ordine o",
		// Date.class);

		TypedQuery<Ordine> query = entityManager.createQuery(
				"select distinct o from Ordine o where o.dataSpedizione= (select max(o.dataSpedizione) from Ordine o join o.articoli a join a.categorie c where c.id=:idC)",
				Ordine.class);
		query.setParameter("idC", categoriaInstance.getId());
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<String> findAllIndirizziByStringaCheContiene(String stringaInput) throws Exception {
		TypedQuery<String> query = entityManager.createQuery(
				"select distinct o.indirizzoDestinatario from Ordine o join o.articoli a where a.numeroSeriale like :stringa ",
				String.class);
		query.setParameter("stringa", "%" + stringaInput + "%");
		return query.getResultList();
	}

}
