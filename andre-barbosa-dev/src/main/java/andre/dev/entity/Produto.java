package andre.dev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "produto")
@Entity
@SequenceGenerator(sequenceName = "seq_produto", 
                  name = "seq_produto",
                  allocationSize = 1, 
                  initialValue = 1)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	generator = "seq_produto")
	private Long id;

	@Column(nullable = false)
	private String nome;

	public Produto() {
	}

	public Produto(String nome) {
		super();
		this.nome = nome;
	}

	public Produto(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

}
