package com.example.lurenjiaspring.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MybatisManyParam {
    private String userName;
    private String age;
    private String appName;
    private Integer aId;
    private String tableName;


}
