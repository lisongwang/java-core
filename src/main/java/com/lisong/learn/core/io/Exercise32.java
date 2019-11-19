package com.lisong.learn.core.io;

import com.lisong.learn.core.io.util.TextFile;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

public class Exercise32 {

    private static void writeToXml(Map<String,Integer> resultMap, String fileName) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            Element root = new Element("WordCount");
            Document doc = new Document(root);
            for(Map.Entry<String,Integer> entry : resultMap.entrySet()) {
                Element element = new Element("word");
                element.addAttribute(new Attribute("name", entry.getKey()));
                element.appendChild(entry.getValue().toString());
                root.appendChild(element);
            }
            Serializer serializer = new Serializer(out, Charset.defaultCharset().name());
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            serializer.write(doc);
            serializer.flush();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String path = "JavaCore/src/main/java/com/lisong/learn/core/io/Exercise32.java";
        Map<String,Integer> resultMap = new HashMap<>();
        for(String word : new TextFile(path, "\\W+"))
            resultMap.put(word, resultMap.get(word) == null ? 1 : resultMap.get(word)+1);
        print(resultMap);
        String fileName = "JavaCore/src/main/java/com/lisong/learn/core/io/file/exercise32.xml";
        fileName= new File(fileName).getCanonicalPath();
        writeToXml(resultMap, fileName);
    }
}