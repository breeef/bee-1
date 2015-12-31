package com.steel.bee.attempt.spi;

import com.steel.bee.attempt.spi.api.Search;

import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by gang.qin on 2015/12/31.
 * SPI机制约定：
 * 1. 在META-INF/services/目录中创建以接口命名(fullName)的文件，内容为API具体实现的的类名(fullName)
 * 2. 使用ServiceLoader类动态加载META-INF中的实现类
 * 3. API具体实现类必须有一个不带参数的构造方法
 */
public class SearchTest {
    public static void main (String[] args) {
        ServiceLoader<Search> searches = ServiceLoader.load(Search.class);
        for (Search s : searches) {
            List<String> results = s.search("test");
            for (String str : results) {
                System.out.println(str);
            }
        }
    }
}
