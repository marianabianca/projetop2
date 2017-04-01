package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.ProjetoController;

/**
 * Classe para a instacicao do objeto que implementa a logica da persistencia do sistema
 * 
 * @author Mariana
 *
 */
public class Persistencia {
	
	private Controllers controllers;
	private File path = new File("arquivos_sistema");
	private File arquivo = new File (path + File.separator + "cpc_ufcg.dat");
	
	/**
	 * Construtor, inicia um novo objeto Controllers controllers
	 */
	public Persistencia() {
		controllers = new Controllers(new ProjetoController(), new PessoaController());
	}
	
	/**
	 * Metodo que inicia o sistema, carregando o arquivo que tem salvo um objeto do tipo Controllers e instanciando o atibuto
	 * controllers (da classe) como esse objeto. Caso o arquivo nao exista e seja lancada uma FileNotFoundException, sera criado
	 * o diretorio do atributo path e sera lancada uma nova FileNotFoundException que devera ser tratada em outro metodo
	 * @throws IOException - caso haja problema com o arquivo
	 * @throws ClassNotFoundException - caso o downcast nao funcione
	 */
	public void iniciaSistema() throws IOException, ClassNotFoundException{
		try{			
			FileInputStream fis = new FileInputStream(arquivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			controllers = (Controllers) ois.readObject();
			fis.close();				
		} catch (FileNotFoundException e) {
			path.mkdir();
			throw new FileNotFoundException(e.getMessage());
		}
	}
	
	/**
	 * Metodo que pega o atributo pessoaController e o retorna
	 * @return PessoaController - atributo pessoaController
	 */
	public PessoaController iniciaPessoa() {
		return this.controllers.getPessoaController();
	}
	
	/**
	 * Metodo que pega o atributo projetoController e o retorna
	 * @return ProjetoController - atributo projetoController
	 */
	public ProjetoController iniciaProjeto() {
		return this.controllers.getProjetoController();
	}
	
	/**
	 * Metodo que pega o atributo participacaoController e o retorna
	 * @return ParticipcaoController - atributo participacaoController
	 */
	public ParticipacaoController iniciaParticipacao() {
		return this.controllers.getParticipacaoController();
	}
	
	/**
	 * Metodo que salva os controllers passados como parametro em um objeto do tipo Controller e escreve esse objeto
	 * no arquivo cujo path esta no atributo path.
	 * 
	 * @param projetoController - objeto do tipo ProjetoController a ser salvo
	 * @param pessoaController - objeto do tipo PessoaController a ser salvo
	 * @param participacaoController - objeto do tipo PartipacaoController a ser salvo
	 * @throws IOException - caso haja algum problema com o arquivo
	 */
	public void fechaSistma(ProjetoController projetoController, PessoaController pessoaController, 
			ParticipacaoController participacaoController) throws IOException {
		controllers = new Controllers(projetoController, pessoaController, participacaoController);
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(controllers);
		fos.close();
	}

}
