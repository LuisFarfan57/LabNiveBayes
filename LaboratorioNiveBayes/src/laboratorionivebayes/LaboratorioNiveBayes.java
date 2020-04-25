/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorionivebayes;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author luise
 */
public class LaboratorioNiveBayes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] valores = {"aquellas","aquellos","aquella","aquel","esas","esos","ese","esa","estas","estos","esta","este","lo","del","al","unas","unos","una","un","tal","según","ante","pues","porque","que","así","en","sin","a","de","es","pero","hasta","incluso","encima","igualmente","asimismo","también","la", "el", "de", "los", "las" , "por", "mientras", "para", "con", "es", "ademas", "y", "sobre", "cabe", "destacar", "modo" , "mismo"};
        Scanner scanner = new Scanner(System.in);
        Classifier<String, String> bayes = new BayesClassifier<String, String>();
        
        System.out.println("Vamos a entrenar el sistema");
        
        String salir = "NO";
        
        while(!salir.toUpperCase().equals("SI"))
        {
            System.out.println("Ingrese una frase");
            String[] frase = scanner.nextLine().toLowerCase().split("\\s");
            String[] frasesinConectores = saltarConectores(valores,frase);
            System.out.println("Ingrese la etiqueta de la frase ingresada");
            String etiqueta = scanner.nextLine();       
            
            bayes.learn(etiqueta, Arrays.asList(frasesinConectores));      
            
            String respuesta = "x";
            
            while(!respuesta.toUpperCase().equals("SI") && !respuesta.toUpperCase().equals("NO"))
            {
                System.out.println("¿Terminó de entrenar al bot? (Ingrese Si o no)");
                respuesta = scanner.nextLine();
                
                if (!respuesta.toUpperCase().equals("SI") && !respuesta.toUpperCase().equals("NO")) {
                    System.out.println("Entrada incorrecta");                    
                }   
                else {
                    salir = respuesta.toUpperCase();
                }
            }
            
        }   
        
        System.out.println("Bot entrenado, ingrese frases para clasificarlas");
        
        salir = "SI";
        
        while(!salir.toUpperCase().equals("NO"))
        {
            System.out.println("Ingrese una frase");
            String[] frase = scanner.nextLine().toLowerCase().split("\\s");
            String[] frasesinConectores = saltarConectores(valores,frase);
                      
            //Etiqueta
            System.out.println(bayes.classify(Arrays.asList(frasesinConectores)).getCategory());
            //Detalles
            System.out.println(((BayesClassifier<String, String>) bayes).classifyDetailed(
            Arrays.asList(frasesinConectores)));
            
            String respuesta = "x";
            
            while(!respuesta.toUpperCase().equals("SI") && !respuesta.toUpperCase().equals("NO"))
            {
                System.out.println("¿Desea clasificar otra frase? (Ingrese Si o no)");
                respuesta = scanner.nextLine();
                
                if (!respuesta.toUpperCase().equals("SI") && !respuesta.toUpperCase().equals("NO")) {
                    System.out.println("Entrada incorrecta");                    
                }   
                else {
                    salir = respuesta.toUpperCase();
                }
            }            
        }                                          

        // Change the memory capacity. New learned classifications (using
        // the learn method) are stored in a queue with the size given
        // here and used to classify unknown sentences.
        //bayes.setMemoryCapacity(500);
    }
    
    public static String[] saltarConectores(String[] saltar, String[] valores){
        List<String> saltarList = new ArrayList(Arrays.asList(saltar));
        List<String> valoresList = new ArrayList(Arrays.asList(valores));
        for(int i = 0; i < valoresList.size(); i++){
            String valor = valoresList.get(i);
            if(saltarList.contains(valor)){
                valoresList.remove(i);
            }
        }
        return valoresList.toArray(new String[0]);
    }
    
}
