package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;

public class EntityManagerFactorySingleton {
	
	//1 - Atributo estático que será único
	private static EntityManagerFactory fabrica;
	
	//2 - Construtor privado
	private EntityManagerFactorySingleton() {}
	
	//3 - Método estático que retorna a única instancia
	public static EntityManagerFactory getInstance() {
		return fabrica;
		
	}

}
