package com.sbsft.wslapi.service;

import com.sbsft.wslapi.mapper.ApiMapper;
import com.sbsft.wslapi.utils.DtnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AppService {


    @Autowired
    private ApiMapper apiMapper;
    @Autowired
    private DtnUtil dtnUtil;


    @Transactional
    public int save(HttpServletRequest req) {
        String word = req.getParameter("w");
        int code = 999;
        try {
            apiMapper.insertToWordList(word);
            code = 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public List<Map<String,Object>> list() {
        //get recent word list (order by date desc, limit 5)
        return apiMapper.selectRecentList();
    }

    public String getWinNumbers(String story, int iss) {

        String result = apiMapper.selectDreamNumber(dtnUtil.textCheck(story));
        Map map;
        map = new HashMap<String,Object>();
        map.put("story",story);
        map.put("result",result);
        map.put("iss",iss);
        apiMapper.insertDreamResult(map);
        return result;
    }

    public List<Map<String, Object>> dlist() {
        return apiMapper.selectDreamResultList();
    }
}



	
