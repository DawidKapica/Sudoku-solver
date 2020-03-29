package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterToCsv {

    private String CSV_FILE_NAME;
    private List<String> dataLines = new ArrayList<>();

    public WriterToCsv (String fileName) {
        CSV_FILE_NAME = fileName;
    }

    public WriterToCsv () {
        CSV_FILE_NAME = "exampleFileNameSudoku.csv";
    }

    public void createCsv() throws IOException {
        FileWriter csvWriter = new FileWriter(CSV_FILE_NAME);
        for (int i = 0; i < dataLines.size(); i++) {
            csvWriter.append(dataLines.get(i));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public void addTextToConvert(String text){
        dataLines.add(text);
    }

}
