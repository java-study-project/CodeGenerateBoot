package com.code.generate;

import com.code.generate.utils.FileUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class GenerateApplicationTests {

    @Test
    public void contextLoads() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:template/home/base");
        FileUtils.getChildFiles(true, file);
    }

}
