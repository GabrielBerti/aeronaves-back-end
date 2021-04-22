package com.aeronave.repository;

import com.aeronave.domain.Aeronave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {

    List<Aeronave> findByNome(String nome);


}
