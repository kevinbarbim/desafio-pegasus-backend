package br.com.pegasus.Pegasus;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PegasusApplication implements CommandLineRunner {
	@Autowired
    private ClienteService service;
	public static void main(String[] args) {
		SpringApplication.run(PegasusApplication.class, args);
	}
	@Override
    public void run(String... args) throws Exception {
		
        //toda vez que rodava o programa ele criava o cliente de teste vinicius novamente, implementei esse if para
		// a criacao nao se repetir todas as vezes que eu rodo  
        if (!service.listarTodos().isEmpty()) {
            System.out.println("--- O BANCO JÁ TEM DADOS. PULEI A INSERÇÃO DE TESTE. ---");
            
            return; 
        }

        System.out.println("--- BANCO VAZIO: INSERINDO DADOS DE TESTE ---");

        // cliente teste
        Cliente c1 = new Cliente(null, "Vinicius", "vini@gmail.com", LocalDateTime.now());

        // endereco teste ligado ao cliente
        Endereco e1 = new Endereco("CASA", "Rua das Flores", "10", null, "15000-000", "Rio Preto", "SP", c1);

        // adiciono os dois na lista
        c1.getEnderecos().add(e1);

        // salvo tudo
        service.adicionar(c1);
    }
    
}