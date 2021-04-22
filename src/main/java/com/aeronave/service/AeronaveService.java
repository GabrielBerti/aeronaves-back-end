package com.aeronave.service;

import com.aeronave.domain.Aeronave;
import com.aeronave.exception.BadRequestException;
import com.aeronave.repository.AeronaveCustomRepository;
import com.aeronave.repository.AeronaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AeronaveService {

    private final AeronaveRepository aeronaveRepository;
    private final AeronaveCustomRepository aeronaveCustomRepository;

    public Page<Aeronave> listAll(Pageable pageable) {
        return aeronaveRepository.findAll(pageable);
    }

    public List<Aeronave> listAllNonPageable() {
        return aeronaveRepository.findAll();
    }

    public List<Aeronave> findByNome(String nome) {
        return aeronaveRepository.findByNome(nome);
    }

    public List<Aeronave> findAeronaveLastWeek() {
        return aeronaveCustomRepository.findAeronaveLastWeek();
    }

    public List<Aeronave> findAeronaveGroupByNome() {
        return aeronaveCustomRepository.findAeronaveGroupByNome();
    }

    public List<Aeronave> findAeronaveNotSold() {
        return aeronaveCustomRepository.findAeronaveNotSold();
    }

    public Aeronave findByIdOrThrowBadRequestException(long id) {
        return aeronaveRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Aeronave not Found"));
    }

    @Transactional
    public Aeronave save(Aeronave aeronave) {
        return aeronaveRepository.save(aeronave);
    }

    public void delete(long id) {
        aeronaveRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(Aeronave aeronave) {
        Aeronave savedAeronave = findByIdOrThrowBadRequestException(aeronave.getId());
        aeronave.setId(savedAeronave.getId());
        aeronaveRepository.save(aeronave);
    }
}
