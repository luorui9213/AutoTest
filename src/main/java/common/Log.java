package common;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    public void printLog(String message){
        System.out.println("++++++++++ " + message +" ++++++++++");
    }
    public void writeLog(String message) {
        String dirPath = "log";
        //设置日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        //新建文件夹
        File dir = new File(dirPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dirPath + "\\" + date + "日志.txt");
        FileWriter writer = null;
        try {
            if (!file.exists()){
                //创建目标文件
                file.createNewFile();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(new Date());
            //向文件写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            writer = new FileWriter(file,true);
            writer.write("日志记录时间：" + time + System.getProperty("line.separator"));
            writer.write(message);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
