import java.io.IOException;
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
}