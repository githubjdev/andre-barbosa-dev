package andre.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import andre.dev.entity.Produto;
import andre.dev.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public Produto atualizar(Produto p) {
		Produto existente = produtoRepository.findById(p.getId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		existente.setNome(p.getNome());
		return produtoRepository.save(existente);
	}

	@Transactional
	public void excluir(Long id) {
		produtoRepository.deleteById(id);
	}

	@Transactional
	public List<Produto> buscarPorNome(String nome) {
		return produtoRepository.buscarPorNome(nome.toLowerCase());
	}
	
	@Transactional
	public boolean existePorId(Long id) {
	    return produtoRepository.existsById(id);
	}

}
