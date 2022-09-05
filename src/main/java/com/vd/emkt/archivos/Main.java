package com.vd.emkt.archivos;

//import archivos.ExcelReader2;
//import archivos.FilaExcelNico;
//import auxiliar.ModeloContactosOutlook;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.vd.emkt.controllers.MasterController
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.Message;
//import mailsUtils.HtmlParserForMails;
//import mailsUtils.MailManager3;
//import mailsUtils.ParametroMail;
//import com.vd.emkt.modelo.Archivo;
//import com.vd.emkt.modelo.Grupo;
//import com.vd.emkt.modelo.Operador;
//import com.vd.emkt.modelo.Persona;
//import com.vd.emkt.modelo.RelPersonaGrupo;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.*;
//import util.ExcelReader;
//import static ws.wsEmail.ESTOYENVIANDOMAILS;
//import static ws.wsEmail.dameOperadorAsignado;

public class Main
{
    public static void main(String args[])
    {
//        // EJEMPLO OPERATIVO LEER XLSX E Y CONVERTIR A XLS FIXED MAS SENCILLO DE IMPORTAR:
//        System.out.println("INICIANDO");
//
//        String carpeta = "C:\\jtemp\\";
//        String nombreArchi = "fabricas";
//        String strArchivoEntrada =  nombreArchi + ".xlsx";
//        String strArchivoSalida = nombreArchi + "-fixed.xlsx";
//        String nombreSheet = nombreArchi;
//        String rutaCompletaEntrada = carpeta + "" + strArchivoEntrada;
//        String rutaCompletaSalida = carpeta + "" + strArchivoSalida;
//
//
//        List<ModeloContactosOutlook> arrParseado = ExcelReader2.parsearXLSX(rutaCompletaEntrada, 0, 0, "auxiliar.ModeloContactosOutlook");
//        List<ModeloContactosOutlook> arrFixed = new ArrayList<>();
//        List<FilaExcelNico> arrFixedXLS = new ArrayList<>();
//
//        if(arrParseado != null)
//        {
//            for(ModeloContactosOutlook modeloLoop: arrParseado)
//            {
//                String colNombre = MasterController.dameStringEntreCaracteres("-", modeloLoop.getNombre(), "-");
//                String colApellido = modeloLoop.getApellido();
//                String colTel = modeloLoop.getTel();
//                String colAlias = modeloLoop.getAlias();
//
//                if(colNombre != null && colNombre.equals("xxx"))
//                {
//                    colNombre = "";
//                    modeloLoop.setNombre(colNombre);
//                }
//                if(colApellido != null && colApellido.equals("xxx"))
//                {
//                    colApellido = "";
//                    modeloLoop.setApellido(colApellido);
//                }
//                if(colTel != null && colTel.equals("xxx"))
//                {
//                    colTel = "";
//                    modeloLoop.setTel(colTel);
//                }
//                if(colAlias != null && colAlias.equals("xxx"))
//                {
//                    colAlias = "";
//                    modeloLoop.setAlias(colAlias);
//                }
//
//                String todoJunto = colNombre + " " + colApellido;
//                int posPrimerGuion = todoJunto.indexOf("-") + 1 ;
//                int posUltimoGuion = todoJunto.lastIndexOf("-");
//
//                try
//                {
//                    String nombreParseado = MasterController.dameStringEntreCaracteres("-", colNombre, "-");
//                    String apellidoParseado = MasterController.dameStringEntreCaracteres("-", colApellido, "-");
//                    if(posPrimerGuion > 1 && posUltimoGuion > -1 && posUltimoGuion > posPrimerGuion)
//                    {
//                        nombreParseado = todoJunto.substring(posPrimerGuion , posUltimoGuion);
//                        apellidoParseado = todoJunto.substring(0, posPrimerGuion - 1) +  " " + todoJunto.substring(posUltimoGuion + 1 , todoJunto.length());
//                    }
//
//                    System.out.println("ORIGINAL:" +  todoJunto);
//                    modeloLoop.setNombre(nombreParseado);
//                    modeloLoop.setApellido(apellidoParseado);
//
//                    System.out.println(modeloLoop.toJSON());
//
////                    arrFixed.add(modeloLoop);
//                    String strFilaExcel = "" + modeloLoop.getNombre() + "|" + modeloLoop.getApellido() + "|" + modeloLoop.getEmail()+ "|" + modeloLoop.getTel() + "|" +modeloLoop.getAlias() + "|";
//                    arrFixedXLS.add(new FilaExcelNico(strFilaExcel));
//                    System.out.println("----" );
//                }
//                catch(Exception e)
//                {
//                    e.printStackTrace();
//                    System.out.println("ERROR:(" + posPrimerGuion + "," + posUltimoGuion + "|" + todoJunto.length() +"): " + todoJunto);
//                }
//            }
//        }
//
//        ExcelReader2.escribirXLSX(rutaCompletaSalida, nombreSheet, arrFixedXLS);
//    }
    }
}
