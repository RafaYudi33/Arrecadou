package org.arrecadou.Repositories;

import org.arrecadou.Model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
    @Query("SELECT e FROM Entidade e ORDER BY e.id ASC")
    Optional<Entidade> findFirstEntidade();
}
