import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        String directoryToSearch = "";
        String searchFile = "test.txt";
        long minSize = 11;
        long maxSize = 99;
        String regEx = "t.*";
        String keyLine = "Our World is so big!";
        System.out.println(findFile(directoryToSearch, searchFile, minSize, maxSize, regEx,keyLine));

    }

    public static List<Path> findFile(String directory, String name, long minSize, long maxSize, String regEx,String keyLine) throws IOException {
        try (Stream<Path> files = Files.walk(Paths.get(directory))) {
            return files
                    .filter(p -> p.getFileName().toString().equals(name) || p.toFile().getName().matches(regEx) || p.toFile().toString().equals(keyLine))
                    .filter(p -> {
                        try {
                            return Files.size(p) < maxSize && Files.size(p) > minSize;
                        } catch (IOException e) {
                            e.getMessage();
                        }
                        return false;
                    })
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toList());
        }
    }

    public static void findByKeyLine(String keyLine) {
        try {
            File file = new File("/Users/raydavis/Desktop/developmentJAVA/Modul2/freedom.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line == keyLine) {
                    System.out.println("here it is");
                } else System.out.println("nothing here");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ;
    }
    }