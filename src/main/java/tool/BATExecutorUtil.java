package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 批处理文件的执行类.<br>
 * @author RuiQiYou <br>
 * @version 1.0.0 2020年2月20日<br>
 * @see
 * @since JDK 1.8.0
 */
public class BATExecutorUtil {

    /**
     * java进程.
     */
    public static Process process = null;

    /**
     * 开口窗口的模式执行bat文件.
     * @param batFilePath bat文件的绝对路径
     */
    public static void execBAT(final String batFilePath) {
        if (process != null) {
            process.destroy();
        }
        try {
            process = Runtime.getRuntime().exec(COMMAND_K + batFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 无窗口的模式执行bat文件.
     * @param batFilePath bat文件的绝对路径
     */
    public static void execBATNoConsole(final String batFilePath) {
        if (process != null) {
            process.destroy();
        }
        try {
            process = Runtime.getRuntime().exec(COMMAND_C + batFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 执行命令
     * @param command
     */
    public static void  execCommand(final String command){
        if (process != null) {
            process.destroy();
        }
        try {
            process=Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    public static boolean  execCommand(final String command){
        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            process.destroy();
            process = null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }*/

    /**
     * 将要执行的批处理命令写入到目标bat文件中
     *
     * @param command
     * @param batFilePath
     */
    public static void creatBAT(final String command, final String batFilePath) {
        try {
            File f = new File(batFilePath);
            // 定义编码
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "GBK");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(command);
            writer.close();
            write.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 打开命令窗口.
     */
    public static final String COMMAND_K = "cmd /k start ";

    /**
     * 不打开命令窗口.
     */
    public static final String COMMAND_C = "cmd /c start ";

    /**
     * @param args
     */
//    public static void main(final String[] args) {
//        String batFilePath = "F:\\mapp\\installtemple\\test\\test.bat";
//        String content = "d:\ncd D:\\Program Files (x86)\\Inno Setup 5\n  Compil32 /cc  F:\\mapp\\installtemple\\test\\template.iss";
//        BATExecutorUtil.creatBAT(content, batFilePath);
//        BATExecutorUtil.execBATNoConsole(batFilePath);
//
//    }

}
