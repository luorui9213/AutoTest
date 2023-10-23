package common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private static final String driverName;
    private static final String url;
    private static final String username;
    private static final String password;
    /*
    * 静态代码块，类初始化时加载数据库驱动
    */
    static {
        try {
            //加载配置文件
            InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            //获取驱动名称、url、用户名、密码
            driverName = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            //加载驱动
            Class.forName(driverName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    //释放资源
    public static void releaseResources(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
          resultSet = null;
          try {
              if (statement != null){
                  statement.close();
              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          } finally {
              statement = null;
              try {
                  if (connection != null){
                      connection.close();
                  }
              } catch (SQLException e) {
                  throw new RuntimeException(e);
              } finally {
                  connection = null;
              }
          }
        }
    }

    //将查询结果转换为Json格式
    public static String resultSetToJson(ResultSet resultSet){
        JSONArray jsonArray = new JSONArray();
        //数据库中每行的数据对象
        JSONObject row = null;
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //遍历数据集
            while (resultSet.next()){
                row = new JSONObject();
                //获取表列数
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i =1; i <= columnCount; i++){
                    //列名
                    String columnName = resultSetMetaData.getColumnName(i);
                    //值
                    String value = resultSet.getString(columnName);
                    //添加到row对象
                    row.put(columnName, value);
                }
                jsonArray.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return JSON.toJSONString(jsonArray, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullListAsEmpty);
    }
}
