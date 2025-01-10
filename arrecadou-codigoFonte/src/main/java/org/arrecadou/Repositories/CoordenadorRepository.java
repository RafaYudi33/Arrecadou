package org.arrecadou.Repositories;

import org.arrecadou.Model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {

}
