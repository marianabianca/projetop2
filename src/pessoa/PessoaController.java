package pessoa;

import validacao.validaPessoa;

public class PessoaController {
	private PessoaService pessoaService;
	
	public PessoaController(){
		pessoaService = new PessoaService();
	}	
	
	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		try {
			validaPessoa.validaNome(nome);
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
		pessoaService.editaPessoa(cpfPessoa, atributo, valor);
	}
	
	
}
