package com.qpm.learn.ftp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/** 
* FTPClientTemplate Tester. 
* 
* @author <Authors name> 
* @since <pre>8月 12, 2020</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FTPClientTemplateTest {

    @Autowired
    FTPClientTemplate ftpClientTemplate;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: upload(File localFile, String newName) 
* 
*/ 
@Test
public void testUpload() throws Exception { 
    File file = new File("testUploadFile.txt");
    boolean success = ftpClientTemplate.upload(file, "testUploadFile_onServer.txt", "/CLEAR");
    // todo 编写断言
    Assert.assertTrue("上传失败", success);
}

@Test
public void testdeleteFtpServerFile() throws Exception {
    boolean result = ftpClientTemplate.deleteFtpServerFile("/CLEAR/testUploadFile_onServer.txt");
    Assert.assertTrue("删除失败", result);
}


/** 
* 
* Method: getFTPClient() 
* 
*/ 
@Test
public void testGetFTPClient() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = FTPClientTemplate.getClass().getMethod("getFTPClient"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
