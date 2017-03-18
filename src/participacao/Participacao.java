package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public abstract class Participacao implements Comparable<Participacao> {

	private Pessoa pessoa;
	protected Projeto projeto;
	private double valorHora;
	private int qntHoras;

	public Participacao(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		this.pessoa = pessoa;
		this.projeto = projeto;
		this.valorHora = valorHora;
		this.qntHoras = qntHoras;
	}

	public String getNomeDaPessoa() {
		return this.pessoa.getNome();
	}

	public String getCpfDaPessoa() {
		return this.pessoa.getCpf();
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public String getNomeDoProjeto() {
		return this.projeto.getNome();
	}

	public String getCodigoDoProjeto() {
		return this.projeto.getCodigo();
	}

	@Override
	public int compareTo(Participacao outra) {
		return this.pessoa.getNome().compareTo(outra.getPessoa().getNome());
	}
	
	public String getDataInicioDoProjeto() {
		return this.projeto.getDataInicio();
	}
	
	public abstract int calculaPontuacao();

}