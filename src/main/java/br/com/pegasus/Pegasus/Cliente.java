package br.com.pegasus.Pegasus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name="tb_cliente")
public class Cliente {
	// criacao dos dados que serao inseridos no banco de dados na tabela cliente
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	@SequenceGenerator(name= "cliente_seq", sequenceName = "seq_cliente", allocationSize = 1)
	@Column (name="id_cliente")
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, unique = true)
	private String email;		
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dataCadastro;
	//como o cliente pode ter varios enderecos tive de criar uma lista para exibi-los e com o cascadetype all para sempre que eu salvar ou apagar
	// o cliente, o java salvar ou apagar a lista de enderecos automaticamente
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Endereco> enderecos = new ArrayList<>();
	public Cliente() {
	}
	public Cliente(Long id, String nome, String email, LocalDateTime dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataCadastro = dataCadastro;
		
	}
	//booleano para verificar email
	public boolean emailValido() {
		if(this.email == null) {
			return false;
		}
		if(email.contains("@") && email.contains(".")){
			return true;
		}
		return false;
	}
	//getters e setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	// criei o set nome com um if para cumprir a exigencia do nome ser entre 3 e 100 caracteres
	public void setNome(String nome) {
		if (nome ==null || nome.length()<3 || nome.length()>100) {
			throw new IllegalArgumentException("Erro: o nome é maior que 100 ou menor que 3");
		}
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public List<Endereco> getEnderecos(){
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos =  enderecos;
	}
	@Override
	public String toString() {
		return "Cliente: "+nome;
	}
	
}
	