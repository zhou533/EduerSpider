package com.gozap.spider.conf;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-5-31
 * Time: PM4:36
 * To change this template use File | Settings | File Templates.
 */
public class Configuration {
    private static final Logger LOGGER = Logger.getLogger(Configuration.class);
    private static Configuration instance = new Configuration();

    private String url;
    private int interval; //
    private String savePath;
    private boolean isSaveHtml;
    private boolean isOnlyEmail;
    private String taskName;



    /*

     */
    private Configuration(){


    }

    /*

     */
    public static Configuration getInstance(){
        return instance;
    }

    /*

     */
    public static boolean hasValidExt(String filename){
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex < 0){
            return false;
        }
        String ext = filename.substring(dotIndex);
        if (ext.equalsIgnoreCase(".xml")){
            return true;
        }
        return false;
    }

    /*

     */
    public boolean load(String filename){
        boolean result = false;
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(filename));

            Element config = doc.elementByID("config");
            if (config == null){
                throw new Exception("invalid configuration file.");
            }

            Element taskArgListElement = config.element("task_arg_list");
            if (taskArgListElement != null){
                List taskArgList = taskArgListElement.elements("task_arg");
            }


            result = true;
        } catch (Exception e){
            LOGGER.error("configuration file parse failed:", e);
        }
        return result;
    }

    /*
    getter
     */

    public String getUrl() {
        return url;
    }

    public int getInterval() {
        return interval;
    }

    public String getSavePath() {
        return savePath;
    }

    public boolean isSaveHtml() {
        return isSaveHtml;
    }

    public boolean isOnlyEmail() {
        return isOnlyEmail;
    }

    public String getTaskName() {
        return taskName;
    }
}

