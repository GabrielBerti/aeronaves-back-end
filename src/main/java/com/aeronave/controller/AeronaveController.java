package com.aeronave.controller;

import com.aeronave.domain.Aeronave;
import com.aeronave.repository.AeronaveCustomRepository;
import com.aeronave.service.AeronaveService;
import com.aeronave.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("aeronaves")
@Log4j2
@RequiredArgsConstructor
public class AeronaveController {
    private final DateUtil dateUtil;
    private final AeronaveService aeronaveService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Page<Aeronave>> list(Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(aeronaveService.listAll(pageable));
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public ResponseEntity<Aeronave> findById(@PathVariable long id) {
        return ResponseEntity.ok(aeronaveService.findByIdOrThrowBadRequestException(id));
    }


    @CrossOrigin
    @GetMapping(path = "/find")
    public ResponseEntity<List<Aeronave>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(aeronaveService.findByNome(nome));
    }

    @CrossOrigin
    @GetMapping(path = "/lastWeek")
    public ResponseEntity<List<Aeronave>> findAeronaveLastWeek() {
        return ResponseEntity.ok(aeronaveService.findAeronaveLastWeek());
    }

    @CrossOrigin
    @GetMapping(path = "/groupByNome")
    public ResponseEntity<List<Aeronave>> findAeronaveGroupByNome() {
        return ResponseEntity.ok(aeronaveService.findAeronaveGroupByNome());
    }

    @CrossOrigin
    @GetMapping(path = "/countNotSolds")
    public ResponseEntity<List<Aeronave>> findAeronaveNotSold() {
        return ResponseEntity.ok(aeronaveService.findAeronaveNotSold());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Aeronave> save(@RequestBody @Valid Aeronave aeronave) {
        return new ResponseEntity<>(aeronaveService.save(aeronave), HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        aeronaveService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Aeronave aeronave) {
        aeronaveService.replace(aeronave);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
