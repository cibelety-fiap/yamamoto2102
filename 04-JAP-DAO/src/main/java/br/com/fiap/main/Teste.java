package br.com.fiap.main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.dao.EmpresaDAO;
import br.com.fiap.dao.impl.EmpresaDAOImpl;
import br.com.fiap.entity.Empresa;
import br.com.fiap.entity.Status;
import br.com.fiap.exception.CodigoInexistenteException;
import br.com.fiap.exception.CommitException;

public class Teste {

	public static void main(String[] args) throws CommitException {

		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		EntityManager em = fabrica.createEntityManager();
		
		EmpresaDAO dao = new EmpresaDAOImpl(em);
		
		
		//Cadastrar uma Empresa
		
		try {
			dao.cadastrar(new Empresa("ABC", new GregorianCalendar(1990, Calendar.JANUARY,1), Status.ATIVO, "123/001"));
			dao.commit();
			System.out.println("Cadastrado com sucesso!");
		} catch (CommitException e) {
			e.printStackTrace();
			System.err.println("Erro ao cadastrar");
		}
		
		System.out.println(dao.pesquisar(1).getNomeFantasia());
		
		try {
			dao.atualizar(new Empresa(1, "BCD", new GregorianCalendar(2000, Calendar.JANUARY,1), Status.ATIVO, "123/001"));
			dao.commit();
			System.out.println("Atualizado com sucesso!");
		} catch (CommitException e) {
			e.printStackTrace();
			System.err.println("Erro ao atualizar");
		}
		
		System.out.println(dao.pesquisar(1).getNomeFantasia());
/*		
		try {
			dao.excluir(1);
			dao.commit();
			System.out.println("Excluído com sucesso!");
		} catch (CodigoInexistenteException e) {
			e.printStackTrace();
			System.err.println("Erro ao excluir");
		}
*/		
		em.close();
		fabrica.close();
	}

}
