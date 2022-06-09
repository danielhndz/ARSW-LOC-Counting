package edu.escuelaing.arsw.labs.locc.counter;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import edu.escuelaing.arsw.labs.locc.App;

/**
 * Unit test for CounterImpl.
 */
public class CounterImplTest {

    List<String> lines;
    boolean defaultData;
    int linesOfThisFile = 127; // The final line (#128) is showed by vscode but no by text editors
    int linesOfDefaultData;
    String otherFileOfThisProject = "counter.CounterImpl";
    int linesOfTheOtherFileOfThisProject = 28;

    @Test
    public void countLinesOfOtherFileOfThisProject() {
        String m;
        defaultData = true;
        lines = otherFileOfThisProjectAsData();
        if (!defaultData) {
            m = "Test de contar las líneas del archivo " + otherFileOfThisProject + ".java";
            assertTrue(m, linesOfTheOtherFileOfThisProject == new CounterImpl().count(lines));
        } else {
            m = "Test de contar las líneas con datos por defecto";
            assertTrue(m, linesOfDefaultData == new CounterImpl().count(lines));
        }
    }

    @Test
    public void countLinesOfThisFile() {
        String m;
        defaultData = true;
        lines = thisFileAsData();
        if (!defaultData) {
            m = "Test de contar las líneas del archivo " + this.getClass().getName() + ".java";
            assertTrue(m, linesOfThisFile == new CounterImpl().count(lines));
        } else {
            m = "Test de contar las líneas con datos por defecto";
            assertTrue(m, linesOfDefaultData == new CounterImpl().count(lines));
        }
    }

    private List<String> otherFileOfThisProjectAsData() {
        List<Path> paths;
        Path path = Paths.get(new StringBuilder(System.getProperty("user.dir"))
                .append(System.getProperty("file.separator"))
                .append("src")
                .append(System.getProperty("file.separator"))
                .append("main")
                .append(System.getProperty("file.separator"))
                .append("java")
                .append(System.getProperty("file.separator"))
                .append(new App().getClass().getPackageName().replace(".", System.getProperty("file.separator")))
                .append(System.getProperty("file.separator"))
                .append(otherFileOfThisProject.replace(".", System.getProperty("file.separator")))
                .append(".java")
                .toString());
        try (Stream<Path> pathStream = Files.find(
                path,
                Integer.MAX_VALUE,
                (p, BasicFileAttributes) -> p.getFileName().toString()
                        .equalsIgnoreCase(otherFileOfThisProject.substring(otherFileOfThisProject.lastIndexOf(".") + 1)
                                + ".java"))) {
            paths = pathStream.collect(Collectors.toList());
            if (paths.size() == 1) {
                defaultData = false;
                return Files.readAllLines(paths.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultData();
    }

    private List<String> thisFileAsData() {
        List<Path> paths;
        Path path = Paths.get(new StringBuilder(System.getProperty("user.dir"))
                .append(System.getProperty("file.separator"))
                .append("src")
                .append(System.getProperty("file.separator"))
                .append("test")
                .append(System.getProperty("file.separator"))
                .append("java")
                .append(System.getProperty("file.separator"))
                .append(this.getClass().getCanonicalName().replace(".", System.getProperty("file.separator")))
                .append(".java")
                .toString());
        try (Stream<Path> pathStream = Files.find(
                path,
                Integer.MAX_VALUE,
                (p, BasicFileAttributes) -> p.getFileName().toString().equalsIgnoreCase("CounterImplTest.java"))) {
            paths = pathStream.collect(Collectors.toList());
            if (paths.size() == 1) {
                defaultData = false;
                return Files.readAllLines(paths.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultData();
    }

    private List<String> defaultData() {
        List<String> list = new ArrayList<>();
        list.add("package x.x.x;");
        list.add("");
        list.add("import x.x.x;");
        list.add("");
        list.add("public class fo {}");
        linesOfDefaultData = list.size();
        return list;
    }
}
