package com.snapmatic.auth.dao;

import com.snapmatic.auth.dto.ConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ConfigDao {

    List<ConfigDTO> configList;

    @Autowired
    public ConfigDao(List<ConfigDTO> list){
        this.configList=list;
    }

    public List<ConfigDTO> fetchAllProperties(){
        return configList;
    }
}
