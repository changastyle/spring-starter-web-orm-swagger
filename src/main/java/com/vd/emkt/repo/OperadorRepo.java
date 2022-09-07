package com.vd.emkt.repo;

import com.vd.emkt.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperadorRepo extends JpaRepository<Operador, Integer>
{
    public Operador getOperadorByEmailAndPassword(String email, String password);
}
