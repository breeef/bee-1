package com.steel.test.spi;

import com.steel.bee.attempt.spi.api.Search;

import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by gang.qin on 2015/12/31.
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
