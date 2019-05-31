package br.com.senai.fatesg.fabrica.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.senai.fatesg.fabrica.entidade.Cliente;
import br.com.senai.fatesg.fabrica.entidade.Prestador;
import br.com.senai.fatesg.fabrica.entidade.TipoDePrestador;
import br.com.senai.fatesg.fabrica.persistencia.PrestadorDao;
import br.com.senai.fatesg.fabrica.persistencia.TipoDePrestadorDao;

@Named("PrestadorControl")
@Scope("conversation")
public class PrestadorControl {

	private Prestador prestador = new Prestador();
	
	private TipoDePrestador tpprestador = new TipoDePrestador();

	@Autowired
	private PrestadorDao prestadorDao;
	
	private List<Prestador> prestadores = new ArrayList<Prestador>();
	
	@Autowired
	private TipoDePrestadorDao tipoPrestadorDao;
	
	
	private List<TipoDePrestador> tiposPrestadores = new ArrayList<TipoDePrestador>();
	
	public TipoDePrestador getTpprestador() {
		return tpprestador;
	}



	public void setTpprestador(TipoDePrestador tpprestador) {
		this.tpprestador = tpprestador;
	}



	public PrestadorDao getPrestadorDao() {
		return prestadorDao;
	}



	public void setPrestadorDao(PrestadorDao prestadorDao) {
		this.prestadorDao = prestadorDao;
	}



	public TipoDePrestadorDao getTipoPrestadorDao() {
		return tipoPrestadorDao;
	}



	public void setTipoPrestadorDao(TipoDePrestadorDao tipoPrestadorDao) {
		this.tipoPrestadorDao = tipoPrestadorDao;
	}



	public List<TipoDePrestador> getTiposPrestadores() {
		return tiposPrestadores;
	}



	public void setTiposPrestadores(List<TipoDePrestador> tiposPrestadores) {
		this.tiposPrestadores = tiposPrestadores;
	}



	public void setPrestadores(List<Prestador> prestadores) {
		this.prestadores = prestadores;
	}



	@PostConstruct
	public void init() {
		listar(null);
		listarTiposPrestadores();
	}

	

	public void confirmar(ActionEvent evt) {
		try {
			if( prestador.getId() == null) {
				prestadorDao.incluir(prestador);
				novo();
			}
			else {				
				prestadorDao.alterar(prestador);
			}
			listar(evt);			
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	public void novo() {
		try {
			prestador = new Prestador();						
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	
	public void editar(Prestador prestador) {
		try {
			this.prestador = prestador;						
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}
	public void excluir(int id) {
		try {
			prestador = prestadorDao.consultar(id);
			prestadorDao.excluirPorId(prestador.getId());	
			prestador = new Prestador();
			prestadores = prestadorDao.listar();
			
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}


	public void listar(ActionEvent evt) {
		try {
			prestadores = prestadorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	private void listarTiposPrestadores() {
		try {
			tiposPrestadores = tipoPrestadorDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
		
	}
	
	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public List<Prestador> getPrestadores() {
		return prestadores;
	}

}
