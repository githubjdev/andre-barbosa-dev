package andre.dev.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
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
	void testeListarTodos1() throws Exception {
		
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
		
	}

	@Test
	@DisplayName("2 - GET /api/produtos - Deve retornar lista de produtos")
	void testeListarTodos2() throws Exception {

		produtoRepository.deleteAll();

		Produto p1 = new Produto("Produto A");
		Produto p2 = new Produto("Produto B");

		p1 = produtoService.salvar(p1);
		p2 = produtoService.salvar(p2);

		getMockMvc().perform(get("/api/produtos"))
		              .andExpect(status().isOk())
		              .andExpect(jsonPath("$.length()").value(2))
				      .andExpect(jsonPath("$[0].id").value(p1.getId()))
				      .andExpect(jsonPath("$[0].nome").value(p1.getNome()))
				      .andExpect(jsonPath("$[1].id").value(p2.getId()))
				      .andExpect(jsonPath("$[1].nome").value(p2.getNome()));

	}


	@Test
	@DisplayName("3 - GET /api/produtos - Deve retornar lista vazia")
	void testeListarTodos3() throws Exception {
		
		produtoRepository.deleteAll();


		ResponseEntity<Produto[]> response = restTemplate
				        .getForEntity(url("api/produtos"), 
				        Produto[].class);
		
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
		assertEquals(0, response.getBody().length);
		
	}

}
