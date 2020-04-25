/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorionivebayes;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import java.util.Arrays;
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
        Scanner scanner = new Scanner(System.in);
        Classifier<String, String> bayes = new BayesClassifier<String, String>();
        
        System.out.println("Vamos a entrenar el sistema");
        
        String salir = "NO";
        
        while(!salir.toUpperCase().equals("SI"))
        {
            System.out.println("Ingrese una frase");
            String[] frase = scanner.nextLine().split("\\s");

            System.out.println("Ingrese la etiqueta de la frase ingresada");
            String etiqueta = scanner.nextLine();       
            
            bayes.learn(etiqueta, Arrays.asList(frase));      
            
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
            String[] frase = scanner.nextLine().split("\\s");
                      
            //Etiqueta
            System.out.println(bayes.classify(Arrays.asList(frase)).getCategory());
            //Detalles
            System.out.println(((BayesClassifier<String, String>) bayes).classifyDetailed(
            Arrays.asList(frase)));
            
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
    
}
