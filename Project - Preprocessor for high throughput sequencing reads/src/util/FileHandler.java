package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;





public class FileHandler
{
    private PrintWriter writer;


    public static ArrayList<String> readLinesFromFile(String filename)
    {
        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = reader.readLine()) != null)
                lines.add(line);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return lines;
    }



    public static void writeLinesToFile(String filename, ArrayList<String> lines)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename)))
        {
            for (String line : lines)
                writer.println(line);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }



    public void openWriter(String filename)
    {
        try
        {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException ex)
        {
            ex.printStackTrace();
            writer = null;
        }
    }



    public void writeLineToWriter(String line)
    {
        if (writer == null) return;
        writer.println(line);
    }



    public void closeWriter()
    {
        if (writer == null) return;
        writer.close();
    }
}
