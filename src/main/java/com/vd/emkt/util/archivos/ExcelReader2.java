package com.vd.emkt.util.archivos;

//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader2
{
    
//    public static List parsearXLSX(String ruta, int hojaSeleccionada, int numeroFilaConHeaders , String nombreClassForName)
//    {
//        List arr = new ArrayList();
//
//        String salida = null;
//
//        try
//        {
//            // 1 - LEO EL ARCHIVO:
//            File archivo = new File(ruta);
//
//            // 2 - TRAIGO LA CLASE A USAR:
//            Class clase = Class.forName(nombreClassForName);
//            //Class clase = Class.forName("auxiliar.ModeloContactosOutlook");
//
//            // 3 - TRAIGO EL ENCABEZADO DE HEADERS:
//            List<String> arrLineas = leerXLSXComoLineas(ruta, hojaSeleccionada);
//            List<FilaExcelNico> arrFilasNico = new ArrayList<FilaExcelNico>();
//
//            if(arrLineas != null)
//            {
//                for(int i = 0 ; i < arrLineas.size() ; i ++)
//                {
//                    String lineaLoop = arrLineas.get(i);
//                    FilaExcelNico filaLoop = new FilaExcelNico(lineaLoop);
//
//                    arrFilasNico.add(filaLoop);
//                }
//            }
//            FilaExcelNico filaHeaders = arrFilasNico.get(numeroFilaConHeaders);
//
//            int cantFilas = arrFilasNico.size();
//            int cantCols = filaHeaders.getArrCeldas().size();
//            System.out.println("------------------------------------");
//            System.out.println("PARSEANDO XLSX DEL ARCHIVO:" + ruta);
//            System.out.println("CANT FILAS:" + cantFilas);
//            System.out.println("CANT COLS:" + cantCols);
//            System.out.println("------------------------------------");
//
//            // RECORRO LAS FILAS Y COLUMNAS PARSEANDO DATOS:
//            for( FilaExcelNico filaLoop : arrFilasNico )
//            {
//                Object objetito = clase.newInstance();
//
//                //COLUMNAS:
//                int col = 0;
//                for(String valorLoop : filaLoop.getArrCeldas())
//                {
//                    if(valorLoop != null)
//                    {
//                        //CAMPOS DE LA CLASE:
//                        for (Field fieldLoop : clase.getDeclaredFields())
//                        {
//                            String nombreCampoClase = fieldLoop.getName().toLowerCase();
////                            System.out.println("campo: "+ fieldLoop.getName());
//
//                            if(nombreCampoClase.equalsIgnoreCase("id"))
//                            {
//                                fieldLoop.setAccessible(true);
//
//                                fieldLoop.setInt(objetito, Integer.parseInt(valorLoop));
//                            }
//                            else
//                            {
//                                String header = filaHeaders.getArrCeldas().get(col);
//                                if(nombreCampoClase.equalsIgnoreCase(header))
//                                {
//                                    fieldLoop.setAccessible(true);
//                                    fieldLoop.set(objetito, valorLoop);
//                                }
//                            }
//                        }
//                    }
//                    col++;
//                }
////                System.out.println("objetito: "+ objetito);
//                arr.add(objetito);
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return arr;
//    }
//
//    public static FilaExcelNico dameArrayDeHeaders(String ruta ,int hojaSeleccionada, int numeroFilaConHeaders )
//    {
//        FilaExcelNico filaHeaders = null;
//
//        List<String> arrLineas = leerXLSXComoLineas(ruta , hojaSeleccionada);
//
//        if(arrLineas != null)
//        {
//            String strArrHeaders = arrLineas.get(numeroFilaConHeaders);
//
//            filaHeaders = new FilaExcelNico(strArrHeaders);
//        }
//
//        return filaHeaders;
//    }
//
//    public static List<String> leerXLSXComoLineas(String ruta , int hojaSeleccionada)
//    {
//        List<String> arrLineas = new ArrayList<String>();
//
//        try
//        {
//            // 2 - CREO UN OBJETO POI QUE LEE EXCEL:
//            System.out.println("RUTA XLSX: " + ruta);
//            Workbook workbook = new XSSFWorkbook(ruta);
//            Sheet datatypeSheet = workbook.getSheetAt(hojaSeleccionada);
//            Iterator<Row> iterator = datatypeSheet.iterator();
//
//            while (iterator.hasNext())
//            {
//                Row currentRow = iterator.next();
//                Iterator<Cell> cellIterator = currentRow.iterator();
//
//                String linea = "";
//
//                while (cellIterator.hasNext())
//                {
//                    Cell currentCell = cellIterator.next();
//                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING)
//                    {
//                        linea += currentCell.getStringCellValue() + "|";
////                        System.out.print(currentCell.getStringCellValue() + " | ");
//                    }
//                    else if (currentCell.getCellType () == Cell.CELL_TYPE_NUMERIC)
//                    {
//                        linea += currentCell.getNumericCellValue() + "|";
////                        System.out.print(currentCell.getNumericCellValue() + " | ");
//                    }
//                }
//                arrLineas.add(linea);
//                linea = "";
////                System.out.println("");
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();;
//        }
//
//        return arrLineas;
//    }
//
//    public static boolean escribirXLSX(String ruta , String nombreHoja , List<FilaExcelNico> arrFilas)
//    {
//        boolean ok = false;
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet(nombreHoja);
//
//        int rowNum = 0;
//        System.out.println("CREANDO EXCEL (XLSX) " +  ruta  +" | " + nombreHoja);
//
//
//        Font headerFont = workbook.createFont();
//        headerFont.setFontHeightInPoints((short) 14);
//        headerFont.setColor(IndexedColors.RED.getIndex());
//
//        for (FilaExcelNico filaLoop : arrFilas)
//        {
//            Row row = sheet.createRow(rowNum++);
//            int colNum = 0;
//            for (String celdaLoop : filaLoop.getArrCeldas())
//            {
//                Cell cell = row.createCell(colNum++);
//                if (celdaLoop instanceof String)
//                {
//                    cell.setCellValue((String) celdaLoop);
//                }
////                else if (field instanceof Integer)
////                {
////                    cell.setCellValue((Integer) field);
////                }
//            }
//        }
//
//        try
//        {
//            FileOutputStream outputStream = new FileOutputStream(ruta);
//            workbook.write(outputStream);
//            ok = true;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        System.out.println("Done");
//
//        return ok;
//    }
}
