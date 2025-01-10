package org.arrecadou.Repositories;

import org.arrecadou.Model.AcaoProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoProducaoRepository extends JpaRepository<AcaoProducao, Long> {
}
