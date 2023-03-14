package etu1906.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import model.util.*; 

import etu1906.framework.Mapping;
import model.util.Utilitaire;
public class FrontServlet extends HttpServlet{
    Vector<Class<?>> listpackage;
    HashMap<String , Mapping> MappingUrls = new HashMap<String  , Mapping>();

    public void init() throws ServletException {
        try{
            MyPackage p=new MyPackage("model");
            this.listpackage =  p.getClasses( null  , "model" );
            this.MappingUrls = Utilitaire.getAllMethod(listpackage, MappingUrls ) ; 
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    public Mapping getMethod( String url )throws Exception{
        String method = "";
        Mapping map = new Mapping();
        for (Class<?> clazz : listpackage) {
            method = Utilitaire.getMethod(clazz, url);
            if( method != null ){
                map.setClassName(clazz.getSimpleName());
                map.setMethod(method);
            }
        }
        return map;
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        try{
            PrintWriter out = res.getWriter();
            // for( Map.Entry<String, Mapping>  entry : MappingUrls.entrySet()){
            //     out.println( entry.getKey() );
            //     out.println( (entry.getValue()).getMethod() );
            // }
            // String context = req.getServletContext().getRealPath("");
            String url = req.getRequestURL().toString();  
            out.println(url);


            String value = Utilitaire.getUrl( url );
            System.out.println( MappingUrls.get(value) );
            if( MappingUrls.get(value) != null){
                out.print( MappingUrls.get(value).getMethod() );
            } 
        }catch( Exception e ){
            e.printStackTrace();
        } 
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        processRequest(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        processRequest(req, res);
    }
}
