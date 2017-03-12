package participacao;

public class AlunoPosGraduando extends Participacao {
	
	private String vinculo;

	public AlunoPosGraduando(double valorHora, int qntHoras, String vinculo) {
		super(valorHora, qntHoras);
		this.vinculo = vinculo;
	}

}
