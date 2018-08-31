package com.heeexy.example.dto.transformer;

import com.heeexy.example.dto.RunDataDto;
import com.heeexy.example.entity.RunningData;

public class RunDataTransformer implements DomainObjectTransformer<RunDataDto,RunningData>{
    @Override
    public RunDataDto toDto(RunningData domain) {
        return null;
    }

    @Override
    public RunningData toDomain(RunDataDto dto) {
        //RunningData runningData = new RunningData();
        return null;
    }
}
