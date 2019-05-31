package br.com.senai.fatesg.fabrica.entidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.ambientinformatica.util.Entidade;

//import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TipoDePrestador", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "descricao") })
public class TipoDePrestador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;	
	private String descricao;	
	private boolean desativado;		
	private Set<Prestador> prestador = new HashSet<Prestador>(0);
	
	@OneToMany(targetEntity=Prestador.class, fetch = FetchType.LAZY, mappedBy = "tipoDePrestador")
	public Set<Prestador> getPrestador() {
		return this.prestador;
	}

	public void setPrestador(Set<Prestador> prestador) {
		this.prestador = prestador;
	}	
	
	@Id
	@GeneratedValue(generator = "tipoDePrestador_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "tipoDePrestador_seq", sequenceName = "tipoDePrestador_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isDesativado() {
		return desativado;
	}
	public void setDesativado(boolean desativado) {
		this.desativado = desativado;
	}
	

}