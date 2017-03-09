package centralDeProjeto;

import exceptions.StringInvalidaException;
import pessoas.Pessoa;

public class centralController {
	
	private CentralDeProjeto central;
	
	public centralController() {
		central = new CentralDeProjeto();
	}
	
	public String adicionaParticipante(String nome, String cpf, String email) throws StringInvalidaException {
		Pessoa pessoa = new Pessoa(nome, email, cpf);
		return central.adicionarParticipante(pessoa);
	}

}
