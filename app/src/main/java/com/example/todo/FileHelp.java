package com.example.todo;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class FileHelp {
    public static final String filename = "listinfo.dat";

    public static void writeData(ArrayList<String> tasks, Context context){

        try{
            FileOutputStream fos = context.openFileOutput(filename,context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(Context context){
        ArrayList<String> taskList = null;

        if(!fileExists(context)){
            try {
                new FileOutputStream(filename, true).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            taskList = new ArrayList<>();
            taskList = (ArrayList<String>) ois.readObject();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(taskList == null){
            System.out.println("tasklist is null");
        }

        return taskList;
    }

    private static boolean fileExists(Context context){

        File file = context.getFileStreamPath(filename);

        if(file == null || !file.exists()){
            return false;
        }

        return true;
    }
}
