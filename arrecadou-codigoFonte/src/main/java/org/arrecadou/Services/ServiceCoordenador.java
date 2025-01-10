package org.arrecadou.Services;

import org.arrecadou.Model.Coordenador;
import org.arrecadou.Repositories.CoordenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCoordenador {
    private final CoordenadorRepository coordenadorRepository;

    public ServiceCoordenador(CoordenadorRepository coordenadorRepository) {
        this.coordenadorRepository = coordenadorRepository;
    }

    public void cadastrarCoordenador(String nome, String cpf, String telefone) {
        this.coordenadorRepository.save(new Coordenador(nome, cpf, telefone));
    }

    public List<Coordenador> listarTodosCoordenadores() {
        return this.coordenadorRepository.findAll();
    }
}
