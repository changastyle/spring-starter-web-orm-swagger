package com.vd.emkt.controllers;

import com.google.gson.Gson;
import com.vd.emkt.Emkt3;
import com.vd.emkt.modelo.Configuracion;
import com.vd.emkt.modelo.Operador;
import com.vd.emkt.repo.OperadorDAO;
import com.vd.emkt.util.dao.DAOEclipse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;


@Component
public class MasterController
{
    @Autowired
    OperadorDAO operadorDAO;

    public static long timestampUltimaActualizacionConfiguracion = -1; 
//    private static Configuracion masterConfig;
//    public static String subCarpetaAdjuntos = "/";

//    public static String urlInstalacion = "F:\\despachador-mono\\";
    
    
//    public static Configuracion dameConfigMaster()
//    {
//        Date ahora = new Date();
//        long timestampActual = ahora.getTime();
//
////        if(timestampUltimaActualizacionConfiguracion == -1)
////        {
////            masterConfig = wsConfiguraciones.dameConfigActual();
////            timestampUltimaActualizacionConfiguracion = timestampActual;
////        }
////        if(timestampUltimaActualizacionConfiguracion == -1 || timestampActual > (timestampUltimaActualizacionConfiguracion + ( 3600 * 1 * 1000)) )
////        {
////            masterConfig = wsConfiguraciones.dameConfigActual();
////            timestampUltimaActualizacionConfiguracion = timestampActual;
////        }
//
//        return masterConfig;
//    }
    private static boolean soyWindows()
    {
        boolean soyWindows = false;
        String os = System.getProperty("os.name");
        System.out.println("os:" + System.getProperty("os.name"));
        
        // SISTEMA OPERATIVO (SO)
        if(os.startsWith("Windows"))
        {
            soyWindows = true;
        }
        return soyWindows;
    }
    /*
    public static String dameUrlArchivoHtmlSegunSO()
    {
        String url = "";
        
        if(soyWindows())
        {
            url = urlArchivoHtmlEnWindows;
        }
        else
        {
            url = urlArchivoHtmlEnLinux;
        }
        
        return url;
    }*/
    
     public static String formatearFechaAAlgoBonito(Date fecha, boolean ponerHoras)
    {
        Date hoy = new Date();

        String fechaFormateada = "";

        int dia = fecha.getDate();
        int mes = fecha.getMonth() + 1 ;
        int year = fecha.getYear() + 1900;
        int hora = fecha.getHours();
        int minutos = fecha.getMinutes();

        String strDia;
        String strDiaSemana = "";
        String strMes;
        String strYear;
        String strH;
        String strM;


        //DIA:
        if(dia < 10)
        {
            strDia = "0" + dia;
        }
        else
        {
            strDia = "" + dia;
        }

        //MES:
        if(mes < 10)
        {
            strMes = "0" + mes;
        }
        else
        {
            strMes = "" + mes;
        }

        //YEAR:
        strYear = "" + year;

        //HORA:
        if(hora < 10)
        {
            strH = "0" + hora;
        }
        else
        {
            strH = "" + hora;
        }

        //MINUTOS:
        if(minutos < 10)
        {
            strM = "0" + minutos;
        }
        else
        {
            strM = "" + minutos;
        }
       
        strDiaSemana = resuelveDiaDeLaSemana(fecha.getDay());

        
        if(dia == hoy.getDate() && (mes == hoy.getMonth() + 1))
        {
            if(ponerHoras)
            {
                fechaFormateada = strH + ":" + strM + " hs";
            }
            else
            {
                fechaFormateada = "Hoy";
            }
        }
        else if((mes == hoy.getMonth() + 1))
        {
            if(ponerHoras)
            {
                fechaFormateada = strDiaSemana + " " +  strDia + " (" + strH + ":" + strM + " hs)";
            }
            else
            {
                fechaFormateada = strDiaSemana + " " +  strDia;
            }
        }
        else
        {
            if(ponerHoras)
            {
                fechaFormateada =  strDia + "/" + strMes + "/" + strYear + " (" + strH + ":" + strM +" hs)";
            }
            else
            {
                fechaFormateada =  strDia + "/" + strMes + "/" + strYear;
            }
        }


        return  fechaFormateada;
    }
     public static String formatearFechaAAlgoBonito2(Date fecha, boolean ponerHoras)
    {
        Date hoy = new Date();

        String fechaFormateada = "";

        int dia = fecha.getDate();
        int mes = fecha.getMonth() + 1 ;
        int year = fecha.getYear() + 1900;
        int hora = fecha.getHours();
        int minutos = fecha.getMinutes();

        String strDia;
        String strDiaSemana = "";
        String strMes;
        String strYear;
        String strH;
        String strM;


        //DIA:
        if(dia < 10)
        {
            strDia = "0" + dia;
        }
        else
        {
            strDia = "" + dia;
        }

        //MES:
        if(mes < 10)
        {
            strMes = "0" + mes;
        }
        else
        {
            strMes = "" + mes;
        }

        //YEAR:
        strYear = "" + year;

        //HORA:
        if(hora < 10)
        {
            strH = "0" + hora;
        }
        else
        {
            strH = "" + hora;
        }

        //MINUTOS:
        if(minutos < 10)
        {
            strM = "0" + minutos;
        }
        else
        {
            strM = "" + minutos;
        }
       
        strDiaSemana = resuelveDiaDeLaSemana(fecha.getDay());

        
        fechaFormateada = strDiaSemana + " " + strDia + "/" + strMes + "/" + strYear + " (" + strH+ ":" + strM +")";


        return  fechaFormateada;
    }
    public static String resuelveDiaDeLaSemana(int dia)
    {
        String strDiaSemana = "";
        switch (dia)
        {
            case 0: strDiaSemana = "Dom"; break;
            case 1: strDiaSemana = "Lun"; break;
            case 2: strDiaSemana = "Mar"; break;
            case 3: strDiaSemana = "Mie"; break;
            case 4: strDiaSemana = "Jue"; break;
            case 5: strDiaSemana = "Vie"; break;
            case 6: strDiaSemana = "Sab"; break;

        }
        return strDiaSemana;
    }
    public static String resuelveStrMes(int mes)
    {
        String strMes = "";
        switch (mes)
        {
            case 1: strMes = "Enero"; break;
            case 2: strMes = "Febrero"; break;
            case 3: strMes = "Marzo"; break;
            case 4: strMes = "Abril"; break;
            case 5: strMes = "Mayo"; break;
            case 6: strMes = "Junio"; break;
            case 7: strMes = "Julio"; break;
            case 8: strMes = "Agosto"; break;
            case 9: strMes = "Septiembre"; break;
            case 10: strMes = "Octubre"; break;
            case 11: strMes = "Noviembre"; break;
            case 12: strMes = "Diciembre"; break;

        }
        return strMes;
    }
    public static String calcularTiempoTranscurrido(Date timestampACalcular)
    {
        long inicioCalculo = System.currentTimeMillis();
        String tiempoTranscurrido = "0 seg";
        
        Date ahoraRightNow = new Date();
        
        long segundo = 1000;
        long minuto = 60 * segundo;
        long hora = 60 * minuto;
        long dia = 24 * hora;
        long semana = 7 * dia;
        long mes = 30 * dia;
        long year = 12 * mes;
        
        int contadorYear = 0;
        int contadorMes = 0;
        int contadorDia = 0;
        int contadorSemana = 0;
        int contadorHora = 0;
        int contadorMinuto = 0;
        int contadorSegundo = 0;
        
        if(timestampACalcular != null)
        {
            long diferencia =  (long) (ahoraRightNow.getTime() - timestampACalcular.getTime());
            if(diferencia > 0)
            {
                if(diferencia > year)
                {
                    contadorYear = Math.round(diferencia / year);
                }
                else
                {
                    //MES
                    if(diferencia > mes)
                    {
                        contadorMes = Math.round(diferencia / mes);
                    }
                    else
                    {
                        //SEMANA:
                        if(diferencia > semana)
                        {
                            contadorSemana = Math.round(diferencia / semana);
                        }
                        else
                        {
                            //DIA:
                            if(diferencia > dia)
                            {
                                contadorDia = Math.round(diferencia / dia);
                            }
                            else
                            {
                                //HORA:
                                if(diferencia > hora)
                                {
                                    contadorHora = Math.round(diferencia / hora);
                                }
                                else
                                {
                                    //MINUTO:
                                    if(diferencia > minuto)
                                    {
                                        contadorMinuto = Math.round(diferencia / minuto);
                                    }
                                    else
                                    {
                                        //SEGUNDO:
                                        if(diferencia > segundo)
                                        {
                                            contadorSegundo = Math.round(diferencia / segundo);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(contadorYear  != 0 )
            {
                if(contadorYear > 1)
                {
                    tiempoTranscurrido = contadorYear + " años";
                }
                else
                {
                    tiempoTranscurrido = contadorYear + " año";
                }
            }
            if(contadorMes  != 0 )
            {
                if(contadorMes > 1)
                {
                    tiempoTranscurrido = contadorMes + " meses";
                }
                else
                {
                    tiempoTranscurrido = contadorMes + " mes";
                }
            }
            if(contadorSemana  != 0 )
            {
                if(contadorSemana > 1)
                {
                    tiempoTranscurrido = contadorSemana + " sem";
                }
                else
                {
                    tiempoTranscurrido = contadorSemana + " semana";
                }
            }
            if(contadorDia  != 0 )
            {
                if(contadorDia > 1)
                {
                    tiempoTranscurrido = contadorDia + " dias";
                }
                else
                {
                    tiempoTranscurrido = contadorDia + " dia";
                }
            }
            if(contadorHora  != 0 )
            {
                if(contadorHora > 1)
                {
                    tiempoTranscurrido = contadorHora + " horas";
                }
                else
                {
                    tiempoTranscurrido = contadorHora + " hora";
                }
            }
            if(contadorMinuto  != 0 )
            {
                if(contadorMinuto > 1)
                {
                    tiempoTranscurrido = contadorMinuto + " min";
                }
                else
                {
                    tiempoTranscurrido = contadorMinuto + " min";
                }
            }
            if(contadorSegundo  != 0 )
            {
                if(contadorSegundo > 1)
                {
                    tiempoTranscurrido = contadorSegundo + " seg";
                }
                else
                {
                    tiempoTranscurrido = contadorSegundo + " seg";
                }
            }
            long finCalculo = System.currentTimeMillis();
            //System.out.println("tarde "+ ((finCalculo - inicioCalculo)) + " ms en calcular la distancia entre " + timestampACalcular.getTime() + " y " + ahoraRightNow.getTime() + " = " + tiempoTranscurrido);
        }
        
        return tiempoTranscurrido;
    }
    
    public static int dameUltimoDigitoCuit(String cuit)
    {
        int ultimoDigito = -1; 
        
        if(cuit != null)
        {
            if(cuit.length() > 0)
            {
                char ultimoCaracter = cuit.charAt( (cuit.length() - 1) );
                ultimoDigito = Integer.parseInt("" + ultimoCaracter);
            }
        }
        
        return ultimoDigito;
    }
    
    
    
    
    
    
    
    
    //LETRAS:
    public static String pasarTodoMayus(String entrada)
    {
        return entrada.toUpperCase();
    }
    public static String primeraMayus(String entrada)
    {
        String salida = "";
        String strPrimerChar = "";
        String restoDeLaCadena = "";
        
        if(entrada.length() > 0)
        {
            char primerChar = entrada.charAt(0);
            strPrimerChar = "" + primerChar;
            strPrimerChar = strPrimerChar.toUpperCase();
            restoDeLaCadena = entrada.substring(1,entrada.length());
        }
        
        salida = strPrimerChar + restoDeLaCadena;
        
        return salida;
    }
    public static String agregarZerosSiTieneLargoMenorA(String str , int cantidadDigitos)
    {
        String zeros = "000000000000000000000000000000000000000000000000000000000000000";
        if(str != null)
        {
            int largo = str.length();
            
            if(largo < cantidadDigitos)
            {
                int cantidadZerosAAgregar = cantidadDigitos - largo ;
                
                str = zeros.substring(0,cantidadZerosAAgregar) + str;
            }
        }
        
        return str;
    }
    public static String dameStringEntreCaracteres(String caracter1 , String str , String caracter2)
    {
        String rta = str;
        
        if(str != null)
        {
            if(str.contains(caracter1) && str.contains(caracter2))
            {
                int pos1 = str.indexOf(caracter1) + 1;
                int pos2 = str.lastIndexOf(caracter2);

                if(pos1 < pos2)
                {
                    rta = str.substring(pos1 , pos2);
                }
                else
                {
                    rta = str.substring(pos2 , pos1);
                }
            }
        }
        
        return rta;
    }
    
    
    
    // COSAS - LOGIN:
    public static String sessionUsername = "USER_" + Emkt3.getNombreProyecto();
    public Operador getOperadorEmpty()
    {
        return operadorDAO.empty();
    }
    public static boolean sesionarUsuario(HttpServletRequest request, Operador usuario)
    {
        boolean sesionado = false;
        
        String strUsuarioJson = new Gson().toJson(usuario);
//        usuario.toJson()
        if(strUsuarioJson != null)
        {
            request.getSession().setAttribute(sessionUsername, strUsuarioJson);
            sesionado = true;
        }
        
        return sesionado;
    }
    public Operador dameUsuarioLogeadoFromDB(HttpServletRequest request)
    {
        // 1 - INICIALIZO UN USUARIO VACIO: 
        Operador operadorRTA = getOperadorEmpty();
        
        // 2 - BUSCO EN LA VARIABLE DE SESION EL ATRIBUTO USER_EMKT Y LO CONVIERTO A JSON:
        String strUsuarioLogeado  = (String) request.getSession().getAttribute(sessionUsername);

        System.out.println("USUARIO VARIABLE SESION:" + strUsuarioLogeado);

        // 3 - SI TENGO ALGO EN VARIABLE DE SESION LO TRAIGO A JAVA Y BUSCO EN DB:
        if(strUsuarioLogeado != null)
        {
            Operador operadorAUX = operadorRTA = new Gson().fromJson(strUsuarioLogeado, Operador.class);

            if(operadorAUX != null)
            {
                operadorRTA = operadorDAO.dameOperadorByID(operadorAUX.getId());
            }
        }

        // 3 - SI EL RESTAURANT ES DISTINTO DE -1 , LO BUSCO BIEN EN DB:
//        if(usuarioLogeado != null)
//        {
//            if(usuarioLogeado.getId() != -1)
//            {
//                Operador usuarioDB = (Operador) DAOEclipse.get(Operador.class, usuarioLogeado.getId());
//                usuarioLogeado = usuarioDB;
//            }
////        }

        if(operadorRTA == null)
        {
            operadorRTA = getOperadorEmpty();
        }
        
        
        return operadorRTA;
    }
    public static boolean exit(HttpServletRequest request)
    {
        boolean deslogeado = false;
        
        request.getSession().setAttribute(sessionUsername, null);

        deslogeado = true;

        return deslogeado;
    }
    
}
