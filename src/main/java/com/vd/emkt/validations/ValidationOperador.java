package com.vd.emkt.validations;

public class ValidationOperador
{
    public static boolean validarEmail(String email)
    {
        boolean ok = true;

        if(email != null)
        {
            if(email.length() > 0)
            {
                if(!email.equalsIgnoreCase("string") && !email.equalsIgnoreCase(""))
                {
                    ok = true;
                }
            }
        }
        return ok;
    }
    public static boolean validarPass(String pass)
    {
        boolean ok = true;

        if(pass != null)
        {
            if(pass.length() > 8)
            {
                ok = true;
            }
        }
        return ok;
    }
}
