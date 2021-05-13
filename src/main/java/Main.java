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
        System.out.println(findFile(directoryToSearch, searchFile, minSize, maxSize, regEx));
    }

    public static List<Path> findFile(String directory, String name, long minSize, long maxSize, String regEx) throws IOException {
        try (Stream<Path> files = Files.walk(Paths.get(directory))) {
            return files
                    .filter(p -> p.getFileName().toString().equals(name))
                    .filter(p -> {
                        try {
                            return Files.size(p) < maxSize && Files.size(p) > minSize;
                        } catch (IOException e) {
                            e.getMessage();
                        }
                        return false;
                    })
                    .filter(p -> p.toFile().getName().matches(regEx))
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toList());
        }
    }
    public class ReadFileLineByLine {
        String keyLine = "Our World is so big!";
        public void main(String[] args) {
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
                    if (line == keyLine){
                        System.out.println("here it is" + line);
                    }
                    else System.out.println("nothing here");;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}