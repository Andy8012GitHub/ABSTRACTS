package com.app.myapp.abstracts; /**
 * Created by wilsoash005 on 4/16/2018.
 */
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class ABSTRACTSFileRead {
    public String fileName = "";
    private ArrayList<String> fileLines = new ArrayList<>();
    private String fileLine = ""; //Person, Place, and Thing (in one line)

    //We will create ABSTRACTSFileRead objects for which files we use when the game play runs.
    //That depends on the mode and/or in-app purchases.
    public ABSTRACTSFileRead(Context context, String fileName) {
        this.fileName = fileName;
        readFile(context, fileName);
    }

    public void readFile(Context context, String fileName) {
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            while(line != null) {
                fileLines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void newFileLine() {
        Random rand = new Random();
        int randInt = rand.nextInt(fileLines.size());
        fileLine = fileLines.get(randInt);
    }

    public String getPerson() {
        return fileLine.substring(fileLine.indexOf("Pe:") + 3, fileLine.indexOf("Pl:")).trim();
    }

    public String getPlace() {
        return fileLine.substring(fileLine.indexOf("Pl:") + 3, fileLine.indexOf("T:")).trim();
    }

    public String getThing() {
        return fileLine.substring(fileLine.indexOf("T:") + 2).trim();
    }

    public String getClueOrClues() {
        newFileLine();
        return fileLine;
    }

}