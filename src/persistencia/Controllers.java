package persistencia;

import java.io.Serializable;

import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.ProjetoController;

/**
 * Classe para instaciacao de objetos do tipo Controllers, que tem como atributo os tres controllers que fazem o sistema funcionar
 * 
 * @author Mariana
 *
 */
public class Controllers implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjetoController projetoController;
	private PessoaController pessoaController;
	private ParticipacaoController participacaoController;
	
	/**
	 * Construtor
	 * 
	 * @param projetoController - objeto do tipo ProjetoController
	 * @param pessoaController - objeto do tipo PessoaController
	 */
	public Controllers(ProjetoController projetoController, PessoaController pessoaController) {
		this.projetoController = projetoController;
		this.pessoaController = pessoaController;
		this.participacaoController = new ParticipacaoController(pessoaController, projetoController);
	}
	
	/**
	 * Construtor
	 * 
	 * @param projetoController - objeto do tipo ProjetoController
	 * @param pessoaController - objeto do tipo PessoaController
	 * @param participacaoController - objeto do tipo ParticipacaoController
	 */
	public Controllers(ProjetoController projetoController, PessoaController pessoaController, ParticipacaoController participacaoController) {
		this.projetoController = projetoController;
		this.pessoaController = pessoaController;
		this.participacaoController = participacaoController;
	}
	
	/**
	 * metodo get do atributo projetoController
	 * 
	 * @return ProjetoController - o objeto que estava no atributo projetoController
	 */
	public ProjetoController getProjetoController(){
		return this.projetoController;
	}
	
	/**
	 * metodo get do atributo pessoaController
	 * 
	 * @return PessoaController - o objeto que estava no atributo pessoaController
	 */
	public PessoaController getPessoaController(){
		return this.pessoaController;
	}
	
	/**
	 * metodo get do atributo participacaoController
	 * 
	 * @return ParticipacaoController - o objeto que estava no atributo participacaoController
	 */
	public ParticipacaoController getParticipacaoController(){
		return this.participacaoController;
	}

}
