package com.slabtech.ttapplication.Dao;

import com.slabtech.ttapplication.Entity.Society;

import java.util.List;

public interface SocietyDao {
    List<Society> findAllSociety();
    Society findBySocietyName(String userName);

    Society findSocietyById(int theId);

    void deleteSocietyById(int theId);

    Society saveSociety(Society theSociety);
}
