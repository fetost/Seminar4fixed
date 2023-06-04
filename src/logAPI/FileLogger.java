package logAPI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger {
    private PrintWriter writeToLog;

    public FileLogger(){
        try{
            writeToLog = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException ioe) {
            System.out.println("ERROR: LOG could not be performed.");
            ioe.printStackTrace();
        }
        
    }
    public void log(String message) {
        writeToLog.println(message);
        writeToLog.flush();
    }
}

