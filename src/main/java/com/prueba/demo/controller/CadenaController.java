package com.prueba.demo.controller;

import com.prueba.demo.model.Cadena;
import com.prueba.demo.service.CadenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cadena")
@CrossOrigin
public class CadenaController {
    @Autowired
    private CadenaService service;

    @PostMapping
    public ResponseEntity<Cadena> saveCadena(@RequestBody Cadena cadena){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCadena(cadena));
    }
    @GetMapping
    public ResponseEntity<Page<Cadena>> getAllCadenas(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10")Integer size,
            @RequestParam(required = false, defaultValue = "false")Boolean enablePagination){
        return ResponseEntity.ok(service.getAllCadenas(page,size,enablePagination));
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity deleteCadena(@PathVariable ("id") Long id){
        service.deleteCadena(id);
        ResponseEntity.ok(service.existById(id));
        return ResponseEntity.ok(service.existById(id));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<Cadena>> findById(@PathVariable ("id") Long id){
        service.findById(id);
        ResponseEntity.ok(!service.existById(id));
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping
    public ResponseEntity<Cadena> editCadena(@RequestBody Cadena cadena){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.editCadena(cadena));
    }


}
