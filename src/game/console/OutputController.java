package game.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OutputController {


    // I can't find a method for clearing the terminal's screen
    private OutputController() {

    }
    public static void clearTerminal(){
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (IOException c){
            // this is a plceholder
        }


    }

}

