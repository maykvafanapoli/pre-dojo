package rank;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	
	private String nome;
	private List<Jogador> assassinatos = new ArrayList<>();
	private int mortes = 0;
	
	public Jogador(String nome) {
		this.nome = nome;
	}
	
	public void contabilizarMorte() {
		this.mortes++;
	}

	public String getNome() {
		return nome;
	}
	
	public void addAssassinato(Jogador assassinado) {
		this.assassinatos.add(assassinado);
	}
	
	public List<Jogador> getAssassinatos() {
		return assassinatos;
	}
	
	public int getMortes() {
		return mortes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
