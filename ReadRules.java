import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class ReadRules {

    /*
    *O Map transicoes será composto da seguinte forma:
    * A primeira string ira concatenar o valor do primeiro elemento com o segundo elemento das linhas do rules.afd 
    * correspondetes as transições de estado (A partir da linha 3 do rules.afd)
    * A Segunda String corresponde ao proximo estado
    */ 
    public Map<String, String> transicoes = new HashMap<String,String>();

    public ArrayList<String> linhas = new ArrayList<>(); //Array para guardar os valores de cada linha

    public ArrayList<String> TotEstados = new ArrayList<>();

    //public String FinalState = ""; //Ira receber o valor da segunda linha do rules.afd logo este deverá ser o estado final
    public ArrayList<String> finalStates = new ArrayList<>();

    public void lerRules(){

        String fName = "Rules.afd"; // nome do arquivo de entrada
        
        String currDir = Paths.get("").toAbsolutePath().toString(); // capturar o diretório corrente

        String nameComplete = currDir+"\\"+fName;  // juntar diretorio + nome do arquivo

        Path path = Paths.get(nameComplete);

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
           while (sc.hasNext()){
            linhas.add(sc.nextLine());
                
           }
        }catch (IOException x){
            System.err.format("Erro de E/S: %s%n", x);
        }

        //ESTADOS
        String totEstadosAux[] = linhas.get(0).split(" "); //totEstadosAux[] ira separar os caracteres de linha 1-rules.afd

        String finalStatesAux[] = linhas.get(1).split(" ");

        for(int i=0; i<totEstadosAux.length; i++){ //Em seguida ira adicionar em um Array por ser dinamico
        TotEstados.add(totEstadosAux[i]);
        }

         for(int j=0; j<finalStatesAux.length; j++){ //Em seguida ira adicionar em um Array por ser dinamico
            finalStates.add(finalStatesAux[j]);
        }

        if(linhas.size()<2){
            throw new IndexOutOfBoundsException(fName + ": Problema na entrada do arquivo. Arquivo com numero de linhas menor que o minimo");
        }
        for(int i = 2; i<linhas.size(); i++){
            String dados[] = linhas.get(i).split(" ");

            transicoes.put(dados[0].concat(dados[1]), dados[2]);

        }
               
    }

   



    


    
}
