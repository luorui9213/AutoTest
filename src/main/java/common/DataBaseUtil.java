package common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtil {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    //查询
    public void query(String sql){
        try {
            connection = JdbcUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println("查询成功：");
            System.out.println(JdbcUtil.resultSetToJson(resultSet));
            System.out.println();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.releaseResources(resultSet,statement,connection);
        }
    }
}
