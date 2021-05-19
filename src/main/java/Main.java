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
        long minSize = 11;
        long maxSize = 99;
        String regEx = "t.*";
        String keyLine = "Our World is so big!";
        System.out.println(findFile(directoryToSearch, searchFile, minSize, maxSize, regEx, keyLine));
        System.out.println(searchFileByKeyWord("/Users/raydavis/Desktop/developmentJAVA/Modul2/freedom.txt","Our"));

    }

    public static List<Path> findFile(String directory, String name, long minSize, long maxSize, String regEx, String keyLine) throws IOException {
        try (Stream<Path> files = Files.walk(Paths.get(directory))) {
            return files
                    .filter(p -> p.getFileName().toString().equals(name) || p.toFile().getName().matches(regEx))
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

    public static List<String> searchFileByKeyWord(String path, String match) {

        List<String> linesToPresent = new ArrayList<>();

        File f = new File("/Users/raydavis/Desktop/developmentJAVA/Modul2/freedom.txt");
        FileReader fr;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            do {
                line = br.readLine();
                Pattern p = Pattern.compile(match);
                Matcher m = p.matcher("Our");
                if (m.find())
                    linesToPresent.add(line);
            } while (line != null);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesToPresent;
    }
}