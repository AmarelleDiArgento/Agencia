/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;

/**
 *
 * @author Amarelle
 */
public class AdminFile {

    public AdminFile() {
    }
    Mensajes m = new Mensajes();
    String SO = System.getProperty("os.name");
    InputStream in = null;
    FileOutputStream out = null;
    File arch = null;
    int pack;

    public void capetas() {
        File folder = null;
        SO = SO.substring(0, 4);
        if (SO.equals("Windo")) {
            folder = new File("x:\\devtroce\\java");
        } else if (SO.equals("Linux")) {
            folder = new File("/media/devtroce/java");
        }
        ;
    }

    public Mensajes subirImg(Part img, String url, String file) {

        url = "/home/freyd/NetBeansProjects/LotusProyect/web/" + url;

        File folder = new File(url);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        url = url + "/" + file;

        try {
            in = img.getInputStream();
            arch = new File(url);
            out = new FileOutputStream(arch);
            pack = in.read();
            while (pack != -1) {
                out.write(pack);
                pack = in.read();
                System.err.println(pack);
            }
            out.close();
            in.close();
        } catch (IOException ex) {
            m.setTipo("Error");
            m.setMsj("IO Exception");
            m.setDetalles(ex.getMessage());
        }

        m.setTipo(
                "Ok");
        m.setMsj(img.getName());
        m.setDetalles(
                "Subido exitosamente");
        return m;
    }

    public void LinuxArch() {

    }

//SO  = SO.substring(0, 3);
}
