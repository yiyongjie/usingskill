package com.skill.util;

import com.skill.common.model.gen.GenContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 基本文件生成工具
 */
public class FreemarkUtil {

    public static void createData(){
        Configuration configuration = new Configuration();
        Writer out = null;
        Properties prop = new Properties();
        try {
            //获取生成的位置
            prop.load(new FileInputStream(new File("src/main/resources/genurl.properties")));
            //获取模版路径
            Resource resource = new ClassPathResource("templates");
            File file = resource.getFile();
            configuration.setDirectoryForTemplateLoading(file);
            //获取到这个数据库里所有的表
            List<String> tableNames=DataUse.getTableNames();
            String tableName=prop.getProperty("tablename");
            boolean isContain=false;
            if(StringUtils.isBlank(tableName)){
                System.out.println("获取到的表名是空的");
            }else {
                isContain=tableNames.contains(tableName);
            }
            if(isContain){
                //准备一下模板参数
                Map<String, Object> dataMap = new HashMap<String, Object>();
//                dataMap.put("modelClassPath", "com.skill.model");
//                dataMap.put("mapperClassPath", "com.skill.dao");
                dataMap.put("modelClassPath", prop.getProperty("modelClassPath"));
                dataMap.put("mapperClassPath", prop.getProperty("mapperClassPath"));
                GenContent genContent=DataUse.getModel(tableName);
                dataMap.put("genContent",genContent);
                //  加载模版文件
                Template modelTemplate = configuration.getTemplate("model.ftl");
                Template dtoTemplate = configuration.getTemplate("dto.ftl");
                Template volTemplate = configuration.getTemplate("vo.ftl");
                Template mapperTemplate = configuration.getTemplate("mapper.ftl");
                Template xmlTemplate = configuration.getTemplate("xml.ftl");
                //生成数据位置,因拆多模块所以加模块，单模块不需要
//                String modelPosition=MODEL_PATH +"/"+genContent.getClassName()+".java";
//                String mapperPosition=MAPPER_PATH +"/"+genContent.getClassName()+"Mapper"+".java";
//                String xmlPosition=XML_PATH +"/"+genContent.getClassName()+".xml";
                String modelPosition=prop.getProperty("MODEL_PATH") +"/"+genContent.getClassName()+".java";
                String dtoPosition=prop.getProperty("DTO_PATH") +"/"+genContent.getClassName()+"DTO.java";
                String voPosition=prop.getProperty("VO_PATH") +"/"+genContent.getClassName()+"VO.java";
                String mapperPosition=prop.getProperty("MAPPER_PATH") +"/"+genContent.getClassName()+"Mapper"+".java";
                String xmlPosition=prop.getProperty("XML_PATH") +"/"+genContent.getClassName()+"Mapper"+".xml";
                //输出model文件
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(modelPosition))));
                modelTemplate.process(dataMap, out);
                //输出dto文件
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dtoPosition))));
                dtoTemplate.process(dataMap, out);
                //输出vo文件
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(voPosition))));
                volTemplate.process(dataMap, out);
                //输出mapper文件
                out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(mapperPosition))));
                mapperTemplate.process(dataMap, out);
                //输出xml文件
                out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(xmlPosition))));
                xmlTemplate.process(dataMap, out);
                System.out.println(genContent.getClassName()+"对象生成成功");
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        createData();
    }
}
