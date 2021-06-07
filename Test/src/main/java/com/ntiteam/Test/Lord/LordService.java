package com.ntiteam.Test.Lord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LordService {
    private final LordRepository lordRepository;

    @Autowired
    public LordService(LordRepository lordRepository){
        this.lordRepository = lordRepository;
    }

    public LordRepository getLordRepository(){
        return lordRepository;
    }

    public List<Lord> getLords() {
        return lordRepository.findAll();
    }

    public Lord createLord(Lord lord) {
        lordRepository.save(lord);

        return lord;
    }

    public Lord getLordById(Long id){
        if (!lordRepository.findById(id).isPresent()){
            throw new IllegalStateException("Lord with id " + id + " doesn't exist");
        }
        return lordRepository.findById(id).get();
    }

    public List<Lord> getBezdelniki() {
        ArrayList<Lord> list = new ArrayList<>();
        for(Lord lord: lordRepository.findAll()){
            if (lord.getPlanets().size() == 0){
                //Adding lord with empty list planets to list
                list.add(lord);
            }
        }

        return list;
    }

    public List<Lord> getTenYoungestLords() {
        return lordRepository.findTop10ByOrderByAgeAsc();
    }
}
