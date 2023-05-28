package pl.edu.agh.mwo;

import org.apache.commons.cli.*;
import pl.edu.agh.mwo.converters.FileCrawler;
import pl.edu.agh.mwo.excelImport.ExcelImport;
import pl.edu.agh.mwo.raports.RaportOne;
import pl.edu.agh.mwo.raports.RaportThree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import pl.edu.agh.mwo.converters.data_to_excel;
import java.util.HashMap;
import java.util.Map;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        List<List<List<List<Object>>>> importedData= new ArrayList<>();

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
                FileCrawler fileCrawler = new FileCrawler(path, "xlsx");
                fileList =fileCrawler.getFiles();
            } else{
                System.out.println("Musisz podać ścieżkę. Zrób to za pomocą komendy '-path'.");
            }

            if(!path.equals("")){
                if(cmd.hasOption("report_1")){
                    //System.out.println("Generowanie raportu - jako argument podamy listę ścieżek");
                    ExcelImport ei = new ExcelImport();
                    importedData= ei.excelImport(fileList);

                    RaportOne raport1= new RaportOne();


                    raport1.analyze(importedData);

                    RaportThree raport3= new RaportThree();
                    raport3.analyze(importedData);

                }
            }

        } catch(ParseException e) {
            System.err.println("Błąd parsowania argumentu: " + e.getMessage());
        }
    }

}
