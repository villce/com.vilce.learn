import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.VueApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: PACKAGE_NAME.LogTest
 * @Author: 雷才哲
 * @Date: 2020/9/1 19:29
 * @Version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VueApplication.class)
public class LogTest {

    @Test
    public void test(){
        List<String> a = new ArrayList<>();
        a.add("a1");
        a.add("a2");
        a.add("a3");
        System.out.println(JSONUtils.toJsonPretty(a));
        List<String> b = new ArrayList<>();
        b.add("b1");
        b.add("b2");
        System.out.println(JSONUtils.toJsonPretty(b));
        if (true) {
            List<String> exchange = a;
            a = b;
            b = exchange;
        }
        System.out.println(JSONUtils.toJsonPretty(a));
        System.out.println(JSONUtils.toJsonPretty(b));
    }
}
