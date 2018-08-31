/*
 * DomainObjectTransformer.java  * Created on  8/1/2018
 * Copyright (c) 2018 BrightSoft
 * BrightSoft TECHNOLOGY CO.,LTD. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * BrightSoft Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with BrightSoft Ltd.
 */
package com.heeexy.example.dto.transformer;

/**
 * @author Wuhui Zhang
 * Defined how to transform dto to domain object
 */

import com.heeexy.example.dto.DtoObject;
import com.heeexy.example.entity.DomainObject;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface DomainObjectTransformer<T extends DtoObject, D extends DomainObject> {
    T toDto(D domain);

    D toDomain(T dto);

    default List<T> toDtos(List<D> domains) {
        if(CollectionUtils.isEmpty(domains)) {
            return Collections.emptyList();
        }
        return domains.stream().map(this::toDto).collect(Collectors.toList());
    }
}

