package participacao;

import pessoa.Pessoa;
import projeto.Projeto;
import projeto.ProjetoMonitoria;

public class AlunoGraduando extends Participacao {

	public AlunoGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) {
		super(pessoa, projeto, valorHora, qntHoras);
	}

	@Override
	public int calculaPontuacao() {
		if (super.projeto.getClass() == ProjetoMonitoria.class ){
			// calcula pra monitoria
		}else{
			// calcula pra ped, pet e extensão
		}
		return 0;
	}

}
