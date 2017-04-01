package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.ProjetoController;

public class Persistencia {
	
	private Controllers controllers;
	private File path = new File("arquivos_sistema");
	private File arquivo = new File (path + File.separator + "cpc_ufcg.dat");
	
	private Controllers iniciaSistema() throws IOException, ClassNotFoundException{
		try {
			FileInputStream fis = new FileInputStream(arquivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			controllers = (Controllers) ois.readObject();
			fis.close();
		} catch (FileNotFoundException e) {
			controllers = new Controllers(new ProjetoController(), new PessoaController());
			path.mkdir();
		}

		return controllers;
	}
	
	public PessoaController iniciaPessoaController() throws ClassNotFoundException, IOException {
		return this.iniciaSistema().getPessoaController();
	}
	
	public ProjetoController iniciaProjetoController() throws ClassNotFoundException, IOException {
		return this.iniciaSistema().getProjetoController();
	}
	
	public ParticipacaoController iniciaParticipacaoController() throws ClassNotFoundException, IOException {
		return this.iniciaSistema().getParticipacaoController();
	}
	
	public void fechaSistma(ProjetoController projetoController, PessoaController pessoaController, 
			ParticipacaoController participacaoController) throws IOException {
		controllers = new Controllers(projetoController, pessoaController, participacaoController);
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(controllers);
		fos.close();
	}

}
