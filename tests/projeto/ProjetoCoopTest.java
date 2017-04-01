package projeto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.LogicaException;
import exception.ParametroInvalidoException;

public class ProjetoCoopTest {

	Projeto projetoCoop;
	
	@Before
	public void setup() {
		this.projetoCoop = new ProjetoCoop("projeto", "COOP", 1, 1, 1, "pesquisa", "20/10/2015", 12, "1");
	}
	
	@Test
	public void getInfoProjetoTest() throws Exception {
		try {
			projetoCoop.getInfoProjeto("disciplina");
			Assert.fail("LogicaException nao capturada para atributo invalido disciplina");
		} catch (LogicaException e) {
			Assert.assertEquals("PED nao possui disciplina", e.getMessage());
		}
		
		Assert.assertEquals("projeto", projetoCoop.getInfoProjeto("nome"));
		Assert.assertEquals("COOP", projetoCoop.getInfoProjeto("categoria"));
		Assert.assertEquals("1", projetoCoop.getInfoProjeto("producao academica"));
		Assert.assertEquals("1", projetoCoop.getInfoProjeto("producao tecnica"));
		Assert.assertEquals("1", projetoCoop.getInfoProjeto("patentes"));
		Assert.assertEquals("pesquisa", projetoCoop.getInfoProjeto("objetivo"));
		Assert.assertEquals("20/10/2015", projetoCoop.getInfoProjeto("data de inicio"));
		Assert.assertEquals("12", projetoCoop.getInfoProjeto("duracao"));
		Assert.assertEquals("", projetoCoop.getInfoProjeto("participacoes"));
	}
	
	@Test
	public void editaProjetoTest() throws Exception {
		try {
			projetoCoop.editaProjeto("disciplina", "p1");
			Assert.fail("LogicaException nao capturada para atributo invalido disciplina");
		} catch (LogicaException e) {
			Assert.assertEquals("PED nao possui disciplina", e.getMessage());
		}
		
		projetoCoop.editaProjeto("nome", "projetoE");
		Assert.assertEquals("projetoE", projetoCoop.getInfoProjeto("nome"));
		projetoCoop.editaProjeto("categoria", "PED");
		Assert.assertEquals("PED", projetoCoop.getInfoProjeto("categoria"));
		projetoCoop.editaProjeto("producao academica", "2");
		Assert.assertEquals("2", projetoCoop.getInfoProjeto("producao academica"));
		projetoCoop.editaProjeto("producao tecnica", "2");
		Assert.assertEquals("2", projetoCoop.getInfoProjeto("producao tecnica"));
		projetoCoop.editaProjeto("patentes", "2");
		Assert.assertEquals("2", projetoCoop.getInfoProjeto("patentes"));
		projetoCoop.editaProjeto("objetivo", "pesquisaE");
		Assert.assertEquals("pesquisaE", projetoCoop.getInfoProjeto("objetivo"));
		projetoCoop.editaProjeto("data de inicio", "20/11/2015");
		Assert.assertEquals("20/11/2015", projetoCoop.getInfoProjeto("data de inicio"));
		projetoCoop.editaProjeto("duracao", "8");
		Assert.assertEquals("8", projetoCoop.getInfoProjeto("duracao"));
	}
	
	@Test
	public void atualizaDespesasTest() throws ParametroInvalidoException {
		try {
			projetoCoop.atualizaDespesas(0, 0, 0);
			Assert.fail("ParametroInvalidoException nao capturado");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("projeto do tipo Coop devem possuir todas as despesas", e.getMessage());
		}
		
		try {
			projetoCoop.atualizaDespesas(0, 1, 0);
			Assert.fail("ParametroInvalidoException nao capturado");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("projeto do tipo Coop devem possuir todas as despesas", e.getMessage());
		}
		
		projetoCoop.atualizaDespesas(5000, 5000, 5000);
	}
	
	@Test
	public void calculaColaboracaoUASCTest() throws ParametroInvalidoException {
		Assert.assertEquals(0, projetoCoop.calculaColaboracaoUASC(), 0.01);
		
		projetoCoop.atualizaDespesas(11000, 11000, 11000);
		Assert.assertEquals(4323, projetoCoop.calculaColaboracaoUASC(), 0.01);
	}
	
}
