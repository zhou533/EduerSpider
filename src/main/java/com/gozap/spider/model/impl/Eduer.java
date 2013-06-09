package com.gozap.spider.model.impl;

import au.com.bytecode.opencsv.CSVWriter;
import com.gozap.spider.conf.Configuration;
import com.gozap.spider.model.Saveable;
import com.sun.tools.javac.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-7
 * Time: PM5:48
 * To change this template use File | Settings | File Templates.
 */
public class Eduer implements Saveable{

    private List<Pair<String, String>> values = new ArrayList<Pair<String, String>>();

    public Eduer() {
    }

    public void addValue(String key, String value){
        Pair<String, String> pair = new Pair<String, String>(key, value);
        values.add(pair);
    }

    @Override
    public void save2Csv() {
        try {
            String fileName = Configuration.getInstance().getTaskName()+".csv";
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            int size = values.size();
            String[] strs = new String[size];
            for (int i = 0; i < size; i++){
                strs[i] = values.get(i).snd;
            }
            csvWriter.writeNext(strs);
            csvWriter.close();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {

        return values.toString();
    }
}
