package participacao;

public class Professor extends Participacao {

	private boolean coordenador;

	public Professor(double valorHora, int qntHoras, boolean coordenador) {
		super(valorHora, qntHoras);
		this.coordenador = coordenador;
	}

}
