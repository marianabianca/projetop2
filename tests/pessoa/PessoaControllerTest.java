package pessoa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.LogicaException;
import exception.ParametroInvalidoException;

public class PessoaControllerTest {

	PessoaController pessoaController;
	
	@Before
	public void setup() {
		pessoaController = new PessoaController();
	}
	
	@Test
	public void cadastraPessoaTest() throws LogicaException {
		try {
			pessoaController.cadastraPessoa(null, "nome", "email");
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			pessoaController.cadastraPessoa("   ", "nome", "email");
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("CPF nulo ou vazio", e.getMessage());
		}
		
		try {
			pessoaController.cadastraPessoa("0", "nome", "email");
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("CPF invalido", e.getMessage());
		}
		
		try {
			pessoaController.cadastraPessoa("000.000.000-00", null, "email");
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("nome nulo ou vazio", e.getMessage());
		}
		
		try {
			pessoaController.cadastraPessoa("000.000.000-00", "   ", "email");
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("nome nulo ou vazio", e.getMessage());
		}
		
		try {
			pessoaController.cadastraPessoa("000.000.000-00", "nome", null);
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("email nulo ou vazio", e.getMessage());
		}
		
		try {
			pessoaController.cadastraPessoa("000.000.000-00", "nome", "  ");
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("email nulo ou vazio", e.getMessage());
		}
		
		try {
			pessoaController.cadastraPessoa("000.000.000-00", "nome", "email@email@email.com");
			Assert.fail("LogicaException nao capturada");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("email invalido", e.getMessage());
		}
		
		pessoaController.cadastraPessoa("000.000.000-00", "pessoa1", "email1@email.com");
				
		try {
			pessoaController.cadastraPessoa("000.000.000-00", "pessoa2", "email2@email.com");
			Assert.fail("LogicaException nao capturada");
		} catch (LogicaException e) {
			Assert.assertEquals("Pessoa com mesmo CPF ja cadastrada", e.getMessage());
		}
	}
	
	@Test
	public void getInfoPessoaTest() throws LogicaException {
		try {
			pessoaController.getInfoPessoa("000.000.000-00", "nome");
			Assert.fail("LogicaException nao capturada");
		} catch (LogicaException e) {
			Assert.assertEquals("Pessoa nao encontrada", e.getMessage());
		}
		
		pessoaController.cadastraPessoa("000.000.000-00", "pessoa1", "email1@email.com");
		
		try {
			pessoaController.getInfoPessoa("000.000.000-00", "idade");
			Assert.fail("LogicaException nao capturada");
		} catch (LogicaException e) {
			Assert.assertEquals("Atributo inexistente", e.getMessage());
		}
		
		Assert.assertEquals("pessoa1", pessoaController.getInfoPessoa("000.000.000-00", "nome"));
		Assert.assertEquals("emai1@email.com", pessoaController.getInfoPessoa("000.000.000-00", "email"));
		Assert.assertEquals("", pessoaController.getInfoPessoa("000.000.000-00", "participacoes"));
	}
	
	@Test
	public void getPessoaTest() throws LogicaException {
		try {
			pessoaController.getPessoa("000.000.000-00");
			Assert.fail();
		} catch (LogicaException e) {
			Assert.assertEquals("Pessoa nao encontrada", e.getMessage());
		}
		
		pessoaController.cadastraPessoa("000.000.000-00", "pessoa1", "email1@email.com");
		String cpf = pessoaController.getPessoa("000.000.000-00").getCpf();
		
		Assert.assertEquals("000.000.000-00", cpf);
	}
	
	@Test
	public void removePessoaTest() throws LogicaException {
		pessoaController.cadastraPessoa("000.000.000-00", "pessoa1", "email1@email.com");
		pessoaController.removePessoa("000.000.000-00");
		
		try {
			pessoaController.getPessoa("000.000.000-00");
			Assert.fail("LogicaException nao capturada");
		} catch (LogicaException e) {
			Assert.assertEquals("Pessoa nao encontrada", e.getMessage());
		}
	}
	
}
