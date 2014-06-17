import java.util.Map.Entry;

import rank.Jogador;
import rank.Match;
import rank.Parser;
import rank.Rank;
import util.Util;


public class Main {
	
	public static void main(String[] args) {
		Parser parser = new Parser("arquivo.log");
		parser.parse();
		
		for (Match match : parser.getMatchs()) {
			Rank rank = new Rank(match);
			rank.ignorarJogadorNoTotalAssassinatos(new Jogador("<WORLD>"));
			rank.calcular();
			System.out.println("Rank:" + rank.getMatch().getNome());
			System.out.println("Início:" + Util.formatarData(rank.getMatch().getInicio())); 
			System.out.println("Fim:" + Util.formatarData(rank.getMatch().getFim()));
			
			for (Entry<String, Jogador> jogador : rank.getMatch().getJogadores().entrySet()) {
				System.out.println("Jogador:" + jogador.getValue().getNome() + "\tMortes:" + jogador.getValue().getMortes() + "\tAssassinatos:" + jogador.getValue().getAssassinatos().size());
			}
			
			System.out.println("Total de assassinatos:" + rank.getTotalAssassinatos());
		}
	}

}
