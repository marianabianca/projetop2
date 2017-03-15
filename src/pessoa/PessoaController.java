package pessoa;

import validacao.ValidaPessoa;

public class PessoaController {
	private PessoaService pessoaService;
	
	public PessoaController(){
		pessoaService = new PessoaService();
	}	
	
	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		try {
			ValidaPessoa.validaCpf(cpf);
			ValidaPessoa.validaNome(nome);
			ValidaPessoa.validaEmail(email);
			pessoaService.cadastraPessoa(cpf, nome, email);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de pessoa: " + e.getMessage());
		}
		
		return cpf;
	}

	public void removePessoa(String cpf) {
		pessoaService.removePessoa(cpf);
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		return pessoaService.getInfoPessoa(cpf, atributo);
	}

	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws Exception {
		try {
			ValidaPessoa.validaCpf(cpfPessoa);
			pessoaService.editaPessoa(cpfPessoa, atributo, valor);
		} catch (Exception e) {
			throw new Exception("Erro na atualizacao de pessoa: " + e.getMessage());
		}
	}
	
	
}
