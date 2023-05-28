package pl.edu.agh.mwo;

import org.apache.commons.cli.*;
import pl.edu.agh.mwo.converters.FileCrawler;
import pl.edu.agh.mwo.excelImport.ExcelImport;
import pl.edu.agh.mwo.exceptions.EmptyPathException;
import pl.edu.agh.mwo.exceptions.LackOfActionException;
import pl.edu.agh.mwo.exceptions.LackOfPathException;
import pl.edu.agh.mwo.raports.RaportOne;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

import java.util.HashMap;


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
            } else{  ///TOOOOOO
                throw new LackOfPathException("Musisz podać ścieżkę. Zrób to za pomocą komendy '-path'.");
            }

            if(!path.equals("")){
                if(cmd.hasOption("report_1")){
                    ExcelImport ei = new ExcelImport();
                    importedData= ei.excelImport(fileList);

                    RaportOne raport1= new RaportOne();
                    raport1.analyze(importedData);
                } else {
                    throw new LackOfActionException("Musisz podać, co zrobić z danymi. Wykorzystaj opcje raportowania.");
                }
            } else {
                throw new EmptyPathException("Nie możesz użyć pustej ścieżki");
            }
        } catch(ParseException e) {
            System.err.println("Błąd parsowania argumentu: " + e.getMessage());
        } catch (LackOfPathException e) {  ///TOOOOOO
            System.err.println(e.getMessage());
        } catch (EmptyPathException e) {  ///TOOOOOO
            System.err.println(e.getMessage());
        } catch (LackOfActionException e) {
            System.err.println(e.getMessage());
        }


    }
}
