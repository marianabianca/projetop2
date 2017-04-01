package projeto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.LogicaException;
import exception.ParametroInvalidoException;

public class ProjetoPICIBPIBITITest {

	Projeto projetoPIBICPIBITI;
	
	@Before
	public void setup() {
		this.projetoPIBICPIBITI = new ProjetoPIBICPIBITI("projeto", "PIBIC", 1, 1, 1, "pesquisa", "20/10/2015", 12, "1");
	}
	
	@Test
	public void getInfoProjetoTest() throws Exception {
		try {
			projetoPIBICPIBITI.getInfoProjeto("disciplina");
			Assert.fail("LogicaException nao capturada para atributo invalido disciplina");
		} catch (LogicaException e) {
			Assert.assertEquals("PED nao possui disciplina", e.getMessage());
		}
		
		Assert.assertEquals("projeto", projetoPIBICPIBITI.getInfoProjeto("nome"));
		Assert.assertEquals("PIBIC", projetoPIBICPIBITI.getInfoProjeto("categoria"));
		Assert.assertEquals("1", projetoPIBICPIBITI.getInfoProjeto("producao academica"));
		Assert.assertEquals("1", projetoPIBICPIBITI.getInfoProjeto("producao tecnica"));
		Assert.assertEquals("1", projetoPIBICPIBITI.getInfoProjeto("patentes"));
		Assert.assertEquals("pesquisa", projetoPIBICPIBITI.getInfoProjeto("objetivo"));
		Assert.assertEquals("20/10/2015", projetoPIBICPIBITI.getInfoProjeto("data de inicio"));
		Assert.assertEquals("12", projetoPIBICPIBITI.getInfoProjeto("duracao"));
		Assert.assertEquals("", projetoPIBICPIBITI.getInfoProjeto("participacoes"));
	}
	
	@Test
	public void editaProjetoTest() throws Exception {
		try {
			projetoPIBICPIBITI.editaProjeto("disciplina", "p1");
			Assert.fail("LogicaException nao capturada para atributo invalido disciplina");
		} catch (LogicaException e) {
			Assert.assertEquals("PED nao possui disciplina", e.getMessage());
		}
		
		projetoPIBICPIBITI.editaProjeto("nome", "projetoE");
		Assert.assertEquals("projetoE", projetoPIBICPIBITI.getInfoProjeto("nome"));
		projetoPIBICPIBITI.editaProjeto("categoria", "PIBITI");
		Assert.assertEquals("PIBITI", projetoPIBICPIBITI.getInfoProjeto("categoria"));
		projetoPIBICPIBITI.editaProjeto("producao academica", "2");
		Assert.assertEquals("2", projetoPIBICPIBITI.getInfoProjeto("producao academica"));
		projetoPIBICPIBITI.editaProjeto("producao tecnica", "2");
		Assert.assertEquals("2", projetoPIBICPIBITI.getInfoProjeto("producao tecnica"));
		projetoPIBICPIBITI.editaProjeto("patentes", "2");
		Assert.assertEquals("2", projetoPIBICPIBITI.getInfoProjeto("patentes"));
		projetoPIBICPIBITI.editaProjeto("objetivo", "pesquisaE");
		Assert.assertEquals("pesquisaE", projetoPIBICPIBITI.getInfoProjeto("objetivo"));
		projetoPIBICPIBITI.editaProjeto("data de inicio", "20/11/2015");
		Assert.assertEquals("20/11/2015", projetoPIBICPIBITI.getInfoProjeto("data de inicio"));
		projetoPIBICPIBITI.editaProjeto("duracao", "8");
		Assert.assertEquals("8", projetoPIBICPIBITI.getInfoProjeto("duracao"));
	}
	
	@Test
	public void atualizaDespesasTest() throws ParametroInvalidoException {
		try {
			projetoPIBICPIBITI.atualizaDespesas(0, 0, 0);
			Assert.fail("ParametroInvalidoException nao capturado");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("projeto do tipo P&D - PIBIC ou PIBIT deve permitir despesas de bolsas", e.getMessage());
		}
		
		try {
			projetoPIBICPIBITI.atualizaDespesas(1, 1, 1);
			Assert.fail("ParametroInvalidoException nao capturado");
		} catch (ParametroInvalidoException e) {
			Assert.assertEquals("projeto do tipo P&D - PIBIC ou PIBIT nao permite despesas de custeio ou capital", e.getMessage());
		}
		
		projetoPIBICPIBITI.atualizaDespesas(5000, 0, 0);
	}
	
	@Test
	public void calculaColaboracaoUASCTest() throws ParametroInvalidoException {
		Assert.assertEquals(0, projetoPIBICPIBITI.calculaColaboracaoUASC(), 0.01);
		
		projetoPIBICPIBITI.atualizaDespesas(11000, 0, 0);
		Assert.assertEquals(0, projetoPIBICPIBITI.calculaColaboracaoUASC(), 0.01);
	}

}
