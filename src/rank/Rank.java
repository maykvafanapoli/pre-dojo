package rank;

import java.util.HashSet;
import java.util.Map;


public class Rank {

	private int totalAssassinatos = 0;
	private HashSet<Jogador> jogadoresIgnorados = new HashSet<Jogador>();
	private Match match;
	
	public Rank(Match match) {
		this.match = match;
	}
	
	public void calcular() {
		for (Map.Entry<String, Jogador> jogador : match.getJogadores().entrySet()) {
			if (!jogadoresIgnorados.contains(jogador.getValue())) {
				totalAssassinatos += jogador.getValue().getAssassinatos().size();
			}
		}
	}

	public int getTotalAssassinatos() {
		return totalAssassinatos;
	}

	public void ignorarJogadorNoTotalAssassinatos(Jogador jogador) {
		jogadoresIgnorados.add(jogador);
		
	}

	public Match getMatch() {
		return match;
	}

}
