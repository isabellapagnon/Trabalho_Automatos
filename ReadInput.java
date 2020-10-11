import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadInput {

    public ArrayList<String> inputLine = new ArrayList<>(); // Array para guardar os valores de cada linha

    public void lerInput() {

        String fName = "input"; // nome do arquivo de entrada

        String currDir = Paths.get("").toAbsolutePath().toString(); // capturar o diretório corrente
        String nameComplete = currDir + "\\" + fName; // juntar diretorio + nome do arquivo
        Path path = Paths.get(nameComplete);

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            while (sc.hasNext()) {

                inputLine.add(sc.nextLine());

            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

        // teste de leitura do arquivo
        for (String InputLido : inputLine) {
            System.out.println("Palavra de entrada: " + InputLido);
        }

    }

}
