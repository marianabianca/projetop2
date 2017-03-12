package participacao;

public class Profissional extends Participacao {
	
	private String cargo;

	public Profissional(double valorHora, int qntHoras, String cargo) {
		super(valorHora, qntHoras);
		this.cargo = cargo;
	}

}
