/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tim-b
 */
public class Td1 {

    static String cheminFichier = "src/fr-dico.txt";
    static String fichierFeries = "src/feries.txt";
    static FileInputStream fis = null;
    
    static String regexQ1 = "t*i*.*";
    static String regexQ2 = "([^i]*i){5}";          // grep -E "([^i]*i){5}" src/fr-dico.txt
    static String regexQ3 = "^([^i]*i){5}[^i]*$";
    static String regexQ4 = "gr[^io]s";
    static String regexQ5 = "^(...).*\\1$";
    static String regexQ6 = "^([mbp]).*\\1$";
    
    static String regexOriginale = "([0-9]{4})([0-9]{2})([0-9]{2}),([0-9]{4})([0-9]{2})([0-9]{2}).*\"(.*)\"$";
    static String regexNouvelle = "du $3/$2/$1 au $6/$5/$4 $7";
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        /*grep(regexQ1, cheminFichier);
        grep(regexQ2, cheminFichier);
        grep(regexQ3, cheminFichier);
        grep(regexQ4, cheminFichier);
        grep(regexQ5, cheminFichier);*/
        grep(regexQ6, cheminFichier);
    
        substit(regexOriginale, regexNouvelle, fichierFeries);
    }
    
    public static void grep(String regex, String nomFichier) throws FileNotFoundException{
        Pattern p = Pattern.compile(regex);
        File fichier = new File(nomFichier);
        
        try{
            BufferedReader in = new BufferedReader(new FileReader(nomFichier));

            String ligne;
            while((ligne=in.readLine()) != null){
                Matcher m = p.matcher(ligne);
                if(m.find()){
                    //si ça match n'importe où dans la ligne
                    System.out.println(ligne);
                }
            }
        }catch(FileNotFoundException fnf){
        }catch(IOException ioe){
        }
        
    }
    
    public static void substit(String regex, String repl, String nomFichier){
        Pattern p = Pattern.compile(regex);
        File fichier = new File(nomFichier);

        try{
            BufferedReader in = new BufferedReader(new FileReader(nomFichier));

            String ligne;
            while((ligne=in.readLine()) != null){
                Matcher m = p.matcher(ligne);
                String ligne1 = m.replaceAll(repl);
                if(!ligne.equals(ligne1)){
                    System.out.println(ligne1);
                }
            }
        }catch(FileNotFoundException fnf){
        }catch(IOException ioe){
        }
    }
    
}
