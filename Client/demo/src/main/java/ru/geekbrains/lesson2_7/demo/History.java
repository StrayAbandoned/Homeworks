package ru.geekbrains.lesson2_7.demo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class History {
    private HelloController hc;
    private File history;
    private List<String> list;
    private Path path;

    History(String login){
        history = new File("Client/Hisory_"+ login +".txt");
        path = history.toPath();
        list = this.readFromFile();
    }

    public void writeOnFile (String s){
        try (FileWriter fw = new FileWriter(history, true)){
            fw.write(s + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> readFromFile (){
        List <String> strings = null;
        if (history.exists()){

            try {
                strings = Files.readAllLines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return strings;
    }

    public List<String> getList() {
        return list;
    }
}
