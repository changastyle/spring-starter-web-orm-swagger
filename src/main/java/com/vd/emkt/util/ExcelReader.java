package com.vd.emkt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class ExcelReader
{
//    public static int dameFilasDelExcel(String ruta , int hojaSeleccionada)
//    {
//        int cantidadFilas = 0;
//        try
//        {
//            // 1 - LEO EL ARCHIVO:
////            System.out.println("COMPROBANDO FILAS DE: " + ruta);
//            File archivo = new File(ruta);
//
//            // 2 - CREO UN OBJETO POI QUE LEE EXCEL:
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//            // 4 - CREO UNA HOJA POI Y LE DIGO QUE VOY A LEER LA HOJA TAL:
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//
//            // 5 - LA HOJA POI ME DA LAS FILAS:
//            int filas = sheet.getPhysicalNumberOfRows();
//
//            cantidadFilas = filas;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return cantidadFilas;
//    }
//    public static int dameColumnasDelExcel(String ruta , int hojaSeleccionada)
//    {
//        int cantidadDeColumnas = 0;
//        try
//        {
//            // 1 - LEO EL ARCHIVO:
////            System.out.println("COMPROBANDO COLUMNAS DE: " + ruta);
//            File archivo = new File(ruta);
//
//            // 2 - CREO UN OBJETO POI QUE LEE EXCEL:
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//            // 3 - CREO UNA HOJA POI Y LE DIGO QUE VOY A LEER LA HOJA TAL:
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//            HSSFRow row;
//            HSSFCell celda;
//
//            // 5 - INTENTO RECORRER LA HOJA POI:
//            int columnas = 0;
//            int tmp = 0;
//
//            // 6 - LA HOJA POI ME DA LAS FILAS PERO NO LAS COLUMNAS:
//            int filas = sheet.getPhysicalNumberOfRows();
//
//            // 1 - AVERIGUAR CUANTAS COLUMNAS (CUANTO ME VOY DE ANCHO):
//            for(int i = 0; i < 10 || i < filas; i++)
//            {
//                row = sheet.getRow(i);
//
//                if(row != null)
//                {
//                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//                    if(tmp > columnas)
//                    {
//                        columnas = tmp;
//                    }
//                }
//            }
//
//            cantidadDeColumnas = columnas;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return cantidadDeColumnas;
//    }
//    public static String leerXLS(String ruta, int hojaSeleccionada , int numeroFilaConHeaders)
//    {
//        String salida = "";
//        try
//        {
//            // 1 - LEO EL ARCHIVO:
////          System.out.println("COMPROBANDO FILAS DE: " + ruta);
//            File archivo = new File(ruta);
//
//            // 2 - CREO UN OBJETO POI QUE LEE EXCEL:
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//            // 3 - CREO UNA HOJA POI Y LE DIGO EN QUE HOJA QUIERO LEER:
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//            HSSFRow row;
//            HSSFCell celda;
//
//            numeroFilaConHeaders = numeroFilaConHeaders - 1;
//            int filas = ExcelReader.dameFilasDelExcel(ruta, hojaSeleccionada);
//            int columnas = ExcelReader.dameColumnasDelExcel(ruta , hojaSeleccionada);
//
//            System.out.println("FILAS:" +  filas);
//            System.out.println("COLUMNAS:" +  columnas);
//
//            // 4 - RECORRO LAS FILAS Y COLUMNAS IMPRIMIENDO DATOS:
//            for( int f = (numeroFilaConHeaders) ; f < filas ; f++)
//            {
//                if((f) != numeroFilaConHeaders)
//                {
//                    String strFila = "";
//                    for( int c = 0 ; c < columnas ; c++)
//                    {
//                        row = sheet.getRow(f);
//                        celda = row.getCell((short)c);
//
//                        if(celda != null)
//                        {
//                            int tipoCelda = celda.getCellType();
//
//
//                            String valor = "";
//                            if(tipoCelda == 0)
//                            {
//                                valor = "" + ((int) celda.getNumericCellValue());
//                            }
//                            else
//                            {
//                                valor = celda.getStringCellValue();
//                                valor = valor.trim();
//                            }
//
//                            if(valor.length() > 0)
//                            {
//                                strFila +=  valor + "|";
//                            }
//                        }
//                    }
//
//                    strFila += "\n";
//
//                    if(strFila.trim().length() > 0)
//                    {
//                        salida += strFila;
//                    }
//                }
//            }
//        }
//        catch(Exception ioe)
//        {
//            ioe.printStackTrace();
//        }
//
//        return salida;
//    }
//    public static List<String> dameArrayDeHeaders2(String ruta , int hojaSeleccionada , int numeroFilaConHeaders )
//    {
//        List<String> arrHeaders = new ArrayList<String>();
//        try
//        {
//            // 1 - LEO EL ARCHIVO:
////          System.out.println("COMPROBANDO FILAS DE: " + ruta);
//            File archivo = new File(ruta);
//
//            // 2 - CREO UN OBJETO POI QUE LEE EXCEL:
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//            // 3 - CREO UNA HOJA POI Y LE DIGO EN QUE HOJA QUIERO LEER:
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//            HSSFRow row;
//            HSSFCell celda;
//
//            numeroFilaConHeaders = numeroFilaConHeaders - 1;
//            int filas = ExcelReader.dameFilasDelExcel(ruta, hojaSeleccionada);
//            int columnas = ExcelReader.dameColumnasDelExcel(ruta , hojaSeleccionada);
//
////            System.out.println("FILAS:" +  filas);
////            System.out.println("COLUMNAS:" +  columnas);
//
//            // 4 - RECORRO LAS FILAS Y COLUMNAS IMPRIMIENDO DATOS:
//            for( int f = (numeroFilaConHeaders) ; f < filas ; f++)
//            {
//                if((f) == numeroFilaConHeaders)
//                {
//                    String strFila = "";
//                    for( int c = 0 ; c < columnas ; c++)
//                    {
//                        row = sheet.getRow(f);
//                        celda = row.getCell((short)c);
//
//                        if(celda != null)
//                        {
//                            int tipoCelda = celda.getCellType();
//
//
//                            String valor = "";
//                            if(tipoCelda == 0)
//                            {
//                                valor = "" + ((int) celda.getNumericCellValue());
//                            }
//                            else
//                            {
//                                valor = celda.getStringCellValue();
//                                valor = valor.trim();
//                            }
//
//                            if(valor.length() > 0)
//                            {
//                                arrHeaders.add(valor);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        catch(Exception ioe)
//        {
//            ioe.printStackTrace();
//        }
//
//        return arrHeaders;
//    }
//    public static List parearXLS2(String ruta, int hojaSeleccionada, int numeroFilaConHeaders, Class clase , boolean verbose)
//    {
//        List arr = new ArrayList();
//
//        String salida = null;
//        try
//        {
//            // 1 - LEO EL ARCHIVO:
//            File archivo = new File(ruta);
//
//            // 2 - CREO UN OBJETO POI QUE LEE EXCEL:
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//            // 3 - CREO UNA HOJA POI Y LE DIGO EN QUE HOJA QUIERO LEER:
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//            HSSFRow row;
//            HSSFCell celda;
//
//            List<String> arrHeaders = ExcelReader.dameArrayDeHeaders2(ruta, hojaSeleccionada , numeroFilaConHeaders);
//
//            numeroFilaConHeaders = numeroFilaConHeaders - 1;
//            int filas = ExcelReader.dameFilasDelExcel(ruta, hojaSeleccionada);
//            int columnas = ExcelReader.dameColumnasDelExcel(ruta , hojaSeleccionada);
//
//            if(verbose)
//            {
//                System.out.println("FILAS:" +  filas);
//                System.out.println("COLUMNAS:" +  columnas);
//            }
//
//            // 4 - RECORRO LAS FILAS Y COLUMNAS IMPRIMIENDO DATOS:
//            for( int f = (numeroFilaConHeaders) ; f < filas ; f++)
//            {
//                // 4.1 - CREO UNA INSTANCIA DE LA CLASE MODELO:
//                Object objetito = clase.newInstance();
//                boolean objetoNoEmpty = false;
//
//                // 5 - NO ES LA FILA DE HEADERS:
//                if((f) != numeroFilaConHeaders)
//                {
//                    // 5 - RECORRO LAS COLUMNAS:
//                    for( int c = 0 ; c < columnas ; c++)
//                    {
//                        row = sheet.getRow(f);
//                        celda = row.getCell((short)c);
//
//                        if(celda != null)
//                        {
//                            int tipoCelda = celda.getCellType();
//
//                            String valor = "";
//                            if(tipoCelda == 0)
//                            {
//                                valor = "" + ((int) celda.getNumericCellValue());
//                            }
//                            else
//                            {
//                                valor = celda.getStringCellValue();
//                            }
//
//                            valor = valor.trim();
//
//                            if(valor.trim().length() > 0)
//                            {
//                                // 6 - RECORRO LOS ATRIBUTOS DE LA CLASE:
//                                for (Field fieldLoop : clase.getDeclaredFields())
//                                {
//                                    String nombreCampo = fieldLoop.getName();
//
//                                    if(c < arrHeaders.size())
//                                    {
//                                        String header = arrHeaders.get(c);
//                                        if(nombreCampo.equalsIgnoreCase(header))
//                                        {
//                                            fieldLoop.setAccessible(true);
//                                            fieldLoop.set(objetito, valor);
//                                            objetoNoEmpty = true;
//
//                                            // MODO VERBOSO:
//                                            if(verbose)
//                                            {
//                                                System.out.println(fieldLoop.getName() + " : " + valor);
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                }
//                if(objetoNoEmpty)
//                {
//                    arr.add(objetito);
//                }
//            }
//        }
//        catch(Exception ioe)
//        {
//            ioe.printStackTrace();
//        }
////
//
//        return arr;
//    }
//
//
//    public static int dameUltimoSheet(String ruta)
//    {
//        int ultimaSheet = -1;
//        try
//        {
//            System.out.println("Leyendo " + ruta);
//            File archivo = new File(ruta);
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//            List<String> arrHeaders = new ArrayList<>();
//
//            int hojaSeleccionada = 0;
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//            HSSFRow row;
//            HSSFCell celda;
//
//            int columnas = 0;
//
//            ultimaSheet = wb.getNumberOfSheets();
////            System.out.println("NUMERO DE SHEETS: " + wb.getNumberOfSheets());
////            int filas = sheet.getPhysicalNumberOfRows();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return ultimaSheet;
//    }
//    public static String dameValorCelda(String ruta, int sheetALeer , int filaSeleccionada, int columnaSeleccionada)
//    {
//        filaSeleccionada = filaSeleccionada - 1;
//        columnaSeleccionada = columnaSeleccionada - 1;
//        String salida = null;
//        try
//        {
//            File archivo = new File(ruta);
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//
//            HSSFSheet sheet = wb.getSheetAt(sheetALeer);
//            HSSFRow row;
//            HSSFCell celda;
//
//            int columnas = 0;
//            int tmp = 0;
//
//            int filas = sheet.getPhysicalNumberOfRows();
//
//            // 1 - AVERIGUAR CUANTAS COLUMNAS (CUANTO ME VOY DE ANCHO):
//            for(int i = 0; i < 10 || i < filas; i++)
//            {
//
//                row = sheet.getRow(i);
//
//                if(row != null)
//                {
//                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//                    if(tmp > columnas)
//                    {
//                        columnas = tmp;
//                    }
//                }
//            }
//
//
//
//            // 2- RECORRO LAS FILAS Y COLUMNAS IMPRIMIENDO DATOS:
//            for( int f = 0 ; f < filas ; f++)
//            {
//                for( int c = 0 ; c < columnas ; c++)
//                {
//                    row = sheet.getRow(f);
//                    celda = row.getCell((short)c);
//
//                    if(f == filaSeleccionada && columnaSeleccionada == c)
//                    {
//                        if(celda != null)
//                        {
//                            int tipoCelda = celda.getCellType();
//                            //System.out.println("typo:" + tipoCelda);
//
//                            String valor = "";
//                            if(tipoCelda == 0)
//                            {
//                                valor = "" + ((int) celda.getNumericCellValue());
//                            }
//                            else
//                            {
//                                valor = celda.getStringCellValue();
//                            }
//                            salida = "" + valor;
//                        }
//                    }
//                }
//            }
//        }
//        catch(Exception ioe)
//        {
//            ioe.printStackTrace();
//        }
//
//        return salida;
//    }
//    public static boolean set(Object object, String fieldName, Object fieldValue)
//    {
//        Class<?> clazz = object.getClass();
//        while (clazz != null) {
//            try
//            {
//                Field field = clazz.getDeclaredField(fieldName);
//                field.setAccessible(true);
//                field.set(object, fieldValue);
//                return true;
//            }
//            catch (NoSuchFieldException e)
//            {
//                clazz = clazz.getSuperclass();
//            }
//            catch (Exception e)
//            {
//                throw new IllegalStateException(e);
//            }
//        }
//        return false;
//    }
//
//
//    public static boolean write(String ruta, String nombreSheet , List<Columna> arrColumnas)
//    {
//        boolean ok = false;
//
//        try
//        {
//            File archi = new File(ruta);
//
//            if(!archi.exists())
//            {
//                archi.createNewFile();
//            }
//
//           // Blank workbook
//            XSSFWorkbook workbook = new XSSFWorkbook();
//
//            // Create a blank sheet
//            XSSFSheet sheet = workbook.createSheet(nombreSheet);
//
//            // This data needs to be written (Object[])
//            Map<String, Object[]> data = new TreeMap<String, Object[]>();
//
//            int contador = 1;
//            if(arrColumnas != null)
//            {
//                if(arrColumnas.size() > 0)
//                {
//                    data.put("" + contador + "", new Object[]{ "ID", "NAME", "LASTNAME" });
//                }
//            }
//
//            for(Columna columnaLoop : arrColumnas)
//            {
//
//            }
//
//            data.put("1", new Object[]{ "ID", "NAME", "LASTNAME" });
//
//            data.put("2", new Object[]{ 1, "Pankaj", "Kumar" });
//            data.put("3", new Object[]{ 2, "Prakashni", "Yadav" });
//            data.put("4", new Object[]{ 3, "Ayan", "Mondal" });
//            data.put("5", new Object[]{ 4, "Virat", "kohli" });
//
//            // Iterate over data and write to sheet
//            Set<String> keyset = data.keySet();
//            int rownum = 0;
//            for (String key : keyset)
//            {
//                // this creates a new row in the sheet
//                Row row = sheet.createRow(rownum++);
//                Object[] objArr = data.get(key);
//                int cellnum = 0;
//                for (Object obj : objArr) {
//                    // this line creates a cell in the next column of that row
//                    Cell cell = row.createCell(cellnum++);
//                    if (obj instanceof String)
//                        cell.setCellValue((String)obj);
//                    else if (obj instanceof Integer)
//                        cell.setCellValue((Integer)obj);
//                }
//            }
//
//                // this Writes the workbook gfgcontribute
//                FileOutputStream out = new FileOutputStream(new File(ruta));
//                workbook.write(out);
//                out.close();
//                System.out.println("successfully written on disk.");
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return ok;
//    }

    
//    public static List parearXLS(String ruta, int numeroFilaConHeaders, Class clase) 
//    {
//        List arr = new ArrayList();
//        
//        String salida = null;
//        try 
//        {
//            System.out.println("Leyendo " + ruta);
//            File archivo = new File(ruta);
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//            
//            List<String> arrHeaders = dameArrayDeHeaders2(ruta, (numeroFilaConHeaders - 1));
//            
//            int hojaSeleccionada = 0;
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//            HSSFRow row;
//            HSSFCell celda;
//            
//            int columnas = 0; 
//            int tmp = 0;
//            
//            System.out.println("NUMERO DE SHEETS: " + wb.getNumberOfSheets());
//            int filas = sheet.getPhysicalNumberOfRows();
//            System.out.println("NUMERO DE ROWS EN SHEET(" + (hojaSeleccionada + 1) + "): " + filas);
//            
//            // 1 - AVERIGUAR CUANTAS COLUMNAS (CUANTO ME VOY DE ANCHO):
//            for(int i = 0; i < 10 || i < filas; i++) 
//            {
//                
//                row = sheet.getRow(i);
//                
//                if(row != null)
//                {
//                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//                    if(tmp > columnas)
//                    {
//                        columnas = tmp;
//                    }
//                }
//            }
//            System.out.println("NUMERO DE COLUMNAS " + columnas);
//            
//            
//            
//            // 2- RECORRO LAS FILAS Y COLUMNAS IMPRIMIENDO DATOS:
//            for( int f = (numeroFilaConHeaders+1) ; f < filas ; f++)
//            {
//                Object objetito = clase.newInstance();
//                
//                //COLUMNAS:
//                for( int c = 0 ; c < columnas ; c++)
//                {
//                    row = sheet.getRow(f);
//                    celda = row.getCell((short)c);
//                    
//                    if(celda != null)
//                    {
//                        int tipoCelda = celda.getCellType();
//
//                        String valor = "";
//                        if(tipoCelda == 0)
//                        {
//                            valor = "" + ((int) celda.getNumericCellValue());
//                        }
//                        else
//                        {
//                            valor = celda.getStringCellValue();
//                        }
//                        
//                        System.out.println("VALOR1:" + valor);
//                        
//                        //CAMPOS DE LA CLASE:
//                        for (Field fieldLoop : clase.getDeclaredFields())
//                        {
//                            String nombreCampo = fieldLoop.getName();
//                            //System.out.println("campo: "+ fieldLoop.getName());
//
//                            if(c < arrHeaders.size())
//                            {
//                                String header = arrHeaders.get(c);
//                                if(nombreCampo.equalsIgnoreCase(header))
//                                {
//                                    fieldLoop.setAccessible(true);
//                                    System.out.println("VALOR2:" + valor);
//                                    fieldLoop.set(objetito, valor);
//                                }
//                            }
//                        }
//                    }
//
//                }
//                
//                System.out.println("objetito: "+ objetito);
//                arr.add(objetito);
//            }
//            
//            
////            System.out.println("HEADERS:");
//            for(String headerLoop : arrHeaders)
//            {
////                System.out.println(headerLoop);
//            }
//        }
//        catch(Exception ioe) 
//        {
//            ioe.printStackTrace();
//        }
//        
//        return arr;
//    }
    
//    public static List<String> dameArrayDeHeaders(String ruta , int hojaSeleccionada , int numeroFilaConHeaders )
//    {
//        
//        List<String> arrHeaders = new ArrayList<>();
//        
//        try 
//        {
////            System.out.println("Leyendo " + ruta);
//            File archivo = new File(ruta);
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//
//            HSSFSheet sheet = wb.getSheetAt(hojaSeleccionada);
//            HSSFRow row;
//            HSSFCell celda;
//            
//            int columnas = 0; 
//            int tmp = 0;
//            
////            System.out.println("NUMERO DE SHEETS: " + wb.getNumberOfSheets());
//            int filas = sheet.getPhysicalNumberOfRows();
////            System.out.println("NUMERO DE ROWS EN SHEET(" + (hojaSeleccionada + 1) + "): " + filas);
//            
//            // 1 - AVERIGUAR CUANTAS COLUMNAS (CUANTO ME VOY DE ANCHO):
//            for(int i = 0; i < 10 || i < filas; i++) 
//            {
//                row = sheet.getRow(i);
//                
//                if(row != null)
//                {
//                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//                    if(tmp > columnas)
//                    {
//                        columnas = tmp;
//                    }
//                }
//            }
////            System.out.println("NUMERO DE COLUMNAS " + columnas);
//
//            // 2- RECORRO LAS FILAS Y COLUMNAS IMPRIMIENDO DATOS:
//            for( int f = (numeroFilaConHeaders) ; f < filas ; f++)
//            {
//                //System.out.print("["+ f +"]");
//                for( int c = 0 ; c < columnas ; c++)
//                {
//                    
//                    row = sheet.getRow(f);
//                    celda = row.getCell((short)c);
//                    
//                    //FILA DE HEADERS:
//                    if((f) == numeroFilaConHeaders)
//                    {
////                        System.out.println("f:"+f + "|c:" +c);
//                        if(celda != null)
//                        {
//                            
//                            int tipoCelda = celda.getCellType();
//                            //System.out.println("typo:" + tipoCelda);
//                            
//                            String valor = "";
//                            if(tipoCelda == 0)
//                            {
//                                valor = "" + ((int) celda.getNumericCellValue());
//                            }
//                            else
//                            {
//                                valor = celda.getStringCellValue();
//                            }
//                            arrHeaders.add(valor);
//                        }
//                        
////                        String valor = celda.getStringCellValue();
////                        //System.out.println("FILA HEADERS: " + f + " - " + c + " - " + celda);
////                        arrHeaders.add(valor);
////                        System.out.print(  celda + ",");
//                    }
//                }
//                ///System.out.println("");
//            }
//            
//            
////            System.out.println("HEADERS:");
////            for(String headerLoop : arrHeaders)
////            {
////                System.out.println(headerLoop);
////            }
//        }
//        catch(Exception ioe) 
//        {
//            ioe.printStackTrace();
//        }
//        
//        return arrHeaders;
//    }
    
    
//    public static int dameCantidadFilasDeSheet(String ruta , int sheetALeer)
//    {
//        int cantidadFilas = -1;
//        try 
//        {
//            System.out.println("Leyendo " + ruta);
//            File archivo = new File(ruta);
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//            
//            List<String> arrHeaders = new ArrayList<>();
//            
//            HSSFSheet sheet = wb.getSheetAt(sheetALeer);
//            HSSFRow row;
//            HSSFCell celda;
//            
//            int columnas = 0; 
//            
//            cantidadFilas = sheet.getPhysicalNumberOfRows();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        
//        return cantidadFilas;
//    }
    
    
}
