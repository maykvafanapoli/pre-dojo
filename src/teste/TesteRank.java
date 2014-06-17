package teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import rank.Jogador;
import rank.Match;
import rank.Parser;
import rank.Rank;

public class TesteRank {

	Parser parser;
	
	@Before
	public void setupParser() {
		parser = new Parser("arquivo.log");
		parser.parse();
		assertNotNull(parser);
	}
	
	@Test
	public void isCarregaLinhasCorretamente() {
		assertNotNull(parser.getLinhas());
		assertTrue(parser.getLinhas().size() == 4);
		assertNotNull(parser.getScanner());
		assertNotNull(parser.getScanner());
		assertNotNull(parser.getMatchs());
		assertTrue(parser.getMatchs().size() == 1);
	}
	
	@Test
	public void isExistemDoisAssassinatos() {
		for (Match match : parser.getMatchs()) {
			Rank rank = new Rank(match);
			rank.calcular();
			assertTrue(rank.getTotalAssassinatos() == 2);	
		}
	}
	
	@Test
	public void ignorarMorteJogador() {
		for (Match match : parser.getMatchs()) {
			Rank rank = new Rank(match);
			rank.ignorarJogadorNoTotalAssassinatos(new Jogador("<WORLD>"));
			rank.calcular();
			assertTrue(rank.getTotalAssassinatos() == 1);
		}
	}
	
	@Test
	public void calcularMortes() {
		for (Match match : parser.getMatchs()) {
			Rank rank = new Rank(match);
			rank.calcular();
			assertTrue(rank.getTotalAssassinatos() == 2);
		}
	}
	
}
