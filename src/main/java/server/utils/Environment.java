package server.utils;

import server.managers.CollectionManager;
import server.commands.ICommand;


import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Environment {
    private CollectionManager collectionManager;
    private BufferedReader bufferedReader;
    private PrintStream printStream;
    private ArrayList<String> history;
    private ICommand[] allCommands;
    private long pointer;

    public long getPointer() {
        return pointer;
    }

    public void setPointer(long pointer) {
        this.pointer = pointer;
    }

    public Environment(CollectionManager collectionManager, BufferedReader bufferedReader, PrintStream printStream, ArrayList<String> history, ICommand[] allCommands){
        this.collectionManager = collectionManager;
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
        this.history = history;
        this.allCommands = allCommands;
        this.pointer = 0;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public ICommand[] getAllCommands() {
        return allCommands;
    }

    public void setAllCommands(ICommand[] allCommands) {
        this.allCommands = allCommands;
    }
}

