package org.arrecadou.Repositories;

import org.arrecadou.Model.AcaoContribuicaoDireta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoContribuicaoDiretaRepository extends JpaRepository<AcaoContribuicaoDireta, Long> {
}
