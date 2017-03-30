package pessoa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import exception.LogicaException;
import participacao.Participacao;
import validacao.ValidaPessoa;

public class PessoaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Pessoa> pessoas;
	private ValidaPessoa validaPessoa;

	public PessoaController() {
		this.pessoas = new HashMap<>();
		this.validaPessoa = new ValidaPessoa();
	}

	/**
	 * O MÉTODO TEM COMO OBJETIVO VERIFICAR SE O CPF, NOME E EMAIL PASSADOS SÃO
	 * VÁLIDOS, CASO NÃO SEJA, ELE LANÇARÁ UMA EXCEPTION, CASO SEJA, ELE CRIARÁ
	 * UMA NOVA PESSOA E COLOCARÁ DENTRO DO MAP DE PESSOAS.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param nome
	 *            RECEBE UMA STRING QUE CORRESPONDE AO NOME PARA A NOVA PESSOA.
	 * @param email
	 *            RECEBE UMA STRING QUE CORRESPONDE AO EMAIL PARA A NOVA PESSOA.
	 * @return RETORNA O CPF DA PESSOA CRIADA.
	 * @throws Exception
	 *             CASO OS PARÂMETROS NÃO SEJAM VÁLIDOS, O SISTEMA LANÇARÁ O UMA
	 *             MENSAGEM DE ERRO.
	 */
	public String cadastraPessoa(String cpf, String nome, String email) throws LogicaException {
		validaPessoa.validaCpf(cpf);
		validaPessoa.validaNome(nome);
		validaPessoa.validaEmail(email);
		if (this.contemPessoa(cpf)) {
			throw new LogicaException("Pessoa com mesmo CPF ja cadastrada");
		}
		Pessoa pessoa = new Pessoa(nome, email, cpf);
		pessoas.put(cpf, pessoa);

		return cpf;
	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR RETORNAR A INFORMAÇÃO PEDIDA PELO O USUÁRIO
	 * SOBRE A PESSOA, CASO O ATRIBUTO NÃO SEJA O VÁLIDO, O SISTEMA DEVERÁ
	 * LANÇAR UMA EXCEPTION.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO BUSCADA SOBRE O
	 *            PESSOA.
	 * @return INFORMAÇÃO PEDIDA PELO USUÁRIO.
	 * @throws Exception
	 *             CASO O ATRIBUTO NÃO SEJA VÁLIDO, O SISTEMA LANÇARÁ UMA
	 *             MENSAGEM DE ERRO.
	 */
	public String getInfoPessoa(String cpf, String atributo) throws LogicaException {
		try {
			Pessoa pessoa = this.getPessoa(cpf);
			return this.selecionaInfoPessoa(pessoa, atributo);
		} catch (LogicaException e) {
			throw new LogicaException("Erro na consulta de pessoa: " + e.getMessage());
		}
	}

	/**
	 * O método seleciona qual método deve ser chamado para retornar a
	 * informação pedida como parâmentro "atributo"
	 * 
	 * @param pessoa
	 *            - pessoa da informação desejada
	 * @param atributo
	 *            - atributo desejado para pegar a informação
	 * @return String - a informação pedida no parâmetro "atributo"
	 * @throws LogicaException
	 *             - caso o atributo desejado não exista
	 */
	private String selecionaInfoPessoa(Pessoa pessoa, String atributo) throws LogicaException {
		switch (atributo.toLowerCase()) {
		case "email":
			return pessoa.getEmail();
		case "nome":
			return pessoa.getNome();
		case "participacoes":
			return pessoa.getParticipacoes();
		default:
			throw new LogicaException("Atributo inexistente");
		}
	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR PROCURAR UMA PESSOA EM "pessoas" A PARTIR DE
	 * UM CPF.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA BUSCADA.
	 * @return RETORNA A PESSOA DESEJADA.
	 * @throws LogicaException
	 *             CASO O CPF NÃO CORRESPONDA A DETERMINADA PESSOA, O SISTEMA
	 *             DEVERÁ LANÇAR UMA EXCEPTION.
	 */
	public Pessoa getPessoa(String cpf) throws LogicaException {
		for (String cpfDaPessoa : pessoas.keySet()) {
			if (cpf.equals(cpfDaPessoa)) {
				return pessoas.get(cpfDaPessoa);
			}
		}
		throw new LogicaException("Pessoa nao encontrada");
	}

	/**
	 * O MÉTODO É RESPONSÁVEL POR REMOVER UMA PESSOA EM "pessoas" A PARTIR DE UM
	 * CPF.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA BUSCADA.
	 * @return RETORNA A OPERAÇÃO FOI FEITA COM SUCESSO.
	 */
	public void removePessoa(String cpf) {
		this.pessoas.remove(cpf, pessoas.get(cpf));
	}

	/**
	 * O MÉTODO É RESPONSÁVEL PARA EDITAR O "atributo" DA PESSOA CORRESPONDENTE
	 * AO CPF PARA O VALOR, CASO OS PARÂMETROS NÃO SEJA O VÁLIDO, O SISTEMA
	 * DEVERÁ LANÇAR UMA EXCEPTION.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF PARA A NOVA PESSOA.
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO QUE DESEJA SER
	 *            MODIFICADA EM PESSOA.
	 * @param valor
	 *            RECEBE UMA STRING QUE CORRESPONDE PARA O QUE O ATRIBUTO SERÁ
	 *            MUDADO PESSOA.
	 * @throws Exception
	 *             CASO OS PARÂMETROS NÃO SEJAM VÁLIDOS, O SISTEMA DEVERÁ LANÇAR
	 *             UMA MENSAGEM DE ERRO
	 * 
	 */
	public void editaPessoa(String cpfPessoa, String atributo, String valor) throws LogicaException {
		validaPessoa.validaCpf(cpfPessoa);
		this.valorAtributoValidos(atributo, valor);

		Pessoa aEditar = getPessoa(cpfPessoa);
		if (atributo.equalsIgnoreCase("nome")) {
			aEditar.setNome(valor);
		} else {
			aEditar.setEmail(valor);
		}
	}

	/**
	 * O método é responsável por adicionar participações dentro de pessoa.
	 * 
	 * @param cpfPessoa
	 *            - cpf da pessoa que deseja adicionar a participação.
	 * @param participacao
	 *            - participação a ser adicionada.
	 * @throws LogicaException
	 *             - caso os parâmetros não sejam os esperados, lançará uma
	 *             exceção.
	 */
	public void adicionaParticipacao(String cpfPessoa, Participacao participacao) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.adicionaParticipacao(participacao);
	}

	/**
	 * O método é responsável por remover participações dentro de pessoa.
	 * 
	 * @param cpfPessoa
	 *            - cpf da pessoa que deseja remover a participação.
	 * @param codigoProjeto
	 *            - participacao de projeto a ser removida.
	 * @throws LogicaException
	 *             - caso os parâmetros não sejam os esperados, lançará uma
	 *             exceção.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.removeParticipacao(codigoProjeto);
	}

	/**
	 * MÉTODO RESPONSÁVEL POR VERIFICAR SE JÁ HÁ OUTRA PESSOA COM ESSE MESMO
	 * CPF.
	 * 
	 * @param cpf
	 *            RECEBE UMA STRING QUE CORRESPONDE A UM CPF DE UMA PESSOA.
	 * @return RETORNA SE HÁ(TRUE) OU NÃO(FALSE) ALGUMA PESSOA COM O MESMO CPF.
	 */
	private boolean contemPessoa(String cpf) {
		if (pessoas.containsKey(cpf)) {
			return true;
		}
		return false;
	}

	/**
	 * MÉTODO RESPONSÁVEL POR VALIDAR AS ENTRADAS DE "valor".
	 * 
	 * @param atributo
	 *            RECEBE UMA STRING QUE CORRESPONDE A INFORMAÇÃO QUE DESEJA SER
	 *            MODIFICADA EM PESSOA.
	 * @param valor
	 *            RECEBE UMA STRING QUE CORRESPONDE PARA O QUE O ATRIBUTO SERÁ
	 *            MUDADO PESSOA.
	 * @throws LogicaException
	 *             CASO O CPF NÃO SEJA REFERENTE A UMA PESSOA CADASTRADA NO
	 *             SISTEMA.
	 */
	private void valorAtributoValidos(String atributo, String valor) throws LogicaException {
		if (atributo.equalsIgnoreCase("cpf")) {
			throw new LogicaException("CPF nao pode ser alterado");
		} else if (atributo.equalsIgnoreCase("nome")) {
			validaPessoa.validaNome(valor);
		} else {
			validaPessoa.validaEmail(valor);
		}
	}

	/**
	 * MÉTODO "calculaPontuacaoPorParticipacao" TEM COMO OBJETIVO ENCONTRAR A
	 * PESSOA ATRAVÉS DO CPF E DEPOIS DISSO CHAMAR O MÉTODO DE "Pessoa" A PARTIR
	 * DA "pessoa" ENCONTRADA.
	 * 
	 * @param cpfPessoa
	 *            RECEBE UMA STRING QUE CORRESPONDE AO CPF DA PESSOA QUE DESEJA
	 *            CALCULAR.
	 * @return CHAMARÁ O MÉTODO DE "Pessoa" A PARTIR DA "pessoa" ENCONTRADA.
	 * @throws LogicaException
	 *             CASO O CPF NÃO SEJA REFERENTE A UMA PESSOA CADASTRADA NO
	 *             SISTEMA.
	 */
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		return pessoa.calculaPontuacaoPorParticipacao();
	}

	/**
	 * Método usado para "pegar" o valor total da(s) bolsa(s) recebidas por uma
	 * pessoa
	 * 
	 * @param cpfPessoa
	 *            - cpf da pessoa a ser "pego" o valor da bolsa
	 * @return double - o valor total da(s) bolsa(s)
	 * @throws LogicaException
	 *             - caso o cpf não seja referente a uma pessoa cadastrada no
	 *             sistema
	 */
	public double getValorBolsa(String cpfPessoa) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		return pessoa.getValorBolsa();
	}

}
