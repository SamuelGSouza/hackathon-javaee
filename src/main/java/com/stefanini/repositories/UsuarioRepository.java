package com.stefanini.repositories;

import com.stefanini.dao.GenericDAO;
import com.stefanini.entities.Usuario;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class UsuarioRepository extends GenericDAO<Usuario, Long> {

    public List<Usuario> buscarAniversariantesDoMes(@QueryParam("mes") Integer mes) {
        TypedQuery<Usuario> query = getEntityManager().createQuery(
                "SELECT u FROM Usuario u WHERE MONTH(u.dataNascimento) = :mes", Usuario.class);
        query.setParameter("mes", mes).getResultList();
        return query.getResultList();
    }

    public List<String> listarProvedores() {
        return getEntityManager().createQuery(
                "SELECT DISTINCT SUBSTRING(email, LOCATE('@', email) +1) FROM Usuario", String.class
        ).getResultList();
    }

    public List<Usuario> listarUsuariosPorInicial(@QueryParam("inicial") String inicial) {
        TypedQuery<Usuario> query = getEntityManager().createQuery(
                "SELECT u FROM Usuario u WHERE u.nome LIKE :inicial", Usuario.class);
        query.setParameter("inicial", inicial + "%");
        return query.getResultList();
    }
}
