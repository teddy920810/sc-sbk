package com.heeexy.example.util;

import com.heeexy.example.entity.RunningData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        List<RunningData> runningDatas = new ArrayList<>();
        RunningData runningData1 = new RunningData(LocalDateTime.now(),new Double(5.6),"iii",new Double(7.6),44l);
        RunningData runningData2 = new RunningData(LocalDateTime.now(),new Double(5.7),"iii",new Double(7.6),44l);
        RunningData runningData3 = new RunningData(LocalDateTime.now(),new Double(5.8),"iii",new Double(7.6),44l);
        RunningData runningData4 = new RunningData(LocalDateTime.now(),new Double(6.0),"iii",new Double(7.6),44l);
        runningDatas.add(runningData1);
        runningDatas.add(runningData2);
        runningDatas.add(runningData3);
        runningDatas.add(runningData4);

        Double avg  = runningDatas.stream().mapToDouble(RunningData::getSpeed).average().getAsDouble();
        System.out.println(avg);

    }

}
