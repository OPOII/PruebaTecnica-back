package com.prueba.demo.service;

import com.prueba.demo.model.Cadena;
import com.prueba.demo.repository.ICadenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CadenaService {
    @Autowired
    private ICadenaRepository repository;

    public Cadena saveCadena(Cadena cadena){
        if(!cadena.getInput().isEmpty()){

            String nuevo=reverso(cadena.getInput(),cadena.getRepeats());
            cadena.setOutput(nuevo);
            return repository.save(cadena);
        }
        return null;
    }
    public Page<Cadena> getAllCadenas(Integer page, Integer size,Boolean enablePagination){
        return repository.findAll(enablePagination? PageRequest.of(page,size): Pageable.unpaged());
    }

    public void deleteCadena(Long id){
    repository.deleteById(id);
    }

    public Cadena editCadena(Cadena cadena){
        if(cadena.getId()!=null && repository.existsById(cadena.getId())){
            return repository.save(cadena);
        }
        return null;
    }
    public boolean existById(Long id){
        return repository.existsById(id);
    }
    public Cadena transformada(Cadena cadena){

        return null;
    }
    public Optional<Cadena> findById(Long id){
        return repository.findById(id);
    }

    public String reverso(String input, int num){
        List<Character> cadenaVocales = new ArrayList<>();
        char[] cadena = input.toCharArray();
        char sustituto = '?';
        for (int i = 0; i < cadena.length; i++) {
            if (cadena[i] == 'a' || cadena[i] == 'e' || cadena[i] == 'i' || cadena[i] == 'o' || cadena[i] == 'u') {
                cadenaVocales.add(input.charAt(i));
                cadena[i] = '?';

            }
        }
        char aux = ' ';
        System.out.println(cadena);
        System.out.println(cadenaVocales);
        for (int j = 0; j < num; j++) {
            for (int i = 0; i < cadenaVocales.size(); i++) {
                if (i == 0) {
                    aux = cadenaVocales.get(0);
                    cadenaVocales.set(0, cadenaVocales.get(i + 1));

                } else if ((i + 1) < cadenaVocales.size() - 1) {
                    cadenaVocales.set(i, cadenaVocales.get(i + 1));
                } else if ((i + 1) == cadenaVocales.size() - 1) {
                    cadenaVocales.set(i, cadenaVocales.get(i + 1));
                    cadenaVocales.set(i + 1, aux);
                }

            }
        }
        int pos=0;
        System.out.println(cadenaVocales);
        for (int i = 0; i < cadena.length; i++) {
            if(cadena[i]=='?') {
                cadena[i]=cadenaVocales.get(pos);
                pos++;
            }

        }
        String response=String.valueOf(cadena);
        System.out.println(response);
        return response;
    }
}
