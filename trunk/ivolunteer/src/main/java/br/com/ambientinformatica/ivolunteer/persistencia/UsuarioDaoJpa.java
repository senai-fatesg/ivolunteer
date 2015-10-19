package br.com.ambientinformatica.ivolunteer.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ambientinformatica.ivolunteer.entidade.Usuario;
import br.com.ambientinformatica.jpa.persistencia.PersistenciaJpa;
import br.com.ambientinformatica.util.UtilLog;

@Repository("usuarioDao")
public class UsuarioDaoJpa extends PersistenciaJpa<Usuario> implements UsuarioDao{

   private static final long serialVersionUID = 1L;

   
	public Usuario consultarPorLogin(String login) throws PersistenceException {
		try {
			String sql = "select distinct u from Usuario u left join fetch u.papeis p where u.login = :login";
			Query query = em.createQuery(sql);
			query.setParameter("login", login);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			UtilLog.getLog().error(e.getMessage(), e);
			throw new PersistenceException("Erro ao consultar usuário pelo Login");
		}
	}

    @Override
    public List<Usuario> consultarPorNome(String nomePessoa) throws PersistenceException {
        try {
            TypedQuery<Usuario> query = em.createQuery("select distinct u from Usuario u left join fetch u.pessoa p where upper(p.nomePessoa) like upper(:nomePessoa)", Usuario.class);
            query.setParameter("nomePessoa", "%" + nomePessoa + "%");
            return query.getResultList();
        } catch (Exception e) {
            UtilLog.getLog().error(e.getMessage(), e);
            throw new PersistenceException("Erro ao consultar o usuário");
        }
    }

}
