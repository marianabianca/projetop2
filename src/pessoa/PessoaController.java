package pessoa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import exception.LogicaException;
import participacao.Participacao;
import validacao.ValidaPessoa;

public class PessoaController implements Serializable {

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
	 * O metodo tem como objetivo verificar se o cpf, nome e email passados sao
	 * validos, caso nao seja, ele lancara uma exception, caso seja, ele criara
	 * uma nova pessoa e colocara dentro do map de pessoas.
	 * 
	 * @param cpf
	 *            - recebe uma string que corresponde ao cpf para a nova pessoa.
	 * @param nome
	 *            - recebe uma string que corresponde ao nome para a nova
	 *            pessoa.
	 * @param email
	 *            - recebe uma string que corresponde ao email para a nova
	 *            pessoa.
	 * @return - retorna o cpf da pessoa criada.
	 * @throws LogicaException
	 *             - caso os parametros nao sejam validos, o sistema lancara o
	 *             uma mensagem de erro.
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
	 * o metodo eh responsavel por retornar a informacao pedida pelo o usuario
	 * sobre a pessoa, caso o atributo nao seja o valido, o sistema devera
	 * lancar uma exception.
	 * 
	 * @param cpf
	 *            - recebe uma string que corresponde ao cpf para a nova pessoa.
	 * @param atributo
	 *            - recebe uma string que corresponde a informacao buscada sobre o
	 *            pessoa.
	 * @return - informacao pedida pelo usuário.
	 * @throws LogicaException
	 *             - caso o atributo nao seja valido, o sistema lancara uma
	 *             mensagem de erro.
	 */
	public String getInfoPessoa(String cpf, String atributo) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpf);
		return this.selecionaInfoPessoa(pessoa, atributo);
	}

	/**
	 * O metodo seleciona qual metodo deve ser chamado para retornar a
	 * informacao pedida como paramentro "atributo"
	 * 
	 * @param pessoa
	 *            - pessoa da informacao desejada
	 * @param atributo
	 *            - atributo desejado para pegar a informacao
	 * @return String - a informacao pedida no parametro "atributo"
	 * @throws LogicaException
	 *             - caso o atributo desejado nao exista
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
	 * o metodo eh responsavel por procurar uma pessoa em "pessoas" a partir de
	 * um cpf.
	 * 
	 * @param cpf
	 *           - recebe uma string que corresponde ao cpf da pessoa buscada.
	 * @return - retorna a pessoa desejada.
	 * @throws logicaexception
	 *             - caso o cpf nao corresponda a determinada pessoa, o sistema
	 *             devera lancar uma exception.
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
	 * o metodo eh responsavel por remover uma pessoa em "pessoas" a partir de um
	 * cpf.
	 * 
	 * @param cpf
	 *           - recebe uma string que corresponde ao cpf da pessoa buscada.
	 * @return - retorna a operacao foi feita com sucesso.
	 */
	public void removePessoa(String cpf) {
		this.pessoas.remove(cpf, pessoas.get(cpf));
	}

	/**
	 * o metodo eh responsavel para editar o "atributo" da pessoa correspondente
	 * ao cpf para o valor, caso os parametros nao seja o valido, o sistema
	 * devera lancar uma exception.
	 * 
	 * @param cpf
	 *            - recebe uma string que corresponde ao cpf para a nova pessoa.
	 * @param atributo
	 *            - recebe uma string que corresponde a informacao que deseja
	 *            ser modificada em pessoa.
	 * @param valor
	 *            - recebe uma string que corresponde para o que o atributo sera
	 *            mudado pessoa.
	 * @throws LogicaException
	 *             - caso os parametros nao sejam válidos, o sistema devera
	 *             lancar uma mensagem de erro
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
	 * O metodo eh responsavel por adicionar participações dentro de pessoa.
	 * 
	 * @param cpfPessoa
	 *            - cpf da pessoa que deseja adicionar a participação.
	 * @param participacao
	 *            - participação a ser adicionada.
	 * @throws LogicaException
	 *             - caso os parametros nao sejam os esperados, lancara uma
	 *             excecao.
	 */
	public void adicionaParticipacao(String cpfPessoa, Participacao participacao) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.adicionaParticipacao(participacao);
	}

	/**
	 * O metodo eh responsavel por remover participacoes dentro de pessoa.
	 * 
	 * @param cpfPessoa
	 *            - cpf da pessoa que deseja remover a participação.
	 * @param codigoProjeto
	 *            - participacao de projeto a ser removida.
	 * @throws LogicaException
	 *             - caso os parametros nao sejam os esperados, lancara uma
	 *             excecao.
	 */
	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		pessoa.removeParticipacao(codigoProjeto);
	}

	/**
	 * metodo responsavel por verificar se ja ha outra pessoa com esse mesmo
	 * cpf.
	 * 
	 * @param cpf
	 *            - recebe uma string que corresponde a um cpf de uma pessoa.
	 * @return - retorna se ha(true) ou nao(false) alguma pessoa com o mesmo
	 *         cpf.
	 */
	private boolean contemPessoa(String cpf) {
		if (pessoas.containsKey(cpf)) {
			return true;
		}
		return false;
	}

	/**
	 * metodo responsavel por validar as entradas de "valor".
	 * 
	 * @param atributo
	 *            - recebe uma string que corresponde a informacao que deseja
	 *            ser modificada em pessoa.
	 * @param valor
	 *            - recebe uma string que corresponde para o que o atributo sera
	 *            mudado pessoa.
	 * @throws logicaexception
	 *             - caso o cpf nao seja referente a uma pessoa cadastrada no
	 *             sistema.
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
	 * metodo "calculapontuacaoporparticipacao" tem como objetivo encontrar a
	 * pessoa atraves do cpf e depois disso chamar o metodo de "pessoa" a partir
	 * da "pessoa" encontrada.
	 * 
	 * @param cpfpessoa
	 *            - recebe uma string que corresponde ao cpf da pessoa que
	 *            deseja calcular.
	 * @return - chamara o metodo de "pessoa" a partir da "pessoa" encontrada.
	 * @throws logicaexception
	 *             - caso o cpf nao seja referente a uma pessoa cadastrada no
	 *             sistema.
	 */
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		return pessoa.calculaPontuacaoPorParticipacao();
	}

	/**
	 * metodo usado para "pegar" o valor total da(s) bolsa(s) recebidas por uma
	 * pessoa
	 * 
	 * @param cpfPessoa
	 *            - cpf da pessoa a ser "pego" o valor da bolsa
	 * @return double - o valor total da(s) bolsa(s)
	 * @throws LogicaException
	 *             - caso o cpf nao seja referente a uma pessoa cadastrada no
	 *             sistema
	 */
	public double getValorBolsa(String cpfPessoa) throws LogicaException {
		Pessoa pessoa = this.getPessoa(cpfPessoa);
		return pessoa.getValorBolsa();
	}

}
