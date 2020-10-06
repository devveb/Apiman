package com.sbsft.wslapi.batch;

import com.sbsft.wslapi.model.NumSet;
import com.sbsft.wslapi.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryCron {

    @Autowired
    LotteryService lotteryService;

    @Scheduled(cron = "0 0 21 * * sat")
    private void getWinningNumbers(){
        // TODO: 2020/08/20
        // 1. get last draw and present draw num
        // 2. get draw result and insert

        int maxDraw = lotteryService.getMaxDraw();
        int presentDraw = (int) lotteryService.getPresentDraw().get("present");

        if(maxDraw < presentDraw){
            for(int i = maxDraw+1;i<=presentDraw;i++ ){
                NumSet ns = lotteryService.callWinNumApi(i);
                lotteryService.insertDrawHistory(ns);
            }
        }
    }

    @Scheduled(cron = "0 30 21 * * sat")
    private void getTargetDraw(){
        // TODO: 2020/08/20
        // 1. get last draw and present draw num
        // 2. get draw result and insert

        int presentDraw = lotteryService.getPresentDraw().get("present");

    }

//    @Scheduled(cron = "0 0 22 * * *")
//    private void generateDrawPlaceResult(){
//        // TODO: 2020/08/20
//        // 1. get suggestions that not matched yet
//        List<DreamStory> suggestionList = lotteryService.getUnpackedSuggestionList();
//        for(DreamStory ds:suggestionList){
//            lotteryService.checkHistory(ds);
//        }
//    }
}