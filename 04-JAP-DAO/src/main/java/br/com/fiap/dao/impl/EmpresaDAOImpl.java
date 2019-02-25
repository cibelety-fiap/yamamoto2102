package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EmpresaDAO;
import br.com.fiap.entity.Empresa;
import br.com.fiap.exception.CodigoInexistenteException;
import br.com.fiap.exception.CommitException;

public class EmpresaDAOImpl implements EmpresaDAO {

	private EntityManager em;

	public EmpresaDAOImpl(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar(Empresa empresa) {
		em.persist(empresa);
	}

	public void atualizar(Empresa empresa) {
		em.merge(empresa);
	}

	public void excluir(int codigo) throws CodigoInexistenteException {
		Empresa empresa = pesquisar(codigo);
		if (empresa==null) {
			throw new CodigoInexistenteException();
		}
		em.remove(empresa);
	}

	public Empresa pesquisar(int codigo) {
		Empresa empresa = em.find(Empresa.class, codigo);
		return empresa;
	}

	public void commit() throws CommitException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			throw new CommitException();
		}
	}

}
