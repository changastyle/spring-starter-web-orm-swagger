package com.vd.emkt.repo;

import com.vd.emkt.modelo.AccionBarraLateral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccionRepo extends JpaRepository<AccionBarraLateral, Integer>
{
}
