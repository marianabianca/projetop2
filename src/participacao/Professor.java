package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class Professor extends Participacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean coordenador;

	public Professor(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras, boolean coordenador) {
		super(pessoa, projeto, valorHora, qntHoras);
		this.coordenador = coordenador;
	}

	/**
	 * Metodo responsavel por analizar se eh um coordenador.
	 * 
	 * @return - true, se for coordenador, false, se nao.
	 */
	@Override
	public boolean isCoordenador() {
		return this.coordenador;
	}

	/**
	 * o metodo tem como objetivo calcular o os pontos por ano. caso seja
	 * monitoria, sera quatro pontos a cada ano, sendo outro tipo(pet, ped,
	 * extensao), sera quatro pontos a cada ano e mais um ponto por cada aluno
	 * participante do projeto.
	 * 
	 * @return - retornara os pontos referentes ao participacao, especificamente
	 *         de "professor".
	 */
	@Override
	public double calculaPontuacao() {
		double porAno = (projeto.getDuracao() / 12);
		if (isMonitoria()) {
			return (porAno * 4);
		} else {
			return (super.projeto.calculaPontuacao() + (porAno * 4));
		}
	}

	/**
	 * Metodo responsavel por retornar o adicional da bolsa que, no caso de ser
	 * coordenador, ganhara 40% de adicional.
	 */
	@Override
	public double getModificadorBolsa() {
		if (this.coordenador) {
			return 1.4;
		}
		return 1;
	}

	/**
	 * Metodo responsavel por quebrar o default da classe pai.
	 */
	@Override
	public boolean isProfessor() {
		return true;
	}

	/**
	 * Metodo responsavel por retornar a bolsa do professor, caso haja.
	 */
	@Override
	public double getBolsa() {
		double bolsa = super.getBolsa();
		if (this.isCoordenador()) {
			if (this.isExtensao() || this.isMonitoria()) {
				return 0;
			}
		}
		return bolsa;
	}

}
