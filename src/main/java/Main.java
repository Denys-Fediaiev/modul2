import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    String Directory = "/Users/raydavis/Desktop/developmentJAVA/Modul2";
    String Name = "freedom.txt";
    int maxSize = 0;
    int minSize = 0;

    public static void FileSearch(String path){
        try{
            Files.walk(Paths.get(path))
                    .filter(p->
                    { Files(p)

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}