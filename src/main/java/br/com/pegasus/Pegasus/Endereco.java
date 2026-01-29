package br.com.pegasus.Pegasus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco {
	// criacao dos dados que serao inseridos no banco de dados na tabela endereco
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
    @SequenceGenerator(name = "endereco_seq", sequenceName = "seq_endereco", allocationSize = 1)
    @Column(name = "id_endereco") 
    private Long id;


    @Column(name = "tipo_endereco", length = 20)
    private String tipoEndereco;

    @Column(nullable = false, length = 200)
    private String logradouro;

    @Column(length = 10)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

   //chave estrangeira do cliente, nao pode ser nulo pois o endereco tem que ta atrelado a um cliente
    //json ignore adicionado para impedir erro de loop infinito
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false) 
    @JsonIgnore
    private Cliente cliente;
    
    public Endereco() {
    }

  
    public Endereco(String tipoEndereco, String logradouro, String numero, String complemento, String cep, String cidade, String estado, Cliente cliente) {
        this.tipoEndereco = tipoEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
    }


//getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    @Override
    public String toString() {
      
        return "Endereco id=" + id + ", logradouro=" + logradouro + ", cidade=" + cidade;
    }
}

