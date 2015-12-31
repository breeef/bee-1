package com.steel.bee.attempt.spi.impl;

import com.steel.bee.attempt.spi.api.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gang.qin on 2015/12/31.
 */
public class FileSearch implements Search {

    @Override
    public List<String> search(String keyword) {
        List<String> result = new ArrayList<String>();
        result.add("This is file system search for keyword: " + keyword);

        return result;
    }
}
