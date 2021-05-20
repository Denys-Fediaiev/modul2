import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main {
    public static void main(String[] args) throws IOException {
        String directoryToSearch = "";
        String searchFile = "test.txt";
         String path = "/Users/raydavis/Desktop/developmentJAVA/Modul2/freedom.txt";
         long minSize = 11;
         long maxSize = 99;
         String regEx = "t.*";
         String searchString = "World";
        findFile(directoryToSearch, searchFile, minSize, maxSize, regEx, searchString);
    }
    public static void findFile(String directory, String name, long minSize, long maxSize, String regEx, String searchString) throws IOException {
        var searchLines = new ArrayList<String>();
        try (Stream<Path> files = Files.walk(Paths.get(directory))) {
            var filteredFiles  = files
                    .filter(p -> p.getFileName().toString().equals(name) || p.toFile().getName().matches(regEx))
                    .filter(p -> {
                        try {
                            return Files.size(p) < maxSize && Files.size(p) > minSize;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toList());
            for (var filteredFile : filteredFiles) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filteredFile)));
                    String line = reader.readLine();
                    if (line == null) {
                        reader.close();
                        continue;
                    }
                    if (line.contains(searchString)) {
                        searchLines.add(line);
                    }
                    reader.close();
                }catch (IOException e) {
                    e.getMessage();
                }
            }
            System.out.println(filteredFiles);
            System.out.println(searchLines);
        }
    }
}