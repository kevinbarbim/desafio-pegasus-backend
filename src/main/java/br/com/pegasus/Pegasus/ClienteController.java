package br.com.pegasus.Pegasus;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	// metodo get que mostra todos os clientes cadastrados
	@GetMapping
	public List<Cliente> listarTodos(){
		return service.listarTodos();
	}
	// metodo get que busca os clientes com base no id pela url  http://localhost:8080/clientes/1
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = service.buscarPorId(id);
		//verifica se achou um cliente com o id 
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	//metodo post que pega o json do usuario e converte em objeto
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente){
		Cliente clienteSalvo = service.adicionar(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
}
