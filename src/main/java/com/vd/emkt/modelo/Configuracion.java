package com.vd.emkt.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import javax.persistence.*;

@Entity @Table( name = "configuraciones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion implements Comparable<Configuracion>
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private boolean enabled;
    private String protocolo;
    private String ip;
    private String puerto;
    private String nombreProyecto;
    private String subCarpetaImagenes;
    private String varSessionHTTP;
    @Transient
    private String urlVisualizacion;
    @Transient
    private String rutaFileSystem;

    //</editor-fold>
    @Override
    public int compareTo(Configuracion o)
    {
        if(this.enabled)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }


    // DYN:
    public String getUrlVisualizacionBackground()
    {
        String urlCompleta = protocolo + "" + ip + "" + puerto + "/upload/" + nombreProyecto + "/backgrounds" ;
        return urlCompleta;
    }
    public String getUrlVisualizacionIconos()
    {
        String urlCompleta = protocolo + "" + ip + "" + puerto + "/upload/" + nombreProyecto + "/";
        return urlCompleta;
    }
    public String getUrlVisualizacion2()
    {
        String urlCompleta = ip + "" + puerto + "/upload/" + nombreProyecto + "/";
        return urlCompleta;
    }
    public String getUrlVisualizacion()
    {
        String urlCompleta = protocolo + "" + ip + "" + puerto + "/upload/" + nombreProyecto + "/";
        return urlCompleta;
    }
    public String getRutaFileSystem()
    {
        String ruta = "";
        String os = System.getProperty("os.name");
        
        if(os.startsWith("Windows"))
        {
            //WINDOWS:
            ruta = "C:\\xampp\\htdocs\\upload\\" + nombreProyecto + File.separator + subCarpetaImagenes;
        }
        else if(os.startsWith("Mac OS X"))
        {
            ruta = "/Applications/XAMPP/xamppfiles/htdocs/upload/" + nombreProyecto + File.separator + subCarpetaImagenes;
        }
        else
        {
            //LINUX:
            ruta = "/var/www/upload/" + nombreProyecto + File.separator + subCarpetaImagenes;
        }
        
        return ruta;
    }
}
