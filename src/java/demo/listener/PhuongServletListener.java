/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class PhuongServletListener implements ServletContextListener {
    
    private static void setup(ServletContextEvent sce)
    throws FileNotFoundException, IOException{
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        Map<String, String> map = null;
        FileReader file = null;
        BufferedReader rd = null;
        try {
            file = new FileReader(realPath + "/WEB-INF/PhuongServletListener.txt");
            rd = new BufferedReader(file);
            String line = "";
            while ((line = rd.readLine()) != null){
                if (line.contains("=")){
                    String[] array = line.split("=");
                    
                    if (map == null){
                        map = new HashMap<>();
                    }
                    map.put(array[0], array[1]);
                }
            }
            context.setAttribute("MAP", map);
        } finally {
            if (rd != null){
                rd.close();
            }
            
            if (file != null){
                file.close();
            }
        }
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try{
            setup(sce);
        } catch(IOException ex){
            context.log("PhuongServletListener _IOException" + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
