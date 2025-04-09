package com.example.backend_service.services;

import com.example.backend_service.models.BankTestJavaOop;
import com.example.backend_service.repositories.BankTestJavaOopRepository;
import com.example.backend_service.services.i_service.I_BankTestJavaOopService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankTestJavaOopService implements I_BankTestJavaOopService {
    @PersistenceContext
    private EntityManager entityManager;

    private final BankTestJavaOopRepository bankTestJavaOopRepository;

    public BankTestJavaOopService(BankTestJavaOopRepository bankTestJavaOopRepository) {
        this.bankTestJavaOopRepository = bankTestJavaOopRepository;
    }

    @Override
    public BankTestJavaOop addBankTestJavaOop(BankTestJavaOop bankTestJavaOop) throws JpaSystemException {
        return bankTestJavaOopRepository.save(bankTestJavaOop);
    }

    @Override
    public List<BankTestJavaOop> getAllBankTestJavaOop() throws JpaSystemException {
        return bankTestJavaOopRepository.findAll();
    }

    @Override
    public Long deleteBankTestJavaOop(Long bankTestJavaOopID) throws JpaSystemException {
        //xet them record lien quan nua;
        return 0L;
    }

    @Override
    public BankTestJavaOop findBankTestJavaOop_By_BankTestJavaOopID(Long bankTestJavaOopID) throws JpaSystemException {
        return bankTestJavaOopRepository.findBankTestJavaOopByBankTestJavaOopID(bankTestJavaOopID);
    }

    @Override
    public BankTestJavaOop updateBankTestJavaOop_By_BankTestJavaOopID(Long bankTestJavaOopID, BankTestJavaOop bankTestJavaOop)
            throws JpaSystemException {
        BankTestJavaOop bankTestJavaOopFound = findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID);
        if(bankTestJavaOopFound != null) {
            bankTestJavaOopFound.setNameTest(bankTestJavaOop.getNameTest());
            bankTestJavaOopFound.setDescriptionTest(bankTestJavaOop.getDescriptionTest());
            bankTestJavaOopFound.setImageDiagram(bankTestJavaOop.getImageDiagram());
            return bankTestJavaOopRepository.save(bankTestJavaOopFound);
        }
        return null;
    }
}
