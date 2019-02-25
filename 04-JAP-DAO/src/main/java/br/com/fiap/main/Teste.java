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

public class Teste {

	public static void main(String[] args) {

		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		EntityManager em = fabrica.createEntityManager();
		
		EmpresaDAO dao = new EmpresaDAOImpl(em);
		
		
		//Cadastrar uma Empresa
		dao.cadastrar(new Empresa("ABC", new GregorianCalendar(1990, Calendar.JANUARY,1), Status.ATIVO, "123/001"));
		
		em.close();
		fabrica.close();
	}

}
