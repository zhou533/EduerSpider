package com.gozap.spider.model.impl;

import au.com.bytecode.opencsv.CSVWriter;
import com.gozap.spider.conf.Configuration;
import com.gozap.spider.model.Saveable;
import com.gozap.spider.utils.Pair;
import org.apache.log4j.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(Eduer.class);

    private List<Pair> values = new ArrayList<Pair>();

    public Eduer() {
    }

    public void addValue(String key, String value){
        if (key == null) key = "";
        if (value == null) value = "";
        Pair pair = new Pair(key, value);
        values.add(pair);
    }

    @Override
    public void save2Csv() {
        try {
            String pathName = Configuration.getInstance().getSavePath();
            File path = new File(pathName);
            if (!path.exists()){
                LOGGER.info("dir does not exist:" + pathName);
                path.mkdirs();
            }
            String fileName = pathName +
                    Configuration.getInstance().getTaskName()+
                    Configuration.getInstance().getTimeSymbol() + ".csv";
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            int size = values.size();
            String[] strs = new String[size];
            for (int i = 0; i < size; i++){
                strs[i] = values.get(i).getValue();
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
