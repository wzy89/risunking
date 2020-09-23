package com.wzy.risunking.utils;

import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;

import java.io.*;

/**
 * CommonUtils
 *
 * @author Wangzy
 * @date 2020/9/15 10:05
 */
public final class CommonUtils {
    /**
     * 根据文件名获取文件类型后缀
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1).toLowerCase();
            }
        }
        return "";
    }

    /**
     * 根据文件名获取文件类型后缀(带".")
     *
     * @param filename
     * @return
     */
    public static String getDotExtensionName(String filename) {
        String extensionName = getExtensionName(filename);
        if (extensionName == null || "".equals(extensionName)) {
            return "";
        }
        return "." + extensionName;
    }

    /**
     * 读取文件中的文本
     *
     * @param filePath
     * @return java.lang.String
     * @author Wangzy
     * @date 2020/9/23 11:46
     */
    public static String readStringFromFile(String filePath) throws CommandException {
        StringBuffer result = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line != null){
                // 处理第一行，不添加回车
                result.append(line);
                // 读取下一行添加“\n”
                line = bufferedReader.readLine();
                while (line != null) {
                    result.append("\n");
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            }
            bufferedReader.close();
            fileReader.close();
            return result.toString();
        } catch (Exception e) {
            throw new CommandException(Response.INNER_ERROR, "读取文件失败！");
        }
    }

    /**
     * 在文件写入字符串
     *
     * @param filePath 文件路径
     * @param append   是否追加
     * @return boolean
     * @author Wangzy
     * @date 2020/9/15 10:16
     */
    public static boolean writeStringToFile(String filePath, String writeStr, boolean append) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
            printWriter.println(writeStr);
            printWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在文件写入字符串
     *
     * @param filePath
     * @return boolean
     * @author Wangzy
     * @date 2020/9/15 10:16
     */
    public static boolean writeStringToFile(String filePath, String writeStr) {
        return writeStringToFile(filePath, writeStr, false);
    }
}
