package com.example.mytodolist;

import java.util.ArrayList;
import java.util.Random;

public class Database {
    private ArrayList<Note> notes = new ArrayList<>();

    private static Database instance = null;

    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    public void add(Note note){
        notes.add(note);
    }

    private Database(){
        Random random = new Random();
        for (int i = 0; i<20;i++){
            Note note = new Note(i,"Note"+i,random.nextInt(3));
            notes.add(note);
        }
    }

    public void remove(int id){
        for (Note note: notes){
            if (id == note.getId()){
                notes.remove(note);
                break;
            }
        }
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}
