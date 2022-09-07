package com.vd.emkt.repo;

import com.vd.emkt.modelo.Menu;
import com.vd.emkt.util.dao.DAOEclipse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuDAO
{
    @Autowired
    MenuRepo menuRepo;

    public List<Menu> findActives()
    {
        return menuRepo.findByActiveTrue();
    }
    public Menu save(Menu menu)
    {
        return menuRepo.save(menu);
    }
}
