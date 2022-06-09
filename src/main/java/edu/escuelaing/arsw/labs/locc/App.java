package edu.escuelaing.arsw.labs.locc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.escuelaing.arsw.labs.locc.counter.Counter;
import edu.escuelaing.arsw.labs.locc.counter.LOCCounter;
import edu.escuelaing.arsw.labs.locc.counter.PhyCounter;

/**
 * Administra la app
 * 
 */
public class App {
    public static void main(String[] args) {
        String mode, fileName;
        Counter counter;
        args = parseInputParemeters(args);
        mode = args[0];
        fileName = args[1];
        switch (mode) {
            case "phy":
                counter = new PhyCounter();
                count(fileName, counter);
                break;
            case "loc":
                counter = new LOCCounter();
                count(fileName, counter);
                break;
            default:
                System.out.println("El método ingresado no es correcto. Opciones = phy, loc.");
                break;
        }
    }

    private static void count(String fileName, Counter counter) {
        List<Path> paths = findPathsByFileName(fileName);
        for (Path path : paths) {
            try {
                int n = counter.count(Files.readAllLines(path));
                System.out.println("File : " + path + "\n" + "n = " + n + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Path> findPathsByFileName(String fileName) {
        List<Path> paths;
        Path path = Paths.get(System.getProperty("user.home"));
        try (Stream<Path> pathStream = Files.find(
                path,
                Integer.MAX_VALUE,
                (p, BasicFileAttributes) -> p.getFileName().toString().equalsIgnoreCase(fileName))) {
            paths = pathStream.collect(Collectors.toList());
            return paths;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String[] parseInputParemeters(String[] args) {
        String[] parameters = new String[2];
        switch (args.length) {
            case 0: // Neither mode nor filename
                parameters[0] = "phy";
                parameters[1] = "App.java";
                break;
            case 1: // Only mode
                parameters[0] = "phy";
                parameters[1] = args[0];
                break;
            case 2: // Mode and filename
                parameters[0] = args[0];
                parameters[1] = args[1];
                break;
            default: // Mode and filename and more(?)
                parameters[0] = args[0];
                parameters[1] = args[1];
                break;
        }
        return parameters;
    }

}
