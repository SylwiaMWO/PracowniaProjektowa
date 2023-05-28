package pl.edu.agh.mwo;

import org.apache.commons.cli.*;
import pl.edu.agh.mwo.converters.FileCrawler;
import pl.edu.agh.mwo.excelImport.ExcelImport;
import pl.edu.agh.mwo.exceptions.EmptyPathException;
import pl.edu.agh.mwo.exceptions.LackOfActionException;
import pl.edu.agh.mwo.exceptions.LackOfPathException;
import pl.edu.agh.mwo.raports.Raport;
import pl.edu.agh.mwo.raports.RaportOne;
import pl.edu.agh.mwo.raports.RaportThree;
import pl.edu.agh.mwo.converters.ImportDataToExcel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class App {

    private static List<List<List<List<Object>>>> importedData= new ArrayList<>();
    private static ArrayList<File> fileList = new ArrayList<>();
    public static void main( String[] args ) {

        Options options = new Options();
        options.addOption("help",false,"Show help");
        options.addOption("path",true,"Path to file or folder");
        options.addOption("output",true,"Path to output folder");
        options.addOption("report_1",false,"Print report one");
        options.addOption("report_3",false,"Print report three");
        CommandLineParser parser = new DefaultParser();

        try{
            CommandLine cmd = parser.parse(options, args);
            String path = "";
            String outputPath;
            if (cmd.hasOption("help")) {
                printHelp();
                return;
            }

            if (cmd.hasOption("path")) {
                path = cmd.getOptionValue("path");
                FileCrawler fileCrawler = new FileCrawler(path, "xlsx");
                fileList =fileCrawler.getFiles();
            } else{
                throw new LackOfPathException("Musisz podać ścieżkę. Zrób to za pomocą komendy '-path'.");
            }

            if (cmd.hasOption("output")) {
                outputPath = cmd.getOptionValue("output");
            } else {
                throw new LackOfPathException("Musisz podać ścieżkę, gdzie zapisać plik. Zrób to za pomocą komendy '-output'.");
            }

            if(!path.equals("")){
                if(cmd.hasOption("report_1")) {
                    System.out.println("##### RAPORT 1 #####");
                    RaportOne raport1 = new RaportOne();
                    handleAnalysis(raport1, outputPath, "Projekt", "Raport_1");
                } else if( cmd.hasOption("report_3")) {
                    System.out.println();
                    System.out.println("##### RAPORT 3 #####");
                    RaportThree raport3= new RaportThree();
                    handleAnalysis(raport3, outputPath, "Zadanie", "Raport_3");
                } else {
                    throw new LackOfActionException("Musisz podać, co zrobić z danymi. Wykorzystaj opcje raportowania -report_*.");
                }
            } else {
                    throw new EmptyPathException("Nie możesz użyć pustej ścieżki");
            }
        } catch (LackOfPathException e) {
            System.err.println(e.getMessage());
        } catch (EmptyPathException e) {
            System.err.println(e.getMessage());
        } catch (LackOfActionException e) {
            System.err.println(e.getMessage());
        } catch(ParseException e) {
            System.err.println("Błąd parsowania argumentu: " + e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void handleAnalysis(Raport raport, String outputPath, String columnName, String fileName) throws IOException {
        ExcelImport ei = new ExcelImport();
        importedData= ei.excelImport(fileList);
        Map<String, Double> test = raport.analyze(importedData);
        ImportDataToExcel.readHashMapToExcelReport_1_3(test, columnName, fileName, outputPath);
    }

    public static void printHelp() {
        System.out.println("#################################################################################");
        System.out.println("Aby uruchomić analizę danych należy podać: ");
        System.out.println("\t\t-path <ścieżka> -output <ścieżka> -report_*");
        System.out.println("Gdzie: ");
        System.out.println("\t -path <ścieżka>  :  ścieżka do folderu/pliku wejściowego");
        System.out.println("\t -output <ścieżka>  :  ścieżka do folderu wyjściowego (eksport pliku xlsx)");
        System.out.println("\t -report_*   :  rodzaj raportu do wygenerowania. Dostępne opcje: report_1, report_3");
        System.out.println("################################################################################");
    }

}
