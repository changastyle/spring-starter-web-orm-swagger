package com.vd.emkt.repo;

import com.vd.emkt.modelo.Configuracion;
import com.vd.emkt.util.dao.DAOEclipse;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.Configuration;
import java.util.List;

@Service @Builder
public class ConfigDAO
{
    @Autowired
    ConfigRepo configRepo;

    public static Configuracion dameConfigActiva()
    {
        return findAll().stream().findFirst().get();
    }
    public static List<Configuracion> findAll()
    {
        String jpql = "SELECT c FROM Configuracion c";
        return DAOEclipse.findAllByJPQL(jpql);
    }
}
