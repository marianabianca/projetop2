package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Classe para instaciacao de objeto que ira salvar relatorios sobre projetos e sobre colaboracoes
 * 
 * @author Mariana
 *
 */
public class ArquivosRelatorios implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File path = new File("arquivos_sistema" + File.separator + "relatorios");
	private File arquivoProjetos;
	private File arquivoColaboracoes;
	
	/**
	 * construtor
	 */
	public ArquivosRelatorios() {
		arquivoProjetos = new File(path + File.separator + "cad_projetos.txt");
		arquivoColaboracoes = new File(path + File.separator + "cad_colaboracoes.txt");
		path.mkdir();
	}
	
	/**
	 *  metodo para salvar relatorio sobre projetos
	 *  
	 * @param texto - texto do relatorio a ser salvo
	 * @throws IOException - caso haja algum prolema com o arquivo
	 */
	public void salvaRelatorioProjetos(String texto) throws IOException {
		FileWriter fw = new FileWriter(arquivoProjetos);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.close();
	}
	
	/**
	 *  metodo para salvar relatorio sobre colaboracoes
	 *  
	 * @param texto - texto do relatorio a ser salvo
	 * @throws IOException - caso haja algum prolema com o arquivo
	 */
	public void salvaRelatorioColaboracoes(String texto) throws IOException {
		FileWriter fw = new FileWriter(arquivoColaboracoes);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.close();
	}

}
