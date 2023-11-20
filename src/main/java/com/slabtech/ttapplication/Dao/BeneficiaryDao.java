package com.slabtech.ttapplication.Dao;
import com.slabtech.ttapplication.Entity.Beneficiary;

import java.util.List;

public interface BeneficiaryDao {

    List<Beneficiary> findAllBeneficiary();

    Beneficiary findFirstByOrderByUdDesc();
    Beneficiary findByBeneficiaryName(String userName);

    Beneficiary findBeneficiaryById(int theId);

    void deleteBeneficiaryById(int theId);

    Beneficiary saveBeneficiary(Beneficiary theBeneficiary);
}
