package br.edu.iff.restaurante;

import br.edu.iff.restaurante.model.*;
import br.edu.iff.restaurante.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestauranteApplication implements CommandLineRunner {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ComandaRepository comandaRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    PermissaoRepository permissaoRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestauranteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Permissao p1 = new Permissao("ADMIN");
        permissaoRepository.save(p1);
        Permissao p2 = new Permissao("FUNC");
        permissaoRepository.save(p2);

        Cliente cli = new Cliente("892.384.000-91", "Daniel", "(22) 99999-9999");
        cli.setEndereco(new Endereco("Rua 1", 1, "Centro", "Campos", "28000-000"));
        clienteRepository.save(cli);

        Funcionario func = new Funcionario("374.572.110-12", "Fulano", "Gerente", "senha123");
        func.setEndereco(new Endereco("Rua 2", 2, "Centro", "Campos", "28000-000"));
        func.setPermissoes(Arrays.asList(p1,p2));
        func.setSenha(new BCryptPasswordEncoder().encode(func.getSenha()));
        funcionarioRepository.save(func);

        Produto prod = new Produto("Coca Cola", BigDecimal.valueOf(5.89));
        prod.setDescricao("Refrigerante");
        produtoRepository.save(prod);

        Comanda com = new Comanda(1, func);
        com.setCliente(cli);
        com.setFormaPagamento(FormaPagamentoEnum.DINHEIRO);
        com.setHorarioFechamento(LocalDateTime.now());
        com.setStatus(StatusComandaEnum.fechada);
        com.getItens().add(new Item(prod, 1));
        comandaRepository.save(com);

    }
}
