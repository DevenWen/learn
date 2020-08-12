package com.qpm.learn.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kangqiang.w
 * @Date 2020/8/12
 **/
@Slf4j
public class FTPUtil {

    private static String ftpHost;

    private static String userName;

    private static String password;

    private static String saveDirectory;

    /**
     * 获取FTPClient对象
     * @return FTPClient
     */
    public static FTPClient getFTPClient() {
        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftp.connect(ftpHost);
            // 设置用户名和密码
            ftp.login(userName, password);
            // 设置连接超时时间,10秒
            ftp.setConnectTimeout(10000);
            // 设置中文编码集，防止中文乱码
            ftp.setControlEncoding("UTF-8");
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                log.info("FTP连接失败");
            } else {
                log.info("FTP连接成功");
            }
        } catch (Exception e) {
            log.error("FTP连接失败");
            log.error(e.getMessage(), e);
        }
        return ftp;
    }

    /**
     * 上传文件到FTP
     * @param ftpClient FTPClient
     * @param srcFile 源文件
     * @param saveName 保存的文件名
     * @return boolean 是否上传成功
     */
    public static boolean upload(FTPClient ftpClient, File srcFile, String saveName, boolean isCloseConnectionAfterUpload){
        boolean flag = false;
        try (FileInputStream fis = new FileInputStream(srcFile);) {
            long startTime = System.currentTimeMillis();
            // 设置PassiveMode传输
            ftpClient.enterLocalPassiveMode();
            ftpClient.setBufferSize(1024);
            //设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //判断FPT目标文件夹时候存在不存在则创建
            if(!ftpClient.changeWorkingDirectory(saveDirectory)){
                ftpClient.makeDirectory(saveDirectory);
                //跳转目标目录
                ftpClient.changeWorkingDirectory(saveDirectory);
            }
            saveName = new String(saveName.getBytes("GBK"),"iso-8859-1");
            flag = ftpClient.storeFile(saveName,fis);
            log.info("文件上传结果:{},耗时:{}",flag,(System.currentTimeMillis()-startTime));
        } catch (Exception e) {
            log.error("文件上传失败:{}",saveName);
            log.error(e.getMessage(), e);
        } finally {
            if (isCloseConnectionAfterUpload) {
                closeFTP(ftpClient);
            }
        }
        return flag;
    }

    public static void closeFTP(FTPClient ftp){
        try {
            ftp.logout();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }finally{
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    log.error("FTP关闭失败");
                }
            }
        }
    }

}
