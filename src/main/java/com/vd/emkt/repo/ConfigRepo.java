package com.vd.emkt.repo;

import com.vd.emkt.modelo.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepo extends JpaRepository<Configuracion, Integer>
{
}
