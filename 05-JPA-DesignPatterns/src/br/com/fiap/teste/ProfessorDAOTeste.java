package br.com.fiap.teste;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.ProfessorDAO;
import br.com.fiap.dao.impl.ProfessorDAOImpl;
import br.com.fiap.entity.Professor;
import br.com.fiap.exception.CodigoInexistenteException;
import br.com.fiap.exception.CommitException;

class ProfessorDAOTeste {
	
	private static ProfessorDAO dao;
	
	private Professor prof;
	
	//método que será executado antes de todos os testes
	@BeforeAll
	public static void inicializar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("teste");
		EntityManager em = fabrica.createEntityManager();
		dao = new ProfessorDAOImpl(em);
	}
	
	@BeforeEach
	public void antesTest() {
		// Arrange - instanciar os objetos
		prof = new Professor("Parducci", null, "123456789");
		
		// Act
		try {
			dao.cadastrar(prof);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste...");

		}
	}
	

	@Test
	void cadastroTest() {
		// Assert - valida o resultado
		// valida se foi gerado um código pela sequence
		assertNotEquals(0, prof.getCodigo());
	}
	
	@Test
	void removerTest() throws CommitException {
		
		try {
			dao.excluir(prof.getCodigo());
			dao.commit();	
		} catch (CodigoInexistenteException e) {
			e.printStackTrace();
			fail("Falha no teste...");
		}	
		
		assertThrows(CodigoInexistenteException.class, ()->dao.pesquisar(prof.getCodigo()));
		
	}
	
	@Test
	void pesquisarTest() {
		try {
			
			Professor busca = dao.pesquisar(prof.getCodigo());
			
			assertNotNull(busca);
			assertEquals(busca.getNome(), prof.getNome());
			
		} catch (CodigoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Falha no teste...");
		}
	}
	
	
	@Test
	void atualizarTest() {
		// Arrange - instanciar os objetos
		Professor prof = new Professor("Blabla", null, "987654321");

		//Act
		// Cadastrar um professor
		try {	
			dao.cadastrar(prof);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste...");
		}

		// Atualizar o professor
		
		Professor prof2 = new Professor(prof.getCodigo(), "Rafael", null, "123123123");
		
		try {	
			dao.atualizar(prof2);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste...");
		}
		
		//Assert
		//Pesquisa e valida se o valor foi alterado no banco
		
		
		try {
			Professor prof3 = dao.pesquisar(prof.getCodigo());
			assertEquals(prof2.getNome(), prof3.getNome());
			assertEquals(prof2.getCpf(), prof3.getCpf());
					
		} catch (CodigoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}