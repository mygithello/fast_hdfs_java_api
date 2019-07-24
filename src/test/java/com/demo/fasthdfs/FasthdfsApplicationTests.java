package com.demo.fasthdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FasthdfsApplicationTests {

    @Autowired
    private FastFileStorageClient storageClient;

    // 文件上传
    @Test
    public void contextLoads() throws FileNotFoundException {
        File file = new File("E:\\aa.png");
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = storageClient.uploadFile(inputStream,
                file.length(), "png", null);
        System.out.println(storePath.getGroup() + " | " +storePath.getPath());
    }
    /**
     * 文件下载
     * @throws IOException
     */
    @Test
    public void testDownload() throws IOException {
        byte[] b = storageClient.downloadFile("group1",
                "M00/00/00/wKgriFr1bX-AG3JlABr-dch7j3Q095.png", new DownloadByteArray());
        FileOutputStream fileOutputStream = new
                FileOutputStream("E:\\2.png");
        fileOutputStream.write(b);
        fileOutputStream.close();
    }

}
