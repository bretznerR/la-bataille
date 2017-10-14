package upmc.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LectureFichier implements LecturePseudo {

    public ArrayList<String> lirePseudo() {
        ArrayList<String> pseudos = new ArrayList<>();
        String fileName = "pseudos";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            pseudos = stream
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pseudos.forEach(System.out::println);
        return pseudos;
    }
}
