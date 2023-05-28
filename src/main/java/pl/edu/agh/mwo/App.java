package pl.edu.agh.mwo;

import org.apache.commons.cli.*;
import pl.edu.agh.mwo.converters.FileCrawler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Options options = new Options();
        options.addOption("help",true,"Show help");
        options.addOption("path",true,"Path to file");
        options.addOption("report_1",false,"Print raport one");
        CommandLineParser parser = new DefaultParser();

        ArrayList<File> fileList = new ArrayList<>();

        try{
            CommandLine cmd = parser.parse(options, args);
            String path = "";
            if (cmd.hasOption("path")) {
                path = cmd.getOptionValue("path");
                FileCrawler fileCrawler = new FileCrawler(path, "xls");
                fileList =fileCrawler.getFiles();
            } else{
                System.out.println("Musisz podać ścieżkę. Zrób to za pomocą komendy '-path'.");
            }

            if(!path.equals("")){
                if(cmd.hasOption("report_1")){
                    System.out.println("Generowanie raportu - jako argument podamy listę ścieżek");
                }
            }

        } catch(ParseException e) {
            System.err.println("Błąd parsowania argumentu: " + e.getMessage());
        }
    }
}
