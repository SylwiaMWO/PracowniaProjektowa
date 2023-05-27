package pl.edu.agh.mwo.converters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class FileCrawler {

    static private ArrayList<File> fileList;
    static private String sourceDir, fileType;

    static boolean print = false;

    static InputStream inputStream;

    public FileCrawler(String sourceDir, String fileType) {
        FileCrawler.sourceDir = sourceDir;
        FileCrawler.fileList = new ArrayList<File>();
        FileCrawler.fileType = fileType;
    }

    public ArrayList<File> getFiles(){
        crawlFiles();
        return fileList;
    }

    public void crawlFiles() {
        File files[] = new File(sourceDir).listFiles();
        traverseFolder(files);
    }

    private static void traverseFolder(File[] files) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    traverseFolder(file.listFiles());
                } else {
                    String[] type = file.getName().toString().split("\\.(?=[^\\.]+$)");
                    if (type.length > 1) {
                        if (type[1].equals(fileType)) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }



}