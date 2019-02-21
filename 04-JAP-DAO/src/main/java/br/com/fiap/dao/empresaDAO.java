package br.com.fiap.dao;

import br.com.fiap.entity.Empresa;

public interface EmpresaDAO {
	
	//sempre � public
	void cadastrar(Empresa empresa);
	
	void atualizar(Empresa empresa);
	
	void excluir(int codigo);
	
	Empresa pesquisar(int codigo);
	
	
}
