package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * �������ļ���ִ����.<br>
 * @author RuiQiYou <br>
 * @version 1.0.0 2020��2��20��<br>
 * @see
 * @since JDK 1.8.0
 */
public class BATExecutorUtil {

    /**
     * java����.
     */
    public static Process process = null;

    /**
     * ���ڴ��ڵ�ģʽִ��bat�ļ�.
     * @param batFilePath bat�ļ��ľ���·��
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
     * �޴��ڵ�ģʽִ��bat�ļ�.
     * @param batFilePath bat�ļ��ľ���·��
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
     * ִ������
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
     * ��Ҫִ�е�����������д�뵽Ŀ��bat�ļ���
     *
     * @param command
     * @param batFilePath
     */
    public static void creatBAT(final String command, final String batFilePath) {
        try {
            File f = new File(batFilePath);
            // �������
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "GBK");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(command);
            writer.close();
            write.close();
        } catch (Exception e) {
            System.out.println("д�ļ����ݲ�������");
            e.printStackTrace();
        }
    }

    /**
     * �������.
     */
    public static final String COMMAND_K = "cmd /k start ";

    /**
     * ���������.
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
