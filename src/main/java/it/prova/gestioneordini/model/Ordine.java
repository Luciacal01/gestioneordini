package it.prova.gestioneordini.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nomeDestinatorio")
	private String nomeDestinatario;
	@Column(name = "indirizzoDestinatario")
	private String indirizzoDestinatario;
	@Column(name = "dataSpedizione")
	private Date dataSpedizione;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio")
	private Set<Articolo> articoli = new HashSet<>();

	public Ordine() {
	}

	public Ordine(String nomeDestinatario, String indirizzoDestinatario, Date dataSpedizione) {
		super();
		this.nomeDestinatario = nomeDestinatario;
		this.indirizzoDestinatario = indirizzoDestinatario;
		this.dataSpedizione = dataSpedizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getIndirizzoDestinatario() {
		return indirizzoDestinatario;
	}

	public void setIndirizzoDestinatario(String indirizzoDestinatario) {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}

	public Date getDataSpedizione() {
		return dataSpedizione;
	}

	public void setDataSpedizione(Date dataSpedizione) {
		this.dataSpedizione = dataSpedizione;
	}

	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}

	@Override
	public String toString() {
		return "Ordine [id=" + id + ", nomeDestinatario=" + nomeDestinatario + ", indirizzoDestinatario="
				+ indirizzoDestinatario + ", dataSpedizione=" + dataSpedizione + "]";
	}

}
