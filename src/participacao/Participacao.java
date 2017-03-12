package participacao;

public class Participacao {
	
	private String dataDeInicio;
	private int duracao, horasSemanais, valorDaHora;
	
	public Participacao(String dataDeInicio, int duracao, int horasSemanais, int valorDaHora) {
		this.setDataDeInicio(dataDeInicio);
		this.setDuracao(duracao);
		this.horasSemanais = horasSemanais;
		this.valorDaHora = valorDaHora;
	}

	public String getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(String dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
}
