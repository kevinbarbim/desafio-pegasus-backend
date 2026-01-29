package br.com.pegasus.Pegasus;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {
	@Autowired	
	private ClienteRepository repository;
	// adicionar cliente
	public Cliente adicionar(Cliente cliente) {
		if (cliente.emailValido()) {
			System.out.println("Cliente adicionado com sucesso: "+cliente.getNome());
			return repository.save(cliente);
			
		}else {
			System.out.println("Erro! Email inválido para o cliente "+cliente.getNome());
			throw new IllegalArgumentException("Email inválido, o cliente não foi salvo.");
		} 
	}
	//buscar cliente
	public List<Cliente> buscarPorNome(String nomeBusca){
		return repository.findByNomeContainingIgnoreCase(nomeBusca);
	}
	//remover cliente (verifica se existe antes de apagar para evitar erros)
	public void remover(Long id) {
		if  (repository.existsById(id)) {
			repository.deleteById(id);
			System.out.println("Cliente removido!");
		}
		else {
			System.out.println("Cliente não encontrado!");
		}
	}//buscar por id (opcional pois pode ser que eu pesquise um id que nao existe)
	public Optional<Cliente> buscarPorId(Long id) {
	    return repository.findById(id);
	}
	//lista todos clientes
	public List<Cliente> listarTodos(){
		return repository.findAll();
	}
}
