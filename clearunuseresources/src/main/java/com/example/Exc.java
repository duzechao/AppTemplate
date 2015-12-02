package com.example;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Exc {
    public static void main(String[] args) {
        String path = "/Users/dzc/Desktop/AndroidLintUnusedResources.xml";
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(path,new ParserHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ParserHandler extends DefaultHandler{
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//            System.out.println(uri+"    "+localName+"   "+qName+"    "+attributes.toString());
            for(int i=0;i<attributes.getLength();i++){
                String name = attributes.getQName(i);
                String filePath = attributes.getValue(i);
                if ("FQNAME".equals(name)&&!name.endsWith("string.xml")){
                    getFile("file://$PROJECT_DIR$","",filePath);
                }
            }
        }
    }

    private static File getFile(String preString,String appDir,String filePath){
        String filePathReal = filePath.substring(preString.length());
        System.out.println(filePathReal);
        File file = new File(filePathReal);
        System.out.println(file.getPath());
        return null;
    }
}
