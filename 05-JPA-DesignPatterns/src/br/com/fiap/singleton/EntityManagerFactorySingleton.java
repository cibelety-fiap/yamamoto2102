package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;

public class EntityManagerFactorySingleton {
	
	//1 - Atributo est�tico que ser� �nico
	private static EntityManagerFactory fabrica;
	
	//2 - Construtor privado
	private EntityManagerFactorySingleton() {}
	
	//3 - M�todo est�tico que retorna a �nica instancia
	public static EntityManagerFactory getInstance() {
		return fabrica;
		
	}

}
