package pl.edu.agh.mwo;

import org.apache.commons.cli.*;

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
        options.addOption("path_to_file",true,"Path to file");
        options.addOption("path_to_folder",
                true,"Path to folder");
        options.addOption("raport_1",false,"Print raport one");
        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(options, args);
            String path = "";
            boolean isFile;
            if(cmd.hasOption("path_to_file")){
                obsluzKomende("file");
                path = cmd.getOptionValue("path_to_file");
                isFile = true;
            } else if (cmd.hasOption("path_to_folder")) {
                path = cmd.getOptionValue("path_to_folder");
                isFile = false;
            } else{
                System.out.println("Musisz podać ścieżkę. Zrób to za pomocą komendy 'path_to_file' lub 'path_to_folder'.");

            }
            if(!path.equals("")){
                if(cmd.hasOption("raport_1")){
                    /*ReportGenerator reportGenerator = new ReportGenerator();
                    repo*/
                    obsluzKomende("raport");
                }
            }

        } catch(ParseException e) {
            System.err.println("Błąd parsowania argumentu: " + e.getMessage());
        }
    }
    private static void obsluzKomende(String s){
        System.out.println("Wywołano komendę " + s);
}
}
