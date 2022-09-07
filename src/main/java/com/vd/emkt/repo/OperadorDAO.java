package com.vd.emkt.repo;

import com.vd.emkt.modelo.Operador;
import com.vd.emkt.util.dao.DAOEclipse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorDAO
{
    @Autowired
    OperadorRepo operadorRepo;

    public List<Operador> findActives()
    {
        String jpql = "SELECT o FROM Operador o WHERE o.active = TRUE";

        List<Operador> arr = DAOEclipse.findAllByJPQL(jpql);

        return arr;
    }
    public Operador dameOperadorByID(int id)
    {
        Operador operadorDB = operadorRepo.getById(id);

        if(operadorDB == null)
        {
            operadorDB = empty();
        }

        return operadorDB;
    }
    public Operador checkEmailAndPass(String email, String password)
    {
//        String jpql = "SELECT o FROM Operador o WHERE o.email = '" + email +"' AND o.password = '" + password + "'";
//
//        Object obj = DAOEclipse.getByJPQL(jpql);
//        Operador operadorDB = (Operador) obj;

        Operador operadorDB  = operadorRepo.getOperadorByEmailAndPassword(email,password);

        return operadorDB;
    }
    public Operador save(Operador operador)
    {
        return operadorRepo.save(operador);
    }

    public static Operador empty()
    {
        Operador operadorEmpty = Operador.builder()
                .id(-1)
                .nombre("none")
                .apellido("none")
                .dni("none")
                .alias("none")
                .email("none")
                .emailsCopia("none")
                .img("none")
                .htmlFirma("none")
                .build();

        return operadorEmpty;
    }
}
