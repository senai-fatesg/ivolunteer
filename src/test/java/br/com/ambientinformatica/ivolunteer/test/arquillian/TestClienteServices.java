package br.com.ambientinformatica.ivolunteer.test.arquillian;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.util.List;
//
//import javax.ejb.EJB;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.shrinkwrap.api.ArchivePaths;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import br.com.ambientinformatica.ivolunteer.entidade.Contato;
//
//@RunWith(Arquillian.class)
public class TestClienteServices {
//
//	@EJB
//	SpringServiceAdapter contatoServices;
//
//	@Deployment
//	public static JavaArchive createTestArchive() {
//		return ShrinkWrap.create(JavaArchive.class, "testContato.jar")
//				.addPackages(true, "br.com.ambientinformatica.ivolunteer.entidade")
//				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
//				.addAsManifestResource("test-persistence.xml", "persistence.xml");
//	}
//
//	@Test
//	public void testAdicionaEConsultaClientes() {
//		List<Contato> contatos = contatoServices.findAll();
//
//		assertNotNull(contatos);
//		assertEquals(0, contatos.size());
//
//		contatoServices.add(new Contato("Cliente 1"));
//		contatoServices.add(new Contato("Cliente 2"));
//
//		contatos = contatoServices.findAll();
//		assertNotNull(contatos);
//		assertEquals(2, contatos.size());
//		assertEquals("Cliente 1", contatos.get(0).getNome());
//		assertEquals("Cliente 2", contatos.get(1).getNome());
//	}
//
}