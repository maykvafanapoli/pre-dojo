package rank;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.Util;

public class Parser {

	private List<String> lines;
	private List<Match> matchs = new ArrayList<>();
	private String nomeArquivo = "";

	public Parser(String arquivo) {
		this.nomeArquivo = arquivo;
	}

	public void parse() {
		try {
			lines = Files.readAllLines(Paths.get(this.nomeArquivo), Charset.forName("UTF-8"));
			Scanner scan = null;
			String data = "";

			for (String linha : this.getLinhas()) {
				scan = new Scanner(linha);
				String nomeJogadorAssassino = "";
				String nomeJogadorAssassinado = "";

				switch (this.getToken(linha)) {

				case INCIO_PARTIDA:
					scan.useDelimiter(" ");
					data = scan.next();
					data += " " + scan.next();
					scan.next();
					scan.next();
					scan.next();
					Match match = new Match(scan.next());
					match.setInicio(Util.formatarData(data));
					matchs.add(match);
					break;

				case ASSASSINATO:
					scan.next();// Skip na Data
					scan.next();// Skip na Hora
					scan.next();// Skip no hífen

					nomeJogadorAssassino = scan.next();
					scan.next();// Skip na palavra "killed"
					nomeJogadorAssassinado = scan.next();

					//Obtém o jogador assassino da partida
					Jogador jogadorAssassino = matchs.get(matchs.size() - 1).getJogadores().get(nomeJogadorAssassino);
					//Obtém o jogador assassino da partida
					Jogador jogadorAssassinado = matchs.get(matchs.size() - 1).getJogadores().get(nomeJogadorAssassinado);
					// Se o jogador já estiver associado a partida, é adicionado uma morte a sua coleção.
					
					if (jogadorAssassino == null) {	
						jogadorAssassino = new Jogador(nomeJogadorAssassino);
						matchs.get(matchs.size() - 1).addJogador(nomeJogadorAssassino, jogadorAssassino);
						jogadorAssassino = matchs.get(matchs.size() - 1).getJogadores().get(nomeJogadorAssassino);
					}
					
					if (jogadorAssassinado == null) {	
						jogadorAssassinado = new Jogador(nomeJogadorAssassinado);
						matchs.get(matchs.size() - 1).addJogador(nomeJogadorAssassinado, jogadorAssassinado);
						jogadorAssassinado = matchs.get(matchs.size() - 1).getJogadores().get(nomeJogadorAssassinado);
					}
					
					jogadorAssassino.addAssassinato(jogadorAssassinado);
					jogadorAssassinado.contabilizarMorte();
					

					break;

				case FIM_PARTIDA:
					scan.useDelimiter(" ");
					data = scan.next();
					data += " " + scan.next();
					matchs.get(matchs.size() - 1).setFim(Util.formatarData(data));

				default:
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Match> getMatchs() {
		return this.matchs;
	}

	private PalavrasReservadasEnum getToken(String tokenAtual) {
		if (tokenAtual.contains("has started")) {
			return PalavrasReservadasEnum.INCIO_PARTIDA;
		} else if (tokenAtual.contains("has ended")) {
			return PalavrasReservadasEnum.FIM_PARTIDA;
		} else if (tokenAtual.contains("killed")) {
			return PalavrasReservadasEnum.ASSASSINATO;
		}
		return PalavrasReservadasEnum.INVALIDO;
	}

	public List<String> getLinhas() {
		return this.lines;
	}

	public Scanner getScanner() {
		return new Scanner(lines.toString());

	}

}
