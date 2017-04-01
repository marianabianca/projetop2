package persistencia;

import java.io.Serializable;

import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.ProjetoController;

public class Controllers implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjetoController projetoController;
	private PessoaController pessoaController;
	private ParticipacaoController participacaoController;
	
	public Controllers(ProjetoController projetoController, PessoaController pessoaController) {
		this.projetoController = projetoController;
		this.pessoaController = pessoaController;
		this.participacaoController = new ParticipacaoController(pessoaController, projetoController);
	}
	
	public Controllers(ProjetoController projetoController, PessoaController pessoaController, ParticipacaoController participacaoController) {
		this.projetoController = projetoController;
		this.pessoaController = pessoaController;
		this.participacaoController = participacaoController;
	}
	
	public ProjetoController getProjetoController(){
		return this.projetoController;
	}
	
	public PessoaController getPessoaController(){
		return this.pessoaController;
	}
	
	public ParticipacaoController getParticipacaoController(){
		return this.participacaoController;
	}

}
