package br.com.fiap.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.fiap.exception.CodigoInexistenteException;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.GenericDAO;

public abstract class GenericDAOImpl<Tabela,Chave> implements GenericDAO<Tabela,Chave> {

	private EntityManager em;
	
	private Class<Tabela> clazz;
	
	@SuppressWarnings("all")
	public GenericDAOImpl(EntityManager em) {
		super();
		this.em = em;
		this.clazz = (Class<Tabela>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}
	
	public void cadastrar(Tabela entidade) {
		em.persist(entidade);
	}
	
	public void atualizar(Tabela entidade) {
		em.merge(entidade);
	}
	
	public	void excluir(Chave id) throws CodigoInexistenteException {
		Tabela entidade = pesquisar(id);
		em.remove(entidade);
	}

	public Tabela pesquisar(Chave id) throws CodigoInexistenteException {
		Tabela entidade = em.find(clazz, id);
	
		if(entidade == null) {
			throw new CodigoInexistenteException();
		}
		return entidade;
	}
	
	public void commit() throws CommitException {
		try {
		em.getTransaction().begin();
		em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new CommitException();
		}
	}
}
