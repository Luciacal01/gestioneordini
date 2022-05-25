package it.prova.gestioneordini.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
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
			// testInserisciNuovoOrdine(ordineServiceInstance);
			System.out.println("nella tabella ordini ci sono: " + ordineServiceInstance.listAll().size() + " elementi");
			System.out.println(
					"nella tabella articoli ci sono: " + articoloServiceInstance.listAll().size() + " elementi");
			System.out.println(
					"nella tabella categorie ci sono: " + categoriaServiceInstance.listAll().size() + " elementi");

			// testAggiornaOrdine(ordineServiceInstance);

			// testAggiungiArticoloAdOrdine(articoloServiceInstance, ordineServiceInstance);
			// testRimuoviArticoloAdOrdine(articoloServiceInstance, ordineServiceInstance);

			// testAggiungiCategoriaADArticolo(articoloServiceInstance,
			// categoriaServiceInstance);
			// testAggiungiArticoloACategoria(articoloServiceInstance,
			// categoriaServiceInstance);

			// testCercaPerCategoria(categoriaServiceInstance, ordineServiceInstance);

			// testCercaCategoriaPerOrdine(categoriaServiceInstance,
			// articoloServiceInstance, ordineServiceInstance);
			// testSommaDeiPrezziDegliArticoliLegatiAllaCategoria(categoriaServiceInstance,
			// articoloServiceInstance,
			// ordineServiceInstance);

			testOrdineConDataSpedizionePiùRecente(categoriaServiceInstance, articoloServiceInstance,
					ordineServiceInstance);

			// testCercaCodiciDelleCategorieCheHannoOrdiniEffettutatiAFebraio(categoriaServiceInstance,
			// articoloServiceInstance, ordineServiceInstance);
			// testSommaDeiPrezziDegliArticoliOrdinatiDaMarioRossi(categoriaServiceInstance,
			// articoloServiceInstance,
			// ordineServiceInstance);

			// testListaIndirizziCheCheHannoIndirizzoSeriale(categoriaServiceInstance,
			// articoloServiceInstance,
			// ordineServiceInstance);
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

	public static void testAggiornaOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println("........testAggiornaOrdine inizio.......");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("10-12-2019");
		Ordine ordineDaModificare = new Ordine("Francesca Ruggeri", "Piazza Bologna, 112", dataSpedizione);
		ordineDaModificare.setId(2L);

		ordineServiceInstance.aggiorna(ordineDaModificare);

		System.out.println("........testAggiornaOrdine PASSED.......");
	}

	public static void testAggiungiArticoloAdOrdine(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		System.out.println("........testAggiungiArticoloAdOrdine inizio........");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2020");
		Ordine ordineDaCollegare = new Ordine("Giulio Orlandi", "via Del Corso, 14", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineDaCollegare);

		if (ordineDaCollegare.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");
		long nowInMillisecondi = new Date().getTime();
		Articolo nuovoArticolo = new Articolo("Set Matite", "MATY69", 3,
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-2018"));

		nuovoArticolo.setOrdine(ordineDaCollegare);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);

		if (nuovoArticolo.getId() == null)
			throw new RuntimeException("testAggiungiArticoloAdOrdine FAILED, articolo non aggiunto");

		System.out.println("........testAggiungiArticoloAdOrdine inizio........");
	}

	public static void testRimuoviArticoloAdOrdine(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testRimuoviArticoloAdOrdine inizio........");

		Articolo nuovoArticolo = new Articolo("Set Matite", "MATY69", 3,
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-2018"));

		nuovoArticolo.setOrdine(ordineServiceInstance.listAll().get(1));

		articoloServiceInstance.inserisciNuovo(nuovoArticolo);

		System.out.println("........testRimuoviArticoloAdOrdine PASSED........");
	}

	public static void testAggiungiCategoriaADArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".........testAggiungiCategoriaADArticolo inizio.........");

		Articolo articoloDaCollegare = articoloServiceInstance.listAll().get(1);

		Categoria categoriaDaCollegare = new Categoria("cartoleria", "CART00");
		categoriaServiceInstance.inserisciNuovo(categoriaDaCollegare);
		if (categoriaDaCollegare.getId() == null)
			throw new RuntimeException("test FAILED, categoria non aggiunta");

		articoloServiceInstance.aggiungiCategoria(articoloDaCollegare, categoriaDaCollegare);

		Articolo articoloReload = articoloServiceInstance.caricaSingoloElemento(articoloDaCollegare.getId());
		if (articoloReload.getCategorie() == null)
			throw new RuntimeException("test FILED, categoria non aggiunta");

		System.out.println(".........testAggiungiArticoloACategoria PASSED.........");
	}

	public static void testAggiungiArticoloACategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".........testAggiungiCategoriaADArticolo inizio.........");

		List<Categoria> listaCategorie = categoriaServiceInstance.listAll();
		if (listaCategorie.size() == 0)
			throw new RuntimeException("test FAILED: non ci sono categorie");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		if (listaArticoli.size() == 0)
			throw new RuntimeException("test FAILED: non ci sono articoli");

		Categoria categoriaDaCollegare = listaCategorie.get(2);
		Articolo articoloDaCollegare = listaArticoli.get(0);
		categoriaServiceInstance.aggiungiArticolo(articoloDaCollegare, categoriaDaCollegare);

		Categoria categoriaReload = categoriaServiceInstance.caricaSingoloElemento(categoriaDaCollegare.getId());
		if (categoriaReload.getArticoli() == null)
			throw new RuntimeException("test FAILED, non sono stati collegati");

		Articolo articoloReload = articoloServiceInstance.caricaSingoloElemento(articoloDaCollegare.getId());
		if (articoloReload.getCategorie() == null)
			throw new RuntimeException("test FILED, categoria non aggiunta");

		System.out.println(".........testAggiungiArticoloACategoria PASSED.........");
	}

	public static void testCercaPerCategoria(CategoriaService categoriaServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		System.out.println("..........testCercaPerCategoria inizio.........");

		Categoria categoria = categoriaServiceInstance.listAll().get(1);

		List<Ordine> ordiniCercatiPerCategoria = ordineServiceInstance.cercaPerCategoria(categoria);

		if (ordiniCercatiPerCategoria.size() != 1)
			throw new RuntimeException("test FAILED non sono stati trovati ordini");

		System.out.println("..........testCercaPerCategoria PASSED..........");
	}

	public static void testCercaCategoriaPerOrdine(CategoriaService categoriaServiceInstance,
			ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("............testCercaCategoriaPerOrdine inizio............");

		Categoria categoria = new Categoria("abbigliamento", "ABIG89");
		categoriaServiceInstance.inserisciNuovo(categoria);

		if (categoria.getId() == null)
			throw new RuntimeException("test FAILED, categoria non inserita");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2020");
		Ordine ordineInstance = new Ordine("Giulio Orlandi", "via Del Corso, 14", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo = new Articolo("gonna a balze", "GGGG58", 9,
				new SimpleDateFormat("dd-MM-yyyy").parse("23-03-2018"));
		articolo.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articolo);

		articoloServiceInstance.aggiungiCategoria(articolo, categoria);

		List<Categoria> listaCategoriePerOrdine = categoriaServiceInstance.cercaCategoriePerOrdini(categoria,
				ordineInstance);

		if (listaCategoriePerOrdine.size() != 1)
			throw new RuntimeException("test FAILED");

		categoriaServiceInstance.rimuovi(categoria.getId());
		articoloServiceInstance.rimuovi(articolo.getId());
		ordineServiceInstance.rimuovi(categoria.getId());

		System.out.println("............testCercaCategoriaPerOrdine PASSED............");
	}

	public static void testSommaDeiPrezziDegliArticoliLegatiAllaCategoria(CategoriaService categoriaServiceInstance,
			ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {

		Categoria categoria = new Categoria("Elementi macchina", "MACH89");
		categoriaServiceInstance.inserisciNuovo(categoria);

		if (categoria.getId() == null)
			throw new RuntimeException("test FAILED, categoria non inserita");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2019");
		Ordine ordineInstance = new Ordine("carlo forsi", "via Del Corso, 14", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo1 = new Articolo("Pneumatici", "GGGG58", 220,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2018"));
		articolo1.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articolo1);

		articoloServiceInstance.aggiungiCategoria(articolo1, categoria);

		Articolo articolo2 = new Articolo("cambio", "GGGG58", 120,
				new SimpleDateFormat("dd-MM-yyyy").parse("04-05-2018"));
		articolo2.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articolo2);

		articoloServiceInstance.aggiungiCategoria(articolo2, categoria);

		Long prezzi = articoloServiceInstance.sommaDeiPrezziDegliArticoliLegatiAllaCategoria(categoria);

		if (prezzi != 340)
			throw new RuntimeException("test FAILED");

		System.out.println("..........testSommaDeiPrezziDegliArticoliLegatiAllaCategoria PASSED..........");
	}

	public static void testOrdineConDataSpedizionePiùRecente(CategoriaService categoriaServiceInstance,
			ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {

		Categoria categoria = new Categoria("Macchinetta Del caffe", "CAFF02");
		categoriaServiceInstance.inserisciNuovo(categoria);

		if (categoria.getId() == null)
			throw new RuntimeException("test FAILED, categoria non inserita");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-05-2022");
		Ordine ordineInstance = new Ordine("carlo forsi", "via Del Corso, 14", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo1 = new Articolo("capsule", "CAPS90", 220,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2018"));
		articolo1.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articolo1);

		articoloServiceInstance.aggiungiCategoria(articolo1, categoria);

		Date dataSpedizione1 = new SimpleDateFormat("dd-MM-yyyy").parse("24-05-2022");
		Ordine ordineInstance1 = new Ordine("carlo forsi", "via Del Corso, 14", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance1);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo2 = new Articolo("capsule", "CAPS90", 220,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-10-2018"));
		articolo2.setOrdine(ordineInstance1);
		articoloServiceInstance.inserisciNuovo(articolo2);

		articoloServiceInstance.aggiungiCategoria(articolo2, categoria);

		Ordine ordineConDataSpedPiùRecente = ordineServiceInstance.ordineConDataSpedizionePiùRecente(categoria);

		System.out.println(ordineConDataSpedPiùRecente.getDataSpedizione());

		System.out.println("...........testOrdineConDataSpedizionePiùRecente PASSED.....");
	}

	public static void testCercaCodiciDelleCategorieCheHannoOrdiniEffettutatiAFebraio(
			CategoriaService categoriaServiceInstance, ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		Categoria categoria = new Categoria("Computer", "COMP03");
		categoriaServiceInstance.inserisciNuovo(categoria);

		if (categoria.getId() == null)
			throw new RuntimeException("test FAILED, categoria non inserita");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2022");
		Ordine ordineInstance = new Ordine("claudio tiiis", "via bosio, 34", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo1 = new Articolo("Hp_serv", "HPSER90", 890,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2019"));
		articolo1.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articolo1);

		articoloServiceInstance.aggiungiCategoria(articolo1, categoria);

		Date dataSpedizione1 = new SimpleDateFormat("dd-MM-yyyy").parse("21-02-2022");
		Ordine ordineInstance1 = new Ordine("Gioia vita", "via corsi, 90", dataSpedizione1);
		ordineServiceInstance.inserisciNuovo(ordineInstance1);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo2 = new Articolo("ASUS_CAP", "ASUS90", 1110,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-10-2021"));
		articolo2.setOrdine(ordineInstance1);
		articoloServiceInstance.inserisciNuovo(articolo2);

		articoloServiceInstance.aggiungiCategoria(articolo2, categoria);

		List<String> listaCategorie = categoriaServiceInstance
				.cercaCodiciDelleCategorieCheHannoOrdiniEffettutatiAFebraio();

		for (String categoriaItem : listaCategorie) {
			System.out.println(categoriaItem);
		}
	}

	public static void testSommaDeiPrezziDegliArticoliOrdinatiDaMarioRossi(CategoriaService categoriaServiceInstance,
			ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		Categoria categoria = new Categoria("Computer", "COMP03");
		categoriaServiceInstance.inserisciNuovo(categoria);

		if (categoria.getId() == null)
			throw new RuntimeException("test FAILED, categoria non inserita");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2022");
		Ordine ordineInstance = new Ordine("MArio Rossi", "via mosca 88", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo1 = new Articolo("Hp_serv", "HPSER90", 890,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2019"));
		articolo1.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articolo1);

		articoloServiceInstance.aggiungiCategoria(articolo1, categoria);

		Date dataSpedizione1 = new SimpleDateFormat("dd-MM-yyyy").parse("21-02-2022");
		Ordine ordineInstance1 = new Ordine("Mario Rossi", "via corsi, 90", dataSpedizione1);
		ordineServiceInstance.inserisciNuovo(ordineInstance1);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo2 = new Articolo("ASUS_CAP", "ASUS90", 1110,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-10-2021"));
		articolo2.setOrdine(ordineInstance1);
		articoloServiceInstance.inserisciNuovo(articolo2);

		Long sommaArticoliMArioRossi = articoloServiceInstance.sommaDeiPrezziDegliArticoliOrdinatiDaMarioRossi();
		System.out.println(sommaArticoliMArioRossi);

	}

	public static void testListaIndirizziCheCheHannoIndirizzoSeriale(CategoriaService categoriaServiceInstance,
			ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		Categoria categoria = new Categoria("Calzature", "CALZ");
		categoriaServiceInstance.inserisciNuovo(categoria);

		if (categoria.getId() == null)
			throw new RuntimeException("test FAILED, categoria non inserita");

		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2022");
		Ordine ordineInstance = new Ordine("MArio Rossi", "via gianni, 88", dataSpedizione);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo1 = new Articolo("NIKE DUNK", "DUNK44", 90,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2019"));
		articolo1.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articolo1);

		articoloServiceInstance.aggiungiCategoria(articolo1, categoria);

		Date dataSpedizione1 = new SimpleDateFormat("dd-MM-yyyy").parse("21-02-2022");
		Ordine ordineInstance1 = new Ordine("Mario Rossi", "via corsi, 90", dataSpedizione1);
		ordineServiceInstance.inserisciNuovo(ordineInstance1);

		if (ordineInstance.getId() == null)
			throw new RuntimeException("Test FAILED, ordine non inserito");

		Articolo articolo2 = new Articolo("adidas supers", "NUKS49", 188,
				new SimpleDateFormat("dd-MM-yyyy").parse("14-10-2021"));
		articolo2.setOrdine(ordineInstance1);
		articoloServiceInstance.inserisciNuovo(articolo2);

		List<String> listaIndirizzi = ordineServiceInstance.listaIndirizziCheCheHannoIndirizzoSeriale("k");
		for (String indirizzoItem : listaIndirizzi) {
			System.out.println(indirizzoItem);
		}
	}
}
