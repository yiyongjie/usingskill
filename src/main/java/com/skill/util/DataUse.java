package com.skill.util;

import com.skill.common.model.gen.GenColumn;
import com.skill.common.model.gen.GenContent;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class DataUse {

    private static  String DRIVER ;
    private static  String URL ;
    private static  String USERNAME ;
    private static  String PASSWORD ;
    private static Properties prop = new Properties();

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            prop.load(new FileInputStream(new File("src/main/resources/application.properties")));
            DRIVER=prop.getProperty("spring.datasource.driver-class-name");
            URL=prop.getProperty("spring.datasource.url");
            USERNAME=prop.getProperty("spring.datasource.username");
            PASSWORD=prop.getProperty("spring.datasource.password");
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("can not load jdbc driver", e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            log.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW TABLES");
        try {
            //获取数据库的元数据
            //从元数据中获取到所有的表名
            while(rs.next()) {
                tableNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取数据库下的所有表的注释
     */
    public static String getTableCommonts(String tableName) throws SQLException {
        String tablecommnet = "";
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
        try {
            //获取数据库的元数据
            //从元数据中获取到所有的表名
            if(rs!=null){
                rs.next();
            }
            String createDDL = rs.getString(2);
            tablecommnet = parse(createDDL);
        } catch (SQLException e) {
            log.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
        return tablecommnet;
    }

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }

    /**
     * 获取表主键
     * @param tableName
     * @throws Exception
     */
    public static String getMysqlTablePK(String tableName) throws Exception {
        Connection conn = getConnection();
        ResultSet rs = null;
        rs = conn.getMetaData().getPrimaryKeys(conn.getCatalog().toUpperCase(), null, tableName.toUpperCase());
        rs.next();
        closeConnection(conn);
        return rs.getString("COLUMN_NAME");
    }

    /**
     * 获取模板元素
     * @param tableName
     * @return
     */
    public static GenContent getModel(String tableName) throws Exception {
        GenContent genContent=new GenContent();
        String[] splitTableName=tableName.split("_");
        StringBuffer className=new StringBuffer();
        StringBuffer varName=new StringBuffer();
        //这是对象的类名,变量名
        for(int i=0;i<splitTableName.length;i++){
            //把下斜杠去掉再把每个斜杠后面的字符大写
            String splitContent=splitTableName[i].substring(0,1).toUpperCase().concat(splitTableName[i].substring(1));
            String splitVarContent=i==0?splitTableName[i].substring(0,1).concat(splitTableName[i].substring(1)):splitTableName[i].substring(0,1).toUpperCase().concat(splitTableName[i].substring(1));
            className.append(splitContent);
            varName.append(splitVarContent);
        }
        //获取表的注释
        String tableComment=getTableCommonts(tableName);
        //获取表的主键
        String pk=getMysqlTablePK(tableName);
        genContent.setClassName(className.toString());
        genContent.setVarName(varName.toString());
        genContent.setTableName(tableName);
        genContent.setTableComment(tableComment);
        //拿到所有的字段属性
        List<String> columnNames=getColumnNames(tableName);
        List<String> columnType=getColumnTypes(tableName);
        List<String> columnComment=getColumnComments(tableName);
        List<GenColumn> columns=new ArrayList<>();
        for(int i=0;i<columnNames.size();i++){
            GenColumn genColumn=new GenColumn();
            genColumn.setColumn(columnNames.get(i));
            if(columnType.get(i).equals("INT")){
                genColumn.setColumnType("INTEGER");
            }else {
                genColumn.setColumnType(columnType.get(i));
            }
            genColumn.setColumnRemark(columnComment.get(i));
            if(columnNames.get(i).equals(pk)){
                genColumn.setIsPK(1);
            }
            String javaType=convertType(columnType.get(i));
            genColumn.setColumnJavaType(javaType);
            //对象里的字段名称
            StringBuffer ModelColunmName=new StringBuffer();
            String[] splitColumnName=columnNames.get(i).split("_");
            for(int j=0;j<splitColumnName.length;j++){
                String splitContent=j==0?splitColumnName[j].substring(0,1).concat(splitColumnName[j].substring(1)):splitColumnName[j].substring(0,1).toUpperCase().concat(splitColumnName[j].substring(1));
                ModelColunmName.append(splitContent);
            }
            genColumn.setModelColumn(ModelColunmName.toString());
            columns.add(genColumn);
        }
        genContent.setGenColumns(columns);
        return genContent;
    }

    /**
     * 根据数据库类型转换为java类型
     * @param columnType
     * @return
     */
    public static String convertType(String columnType){
        String javaType="";
        switch (columnType){
            case "INT":javaType="int";break;
            case "BIGINT":javaType="long";break;
            case "VARCHAR":javaType="String";break;
            case "DECIMAL":javaType="BigDecimal";break;
            case "DATETIME":javaType="Date";break;
            case "TIMESTAMP":javaType="Date";break;
        }
        return javaType;
    }

    /**
     * 截取注释信息
     * @param all
     * @return
     */
    public static String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    public static void main(String[] args) throws Exception {
//        List<String> tableNames = getTableNames();
//        System.out.println("tableNames:" + tableNames);
//        for (String tableName : tableNames) {
//            System.out.println("ColumnNames:" + getColumnNames(tableName));
//            System.out.println("ColumnTypes:" + getColumnTypes(tableName));
//            System.out.println("ColumnComments:" + getColumnComments(tableName));
//            System.out.println(getTableCommonts("user"));
//        }
        System.out.println(getMysqlTablePK("user_test"));
//        GenContent genContent=getModel("user_test");
//        System.out.println(genContent.getClassName());
    }
}
