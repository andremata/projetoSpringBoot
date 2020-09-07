package com.andremata.projetospringbootjava;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andremata.projetospringbootjava.domain.Categoria;
import com.andremata.projetospringbootjava.domain.Cidade;
import com.andremata.projetospringbootjava.domain.Cliente;
import com.andremata.projetospringbootjava.domain.Endereco;
import com.andremata.projetospringbootjava.domain.Estado;
import com.andremata.projetospringbootjava.domain.ItemPedido;
import com.andremata.projetospringbootjava.domain.Pagamento;
import com.andremata.projetospringbootjava.domain.PagamentoBoleto;
import com.andremata.projetospringbootjava.domain.PagamentoCartao;
import com.andremata.projetospringbootjava.domain.Pedido;
import com.andremata.projetospringbootjava.domain.Produto;
import com.andremata.projetospringbootjava.domain.enums.EstadoPagamento;
import com.andremata.projetospringbootjava.domain.enums.TipoCliente;
import com.andremata.projetospringbootjava.repositories.CategoriaRepository;
import com.andremata.projetospringbootjava.repositories.CidadeRepository;
import com.andremata.projetospringbootjava.repositories.ClienteRepository;
import com.andremata.projetospringbootjava.repositories.EnderecoRepository;
import com.andremata.projetospringbootjava.repositories.EstadoRepository;
import com.andremata.projetospringbootjava.repositories.ItemPedidoRepository;
import com.andremata.projetospringbootjava.repositories.PagamentoRepository;
import com.andremata.projetospringbootjava.repositories.PedidoRepository;
import com.andremata.projetospringbootjava.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoSpringBootJavaApplication implements CommandLineRunner 	{

	@Autowired
    private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidorepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringBootJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
				
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		/********************************************************************/
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade cd1 = new Cidade(null, "Uberlândia", e1);
		Cidade cd2 = new Cidade(null, "São Paulo", e2);
		Cidade cd3 = new Cidade(null, "Campinas", e2);
				
		e1.getCidades().addAll(Arrays.asList(cd1));
		e2.getCidades().addAll(Arrays.asList(cd2, cd3));
		
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cd1,cd2,cd3));
		
		/**********************************************************************/
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco ed1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cd1);
		Endereco ed2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cd1);
		
		cli1.getEnderecos().addAll(Arrays.asList(ed1, ed2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(ed1, ed2));
		
		/***********************************************************************/
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		//Cria pedidos
		Pedido ped1 = new Pedido(null, sdf.parse("29/08/2020 22:05"), cli1, ed1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/08/2020 09:25"), cli1, ed2);
		
		//Associa os pagamentos
		Pagamento pgto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/08/2020 00:00"), null);
		ped2.setPagamento(pgto2);
		
		//Associa os pedidos ao cliente
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		/********************************************************************************/
		
		ItemPedido itemPedido1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		ped2.getItens().addAll(Arrays.asList(itemPedido3));
		
		p1.getItens().addAll(Arrays.asList(itemPedido1));
		p2.getItens().addAll(Arrays.asList(itemPedido3));
		p3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidorepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}
}
