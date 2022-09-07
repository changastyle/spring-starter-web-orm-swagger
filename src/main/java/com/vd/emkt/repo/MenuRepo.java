package com.vd.emkt.repo;

import com.vd.emkt.modelo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Integer>
{
    public List<Menu> findByActiveTrue();
}
