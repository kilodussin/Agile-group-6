package Model;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
maxEntries decides how many rows of highscores should be displayed
filePath is path to highscores .txt document
 */

public class HighscoreIO {
    private final int maxEntries = 10;
    private final String filePath = "Resources/highscores.txt";

    /**
    readFile reads the local .txt file and appends it to a list of object type
    'Highscores'. Splits on ',', trims unwanted things and then appends to the ArrayList

    Failsafe implemented to catch faulty lines or empty lines

    Returns currentHighscores as it is going to be needed to be written over
    (can't append text row in the middle of the highscores for example).

     */

    public ArrayList<Highscores> readFile(String pathToFile) throws FileNotFoundException {

        ArrayList<Highscores> currentHighscores = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(pathToFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String txtLine;

            while ((txtLine = reader.readLine()) != null) {

                String[] lineParts = txtLine.split(",");

                if (lineParts.length == 3) {
                    String name = lineParts[0].trim();
                    int time = Integer.parseInt(lineParts[1].trim());
                    double score = Double.parseDouble(lineParts[2].trim());

                    Highscores highscores = new Highscores(name, time, score);
                    currentHighscores.add(highscores);
                } else {
                    System.out.println("Line not valid, line is: " + txtLine);
                }
            }

        } catch (IOException e) {
            throw new FileNotFoundException("Something wrong" + e);
        }

        return currentHighscores;
    }


    /**
    Writes the .txt file with highscores, updates every time a game is over.

     */

    public void writeFile(String PathToFile, ArrayList<Highscores> curHighscores) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Highscores x : curHighscores) {
                String appendLine = x.getPlayerName() + "," + x.getTime() + "," + x.getScore();
                bufferedWriter.write(appendLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**

    This could be useful later

    public boolean isThisAHighscore(ArrayList<Highscores> curHighscores, Highscores newEntry) {



        if (curHighscores.size() < maxEntries) {
            return true;
        }

        double curLow = curHighscores.get(0).getScore();
        for (Highscores x : curHighscores) {
            if (x.getScore() < curLow) {
                curLow = x.getScore();
            }
        }

        if (newEntry.getScore() > curLow) {
            System.out.println("Beats current lowest");
            return true;
        } else {
            System.out.println("Does not beat current lowest");
            return false;
        }
    }

     */

    /*
    Because we're using a simple .txt setup we cannot insert rows in the middle of
    the file. We therefore need to completely wipe and rewrite it in a correct order.

    This function sorts and rewrites the .txt file every time a game ends.
    Highest score is always on top in list, rest is sorted.
     */


    public void sortAndWrite(Highscores newEntry) throws FileNotFoundException {

        try {
            ArrayList<Highscores> curHighscore = readFile(filePath);

            curHighscore.add(newEntry);
            curHighscore.sort(
                    Comparator.comparingDouble(Highscores::getScore).reversed()

            );
            if (curHighscore.size() > maxEntries) {
                curHighscore = new ArrayList<>(curHighscore.subList(0,maxEntries));

            }
            writeFile(filePath, curHighscore);

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        }

    }
}
