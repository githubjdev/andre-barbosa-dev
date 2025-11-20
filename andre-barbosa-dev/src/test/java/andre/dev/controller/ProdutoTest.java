package andre.dev.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import andre.dev.app.SpringBootApp;
import andre.dev.entity.Produto;
import andre.dev.repository.ProdutoRepository;
import andre.dev.service.ProdutoService;
import andre.dev.test.TesteGeneric;



@AutoConfigureMockMvc
@SpringBootTest(classes = SpringBootApp.class, 
            webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoTest extends TesteGeneric {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DisplayName("1 - GET /api/produtos - Deve retornar lista de produtos")
	public void testeListarTodos1() {
		
		produtoRepository.deleteAll();
		
		Produto p1 = new Produto("Produto A");
		Produto p2 = new Produto("Produto B");
		
		p1 = produtoService.salvar(p1);
		p2 = produtoService.salvar(p2);
		
		ResponseEntity<Produto[]> response = restTemplate
		        .getForEntity(url("api/produtos"), 
		        Produto[].class);
		
		List<Produto> produtos = Arrays.asList(response.getBody());
		
		assertEquals(2, produtos.size());
		assertEquals(p1.getId(), produtos.get(0).getId());
		assertEquals(p2.getId(), produtos.get(1).getId());
		
		assertEquals(p1.getNome(), produtos.get(0).getNome());
		assertEquals(p2.getNome(), produtos.get(1).getNome());
		
		produtoRepository.deleteAll();
		
	}

}
