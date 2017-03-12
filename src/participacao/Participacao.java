package participacao;

public class Participacao {
	
	private String dataDeInicio;
	private int duracao, horasSemanais, valorDaHora;
	
	public Participacao(String dataDeInicio, int duracao, int horasSemanais, int valorDaHora) {
		this.dataDeInicio = dataDeInicio;
		this.duracao = duracao;
		this.horasSemanais = horasSemanais;
		this.valorDaHora = valorDaHora;
	}
}
