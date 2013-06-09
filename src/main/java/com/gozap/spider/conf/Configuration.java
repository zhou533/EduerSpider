package com.gozap.spider.conf;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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

    private int threadCount;

    private Arguments arguments = new Arguments();
    private List<Selector> selectors = new ArrayList<Selector>();

    /*

     */
    private Configuration(){
        isSaveHtml = false;
        threadCount = 1;
        taskName = "Unknown";
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

            Element config = doc.getRootElement();
            if (config == null){
                throw new Exception("invalid configuration file.");
            }

            Element taskUrlElement = config.element("task_url");
            if (taskUrlElement == null || taskUrlElement.getText() == null){
                throw new Exception("an available url is necessary.");
            }
            this.url = taskUrlElement.getText();
            if (!this.url.matches("[a-zA-z]+://[^\\s]*")){
                throw new Exception("an available url is necessary.");
            }

            Element taskArgListElement = config.element("task_arg_list");
            if (taskArgListElement == null){
                throw new Exception("please indicate task arguments.");
            }

            int argCount = getSubStringCount(this.url, "%s");
            List taskArgList = taskArgListElement.elements("task_arg");
            if (argCount != taskArgList.size()){
                throw new Exception("arguments amount error.");
            }

            Iterator<Element> iterator = taskArgList.iterator();
            while (iterator.hasNext()){
                Element taskArgElement = iterator.next();

                //Argument argument = new Argument(taskArgElement.getText());
                //arguments.addArgument(argument);
                Attribute type = taskArgElement.attribute("type");
                if (type == null){
                    throw new Exception("invalid argument type..");
                }

                LOGGER.info(type.getValue() + ":" + taskArgElement.getText());
                if (type.getValue().equalsIgnoreCase("alpha")){
                    AlphaArgument alphaArgument = new AlphaArgument(taskArgElement.getText());
                    arguments.addArgument(alphaArgument);
                }else if (type.getValue().equalsIgnoreCase("number")){
                    NumberArgument numberArgument = new NumberArgument(taskArgElement.getText());
                    arguments.addArgument(numberArgument);
                }else {
                    throw new Exception("undefined argument type..");
                }
            }


            //output
            Element taskOutListElement = config.element("task_out_list");
            if (taskOutListElement == null){
                throw new Exception("Please indicate output parameters.");
            }

            List<Element> taskOutList = taskOutListElement.elements("task_out");
            Iterator<Element> tastOutIterator = taskOutList.iterator();
            while (tastOutIterator.hasNext()){
                Element taskOutElement = tastOutIterator.next();
                String name = taskOutElement.attributeValue("name");
                String query = new String(taskOutElement.getText().getBytes("UTF-8"));
                Selector selector = new Selector(name, query);
                selectors.add(selector);
            }

            //
            Element taskNameElement = config.element("task_name");
            if (taskNameElement != null){
                taskName = taskNameElement.getText();
            }

            result = true;
            LOGGER.info("Configuration loaded...");
        } catch (Exception e){
            LOGGER.error("configuration file parse failed:", e);
        }
        return result;
    }

    /*
    private
     */
    private int getSubStringCount(String destStr, String subString){
        int count = 0, idx = 0;
        while (idx < destStr.length() && (idx = destStr.indexOf(subString,idx)) >= 0){
            idx += subString.length();
            count++;
        }
        return count;
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

    public int getThreadCount() {
        return threadCount;
    }

    public Arguments getArguments() {
        return arguments;
    }

    public List<Selector> getSelectors() {
        return selectors;
    }
}

