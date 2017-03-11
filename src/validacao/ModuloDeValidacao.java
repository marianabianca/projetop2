package validacao;


import exceptions.*;

public class ModuloDeValidacao {

	public static void objetoNulo(Object obj) throws ObjetoNuloException {
		if (obj == null) {
			throw new ObjetoNuloException("Objeto nulo.");
		}
	}

	public static void participanteNaoEncontrado() throws ParticipanteNaoEncontradoException {
		throw new ParticipanteNaoEncontradoException("Participante nao encontrado.");
	}
	
	public static void stringInvalida(String str) throws StringInvalidaException {
		if (str == null) {
			throw new StringInvalidaException("nulo ou vazio");
		}
		if (str.trim().equals("")) {
			throw new StringInvalidaException("nulo ou vazio");
		} 
	}
	
	public static void duracaoInvalida(int duracao) throws DuracaoInvalidaException {
		if (duracao <= 0) {
			throw new DuracaoInvalidaException("Duracao nao pode ser menor ou igual a zero.");
		}
	}

	public static void cpfInvalido(String cpf) throws StringInvalidaException {
		ModuloDeValidacao.stringInvalida(cpf);	
		ModuloDeValidacao.cpfFormatoInvalido(cpf);		
	}
	
	private static void cpfFormatoInvalido(String cpf) throws StringInvalidaException {
		String ponto1 = Character.toString(cpf.charAt(3));
		String ponto2 = Character.toString(cpf.charAt(7));
		String hifen = Character.toString(cpf.charAt(11));
		
		if (cpf.length() != 11){
			throw new StringInvalidaException("invalido");
		}
		else if (!(ponto1.equals(".") && ponto2.equals(".") & hifen.equals("-"))){
			throw new StringInvalidaException("invalido");
		}
	}
	
	public static void impactoInvalido(int impacto) throws Exception{
		if (impacto < 1 || impacto > 6){
			throw new Exception("Impacto inválido.");
		}
	}
	
	public static void rendimentoInvalido(int rendimento) throws Exception{
		if (rendimento < 0 || rendimento > 100){
			throw new Exception("Rendimento inválido.");
		}
	}
	
	public static void inteiroInvalido(int inteiro) throws Exception{
		if (inteiro < 0){
			throw new Exception("Número inválido.");
		}
	}
	
	public static void dataInvalida(String data) throws StringInvalidaException {
		ModuloDeValidacao.stringInvalida(data);
		ModuloDeValidacao.formatoDataInvalido(data);
		ModuloDeValidacao.diaInvalido(data);
	}
	
	private static void diaInvalido(String data) throws StringInvalidaException {
		String aux = Character.toString(data.charAt(0)) + Character.toString(data.charAt(1));
		int dia = Integer.parseInt(aux);
		
		if (dia < 1 || dia > 31) {
			throw new StringInvalidaException("Formato de data invalido");
		}
	}
	
	private static void formatoDataInvalido(String data) throws StringInvalidaException {
		String barra1 = Character.toString(data.charAt(2));
		String barra2 = Character.toString(data.charAt(5));
		
		if (data.length() != 10){
			throw new StringInvalidaException("Formato de data invalido");
		} else if (!(barra1.equals("/") && barra2.equals("/"))){
			throw new StringInvalidaException("Formato de data invalido");
		}
	}

	public static void objetivoInvalido(String valor) throws StringInvalidaException {
		if (valor == null || valor.equals("")){
			throw new StringInvalidaException("Objetivo nulo ou vazio");
		}
	}
	
	public static void codigoInvalido(String codigo) throws StringInvalidaException {
		if (codigo == null || codigo.equals("")){
			throw new StringInvalidaException("Código inválido");
		}
	}
	
	
}
