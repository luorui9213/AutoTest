import common.DataBaseUtil;

public class SqlTest {
    public static void main(String[] args) {
        DataBaseUtil dataBaseUtil = new DataBaseUtil();
        String sql = "select * from user";
        dataBaseUtil.query(sql);
    }
}
