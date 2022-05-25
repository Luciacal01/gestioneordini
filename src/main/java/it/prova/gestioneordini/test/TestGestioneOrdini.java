package it.prova.gestioneordini.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Ordine;
import it.prova.gestioneordini.service.ArticoloService;
import it.prova.gestioneordini.service.CategoriaService;
import it.prova.gestioneordini.service.MyServiceFactory;
import it.prova.gestioneordini.service.OrdineService;

public class TestGestioneOrdini {

	public static void main(String[] args) {
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {
			testInserisciNuovoOrdine(ordineServiceInstance);
			System.out.println("nella tabella ordini ci sono: " + ordineServiceInstance.listAll().size() + " elementi");
			System.out.println(
					"nella tabella articoli ci sono: " + articoloServiceInstance.listAll().size() + " elementi");
			System.out.println(
					"nella tabella categorie ci sono: " + categoriaServiceInstance.listAll().size() + " elementi");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	public static void testInserisciNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".........testInserisciNuovoOrdine inizio.........");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2020");
		Ordine ordineInstance = new Ordine("Giulio Orlandi", "via Del Corso, 14", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		System.out.println(".........testInserisciNuovoOrdine inizio.........");
	}

}
