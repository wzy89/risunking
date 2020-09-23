package com.wzy.risunking.storage.service;

import com.wzy.risunking.global.config.Config;
import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;

@Service
public class FileUpDownLoadService {
    private Logger logger = LoggerFactory.getLogger(FileUpDownLoadService.class);

    /**
     * 文件下载
     *
     * @param req
     * @param resp
     * @throws CommandException
     */
    public void download(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        resp.setCharacterEncoding("UTF-8");
        String fileName = req.getParameter("fileName");
        String fileSavePath = Config.getFileDirWithFileName(fileName);
        File file = new File(fileSavePath + fileName);
        if (!file.exists()) {
            logger.error("文件路径：" + fileSavePath + fileName);
            throw new CommandException(Response.PARAM_ERROR, "文件不存在！");
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("下载：UTF-8编码失败，可能出现乱码！");
        }
        ServletOutputStream os;
        try {
            os = resp.getOutputStream();
        } catch (IOException e) {
            throw new CommandException(Response.INNER_ERROR, "获取输出流失败！");
        }
        BufferedOutputStream bos = new BufferedOutputStream(os);
        InputStream is = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            is = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[4 * 1024];
            int read = 0;
            while ((read = is.read(buffer)) != -1) {
                bos.write(buffer, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("需要下载的文件读取失败！");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                bos.close();
                os.close();
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("需要下载的文件关闭失败或者输出流关闭失败！");
            }
        }
    }

    /**
     * 文件上传
     *
     * @param req
     * @param resp
     * @throws CommandException
     */
    public void saveUploadFile(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (IOException e) {
            logger.error("上传：UTF-8编码失败，可能出现乱码！");
        }
        String saveFileName = UUID.randomUUID().toString();
        String saveFilePath = Config.getFileDirWithFileName(saveFileName);
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String result = "";
        try {
            StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) req;
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                MultipartFile mfile = request.getFile(itr.next());
                String name = mfile.getOriginalFilename();
                name = saveFileName + CommonUtils.getDotExtensionName(name);
                result = result + name + ",";
                File f = new File(saveFilePath + name);
                mfile.transferTo(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件写入失败！");
        }
        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(result.length() == 0 ? result : result.substring(0, result.length() - 1));
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件上传成功，但输出返回值失败~");
        }
    }

    /**
     * 链接url下载图片
     *
     * @param urlList
     * @param extensionName 文件扩展名（不带“。”的）
     * @return 返回文件名称，方便下载使用.
     * @author Wangzy
     * @date 2020/4/23 11:27
     */
    public String downloadPicture(String urlList, String extensionName) {
        try {
            URL url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String saveFileName = UUID.randomUUID().toString() + "." + extensionName;
            String saveFilePath = Config.getFileDirWithFileName(saveFileName);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(saveFilePath + saveFileName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
            return saveFileName;
        } catch (MalformedURLException e) {
            logger.error("文件下载失败~");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("文件保存失败~");
            e.printStackTrace();
        }
        return "";
    }
}
