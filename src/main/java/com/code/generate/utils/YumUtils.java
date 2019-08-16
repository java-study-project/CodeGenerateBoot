package com.code.generate.utils;

import com.esotericsoftware.yamlbeans.YamlReader;
import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：yum配置操作
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class YumUtils {

    public static Map readYumProps(String url){
        try {
            File dumpFile = FileUtils.getResourceFile(url);
            return Yaml.loadType(dumpFile, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件["+url+"]报错", e);
        }
    }
    public static <T> T readYumProps(String url, Class<T> clazz){
        try {
            File dumpFile = FileUtils.getResourceFile(url);
            return Yaml.loadType(dumpFile, clazz);
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件["+url+"]报错", e);
        }
    }
    public static <T> T readYumProps3(String url, Class<T> clazz){
        try {
            File dumpFile = FileUtils.getResourceFile(url);
            YamlReader reader = new YamlReader(new FileReader(dumpFile));
            return reader.read(clazz);
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件["+url+"]报错", e);
        }
    }
}
