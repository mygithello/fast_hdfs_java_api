package com.demo.fasthdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by Administrator on 2018/5/10.
 */
public class FastDFSJavaApiTest {
    StorageClient client = null;
/**
 * 单元测试方法之前执行
 *
 * @throws IOException
 * @throws MyException
 */
@Before
public void before() throws IOException, MyException {
    // 加载配置文件
    ClientGlobal.init("fdfs_client.conf");
    TrackerClient trackerClient = new TrackerClient();
    TrackerServer trackerServer = trackerClient.getConnection();
    // 通过client对象操作分布式文件系统
    client = new StorageClient(trackerServer, null);
}
    /**
     * 测试文件上传
     */
    @Test
    public void testUpload() throws IOException, MyException {
        // 参数一：本地文件路径 参数二: 文件的扩展名 参数三：元数据列表
        //String[] str =
        client.upload_file("C:\\Users\\\\jiayachong\\Desktop\\1.png", "png",
                null);
        String[] str =
                client.upload_file("C:\\Users\\\\jiayachong\\Desktop\\1.png", "png",
                        new NameValuePair[]{new NameValuePair("width","1080"),new
                                NameValuePair("author","jyc")}
                );
        for (String s : str) {
            System.out.println(s);
        }
    }
    /**
     * 文件下载
     * @throws IOException
     * @throws MyException
     */
    @Test
    public void testDownload() throws IOException, MyException {
        byte[] b = client.download_file("group1",
                "M00/00/00/wKgqgl04ACmAJE29AAAOF0QN2hg876.png");
        FileOutputStream fileOutputStream = new
                FileOutputStream("d:\\aa.png");
        fileOutputStream.write(b);
        fileOutputStream.close();
    }
    /**
     * 文件删除
     * @throws IOException
     * @throws MyException
     */
    @Test
    public void testDelete() throws IOException, MyException {
        client.delete_file("group1", "M00/00/00/wKgriFr0ebmAI1gBABr�dch7j3Q224.png");
    }
    @Test
    public void testGetFileInfo() throws IOException, MyException {
        FileInfo fileInfo = client.get_file_info("group1",
                "M00/00/00/wKgqgl04ACmAJE29AAAOF0QN2hg876.png");
        System.out.println(fileInfo.getCrc32() + " | "
                +fileInfo.getCreateTimestamp() + " | "+fileInfo.getFileSize());
    }
    @Test
    public void testGetMetadata() throws IOException, MyException {
        try {
            NameValuePair[] nameValuePairs =
                    client.get_metadata("group1", "M00/00/00/wKgqgl04ACmAJE29AAAOF0QN2hg876.png");
            for (NameValuePair nameValuePair : nameValuePairs) {
                System.out.println(nameValuePair.getName()
                        +"|"+nameValuePair.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}