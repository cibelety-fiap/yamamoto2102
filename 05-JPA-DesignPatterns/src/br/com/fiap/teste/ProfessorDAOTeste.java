package br.com.fiap.teste;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.ProfessorDAO;
import br.com.fiap.dao.impl.ProfessorDAOImpl;
import br.com.fiap.entity.Professor;
import br.com.fiap.exception.CommitException;

class ProfessorDAOTeste {
	
	private static ProfessorDAO dao;
	
	//método que será executado antes de todos os testes
	@BeforeAll
	public static void inicializar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("teste");
		EntityManager em = fabrica.createEntityManager();
		dao = new ProfessorDAOImpl(em);
	}
	

	@Test
	void cadastroTest() {
		// Arrange - instanciar os objetos
		Professor prof = new Professor("Parducci", null, "123456789");
		
		// Act
		try {
			dao.cadastrar(prof);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste...");

		}

		// Assert - valida o resultado
		// valida se foi gerado um código pela sequence
		assertNotEquals(0, prof.getCodigo());
	}
	
	@Test
	void atualizarTest() {
		// Arrange - instanciar os objetos
		Professor prof = new Professor(1, "Blabla", null, "987654321");

		//Act
		try {	
			dao.atualizar(prof);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste...");
		}

		assertEquals(prof, dao.pesquisar(prof.getCodigo()));
		
	}
}