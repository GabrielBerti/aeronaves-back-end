package com.aeronave.repository;

import com.aeronave.domain.Aeronave;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AeronaveCustomRepository {
    private final EntityManager em;

    public AeronaveCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Aeronave> findAeronaveLastWeek(){
        String query = "SELECT a FROM Aeronave a WHERE datediff(date(now()),created) <= 7";

        var q = em.createQuery(query, Aeronave.class);
        return q.getResultList();
    }

    public List<Aeronave> findAeronaveGroupByNome(){
        //String query = "SELECT new com.aeronave.domain.Aeronave(1, a.nome, 'x', Count(a.nome) as ano, 'x', false) " +
        String query = "SELECT new Map(a.nome as nome, Count(a.nome) as ano) " +
                         "FROM Aeronave a " +
                        "GROUP BY a.nome";

        var q = em.createQuery(query);

        return q.getResultList();
    }

    public List<Aeronave> findAeronaveNotSold(){
        //String query = "SELECT new com.aeronave.domain.Aeronave(1, a.nome, 'x', Count(a.nome) as ano, 'x', false) " +
        String query = "SELECT new Map(Count(a.vendido) as ano) " +
                         "FROM Aeronave a " +
                        "WHERE a.vendido = 0";

        var q = em.createQuery(query);

        return q.getResultList();
    }

}
