package com.qpm.learn.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * FTP 客户端模板
 * https://blog.csdn.net/yangyangmyself/article/details/84097280?utm_medium=distribute.pc_relevant.none-task-blog-OPENSEARCH-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-OPENSEARCH-2.channel_param
 * @author kangqiang.w
 * @Date 2020/8/12
 **/
@Component
@Slf4j
public class FTPClientTemplate {

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.username}")
    private String userName;
    @Value("${ftp.password}")
    private String password;


    private FTPClient getFTPClient() {
        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftp.connect(host);
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
            log.error("FTP 连接失败", e);
        }
        return ftp;
    }

    private void closeFTP(FTPClient ftp) {
        if (ftp == null) return;
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

    /**
     * 向 FTP 根目录上传文件
     *
     * @param localFile
     * @param newName
     * @return
     */
    public boolean upload(File localFile, String newName) {
        return upload(localFile, newName, "/");
    }

    /**
     * 向 FTP 服务器上传文件
     *
     * @param localFile
     * @param newName
     * @param ftpServerDirPath ftp 服务器目录，假如不存在，则自动创建
     * @return
     */
    public boolean upload(File localFile, String newName, String ftpServerDirPath) {
        boolean success = false;
        FTPClient ftp = getFTPClient();
        try {
            if(!ftp.changeWorkingDirectory(ftpServerDirPath)) {
                ftp.makeDirectory(ftpServerDirPath);
                //跳转目标目录
                ftp.changeWorkingDirectory(ftpServerDirPath);
            }
            success = ftp.storeFile(newName, new FileInputStream(localFile));
            log.info("上传完成");
        } catch (FileNotFoundException e) {
            log.error("文件上传ftp失败", e);
        } catch (IOException e) {
            log.error("文件上传ftp失败", e);
        } finally {
            closeFTP(ftp);
        }
        return success;
    }


    /**
     * 删除 ftp 服务的文件
     * @param remoteFilePath
     * @return
     */
    public boolean deleteFtpServerFile(String remoteFilePath) {
        boolean result = false;
        FTPClient ftp = getFTPClient();
        try {
            result = ftp.deleteFile(remoteFilePath);
        } catch (IOException e) {
            log.error("ftp 删除文件失败", e);
        }
        return result;
    }


}
