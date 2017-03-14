package projeto;

import projeto.ProjetoService;

public class ProjetoController {
	private ProjetoService service;
	
	public ProjetoController(){
		service = new ProjetoService();
	}
}
