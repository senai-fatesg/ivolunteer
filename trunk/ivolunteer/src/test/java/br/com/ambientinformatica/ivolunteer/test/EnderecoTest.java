package br.com.ambientinformatica.ivolunteer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.sql.Insert;
import org.junit.Test;

import br.com.ambientinformatica.ivolunteer.entidade.Cidade;
import br.com.ambientinformatica.ivolunteer.entidade.Endereco;
import br.com.ambientinformatica.ivolunteer.entidade.EnumEstado;
import br.com.ambientinformatica.ivolunteer.entidade.EnumTipoTelefone;
import br.com.ambientinformatica.ivolunteer.entidade.Telefone;
import br.com.ambientinformatica.ivolunteer.persistencia.EnderecoDaoJpa;
import br.com.ambientinformatica.ivolunteer.persistencia.TelefoneDaoJpa;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;

public class EnderecoTest {
	
	@Test
	public void TestEndereco(){
		try{
			inserir();
		}catch(Exception erro){
			
		}
	}
	
	public void inserir() throws PersistenciaException {
	
		EnderecoDaoJpa daoEndereco= new EnderecoDaoJpa();
		Endereco endereco = new Endereco();
		endereco = CrieObjetoEndereco();
		daoEndereco.incluir(endereco);
	   
      Endereco EnderecoConsultado = new Endereco();
      try{
      	//consultando objeto endereço
      	EnderecoConsultado = daoEndereco.consultar(endereco.getId());
      	//realizando a verificação da integridade dos objetos
         assertEquals(EnderecoConsultado.getcEP(),endereco.getcEP());
      	
      }catch(Exception erro){
      	fail("Erro: falha ao inserir objeto 'endereco'.");
      }
      
	}
      
	//criação de objetos
	public Endereco CrieObjetoEndereco(){
		Endereco endereco = new Endereco();
		endereco.setBairro("LESTE UNIVERSITARIO");
		endereco.setcEP("7500-800");
		endereco.setComplemento("PROXIMO DA PRAÇA DA BIBLIA");
		endereco.setCidade(CrieObjetoCidade());
		endereco.setLote("11");
		endereco.setNumero(800);
		endereco.setQuadra("14");
		endereco.setRuaOuAvenida("RUA 15");
		
		return endereco;
	}
	public Cidade CrieObjetoCidade(){
		Cidade cidade = new Cidade();
		cidade.setNomeCidade("GOIÂNIA");
		cidade.setEnumEstado(EnumEstado.GO);
		
		return cidade;
	}

}
