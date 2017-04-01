package pessoa;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.LogicaException;
import participacao.AlunoGraduando;
import participacao.Participacao;
import projeto.Projeto;
import projeto.ProjetoExtensao;
import projeto.ProjetoMonitoria;

public class PessoaTest {

	Pessoa pessoa;
	Projeto projetoMonitoria;
	Projeto projetoExtensao;
	Participacao participacaoGraduandoMonitoria;
	Participacao participacaoGraduandoExtensao;
	
	@Before
	public void setup() {
		pessoa = new Pessoa("nome", "email@email.com", "000.000.000-00");
		projetoMonitoria = new ProjetoMonitoria("monitoria", "p2", 100, "monitorar", "2016.2", "01/01/2017", 12, "1");
		projetoExtensao = new ProjetoExtensao("extensao", "paz mundial", 6, "01/04/2017", 12, "2");
		participacaoGraduandoMonitoria = new AlunoGraduando(pessoa, projetoMonitoria, 20, 12);
		participacaoGraduandoExtensao = new AlunoGraduando(pessoa, projetoExtensao, 30, 12);
	}
	
	@Test
	public void adicionaParticipacaoTest() throws LogicaException {
		pessoa.adicionaParticipacao(participacaoGraduandoMonitoria);
		Assert.assertTrue(pessoa.temParticipacaoEmProjeto("1"));
		pessoa.removeParticipacao("1");
		Assert.assertFalse(pessoa.temParticipacaoEmProjeto("1"));
	}
	
	@Test
	public void removeParticipacaoTest() throws LogicaException {
		try {
			pessoa.removeParticipacao("1");
			Assert.fail("LogicaException de projeto nao exsitente nao capturada");
		} catch (LogicaException e) {
			Assert.assertEquals("Pessoa nao possui participacao no projeto indicado", e.getMessage());
		}
		
		pessoa.adicionaParticipacao(participacaoGraduandoMonitoria);
		pessoa.removeParticipacao("1");
		Assert.assertFalse(pessoa.temParticipacaoEmProjeto("1"));
	}
	
	@Test
	public void temParticipacaoEmProjetoTest() {
		Assert.assertFalse(pessoa.temParticipacaoEmProjeto("1"));
		pessoa.adicionaParticipacao(participacaoGraduandoMonitoria);
		Assert.assertTrue(pessoa.temParticipacaoEmProjeto("1"));
	}
	
	@Test
	public void calculaPontuacaoPorParticipacao() {
		// TODO
	}
	
	@Test
	public void calculaPontuacaoPorParticipacaoPEDExtensaoPETTest() {
		pessoa.adicionaParticipacao(participacaoGraduandoExtensao);
		Assert.assertEquals(4, pessoa.calculaPontuacaoPorParticipacaoPEDExtensaoPET(0, participacaoGraduandoExtensao), 0.01);
		Assert.assertEquals(8, pessoa.calculaPontuacaoPorParticipacaoPEDExtensaoPET(4, participacaoGraduandoExtensao), 0.01);		
		Assert.assertEquals(8, pessoa.calculaPontuacaoPorParticipacaoPEDExtensaoPET(8, participacaoGraduandoExtensao), 0.01);		
	}
	
	@Test
	public void calculaPontuacaoPorParticipacaoMonitoriaTest() {
		pessoa.adicionaParticipacao(participacaoGraduandoMonitoria);
		Assert.assertEquals(3, pessoa.calculaPontuacaoPorParticipacaoMonitoria(0, participacaoGraduandoMonitoria), 0.01);
		Assert.assertEquals(6, pessoa.calculaPontuacaoPorParticipacaoMonitoria(3, participacaoGraduandoMonitoria), 0.01);
		Assert.assertEquals(6, pessoa.calculaPontuacaoPorParticipacaoMonitoria(6, participacaoGraduandoMonitoria), 0.01);
	}
	
	@Test
	public void getValorBolsaTest() {
		Assert.assertEquals(0, pessoa.getValorBolsa(), 0.01);
		pessoa.adicionaParticipacao(participacaoGraduandoExtensao);
		Assert.assertEquals(360, pessoa.getValorBolsa(), 0.01);		
	}
	
	@Test
	public void getParticipacoesTest() {
		Assert.assertEquals("", pessoa.getParticipacoes());
		pessoa.adicionaParticipacao(participacaoGraduandoExtensao);
		pessoa.adicionaParticipacao(participacaoGraduandoMonitoria);
		Assert.assertEquals("extensao, monitoria", pessoa.getParticipacoes());
	}
	
	@Test
	public void equalsTest() {
		Pessoa pessoa1 = new Pessoa("nome", "email@email.com", "000.000.000-00");
		Pessoa pessoa2 = new Pessoa("nome", "email@email.com", "100.000.000-00");
		Assert.assertTrue(pessoa.equals(pessoa));
		Assert.assertTrue(pessoa.equals(pessoa1));
		Assert.assertFalse(pessoa.equals(pessoa2));
	}
	
	@Test
	public void toStringTest() {
		Assert.assertEquals("Nome: nome, e-mail: email@email.com, CPF: 000.000.000-00", pessoa.toString());
	}

}
