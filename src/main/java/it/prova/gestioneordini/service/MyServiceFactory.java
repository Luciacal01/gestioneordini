package it.prova.gestioneordini.service;

import it.prova.gestioneordini.dao.MyDaoFactory;

public class MyServiceFactory {
	private static OrdineService ordineServiceInstance = null;
	private static ArticoloService articoloServiceInstance = null;
	private static CategoriaService categoriaServiceInstance = null;

	public static OrdineService getOrdineServiceInstance() {
		if (ordineServiceInstance == null)
			ordineServiceInstance = new OrdineServiceImpl();
		try {
			ordineServiceInstance.setOrdineDAO(MyDaoFactory.getOrdineDAOInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordineServiceInstance;
	}

	public static ArticoloService getArticoloServiceInstance() {
		if (articoloServiceInstance == null)
			articoloServiceInstance = new ArticoloServiceImpl();
		try {
			articoloServiceInstance.setArticoloDAO(MyDaoFactory.getArticoloDAOInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articoloServiceInstance;

	}

	public static CategoriaService getCategoriaServiceInstance() {
		if (categoriaServiceInstance == null)
			categoriaServiceInstance = new CategoriaServiceImpl();
		try {
			categoriaServiceInstance.setCategoriaDAO(MyDaoFactory.getCategoriaDAOInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoriaServiceInstance;
	}

}
