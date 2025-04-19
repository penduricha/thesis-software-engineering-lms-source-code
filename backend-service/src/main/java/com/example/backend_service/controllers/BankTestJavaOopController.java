package com.example.backend_service.controllers;

import com.example.backend_service.models.*;
import com.example.backend_service.services.BankTestJavaOopService;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankTestJavaOopController {
    private final BankTestJavaOopService bankTestJavaOopService;


    public BankTestJavaOopController(BankTestJavaOopService bankTestJavaOopService) {
        this.bankTestJavaOopService = bankTestJavaOopService;
    }

    @GetMapping("/test-java-oop/get-list-test-java-oop")
    public ResponseEntity<List<BankTestJavaOop>> getAllBankTestJavaOop() throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.getAllBankTestJavaOop());
    }

    @PostMapping("/test-java-oop/post-test-java-oop")
    public ResponseEntity<BankTestJavaOop> postBankTestJavaOop(@RequestBody BankTestJavaOop bankTestJavaOop)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.addBankTestJavaOop(bankTestJavaOop));
    }

    @PutMapping("/test-java-oop/put-test-java-oop/{bankTestJavaOopID}")
    public ResponseEntity<BankTestJavaOop> putBankTestJavaOop
            (@PathVariable Long bankTestJavaOopID,@RequestBody BankTestJavaOop bankTestJavaOop)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.updateBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID,bankTestJavaOop));
    }

    @GetMapping("/test-java-oop/get-bank-test-java-oop-by-id/{bankTestJavaOopID}")
    public ResponseEntity<BankTestJavaOop> getBankTestJavaOop_By_BankTestJavaOopID
            (@PathVariable Long bankTestJavaOopID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID));
    }

    @GetMapping("/test-java-oop/get-test-java-oop-by-id/{bankTestJavaOopID}")
    public ResponseEntity<BankTestJavaOop> findBankTestJavaOop_By_BankTestJavaOopID
            (@PathVariable Long bankTestJavaOopID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID));
    }

    @DeleteMapping("/test-java-oop/delete-test-java-oop-by-id/{bankTestJavaOopID}")
    public ResponseEntity<Long> deleteBankTestJavaOop_By_BankTestJavaOopID(@PathVariable Long bankTestJavaOopID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.deleteBankTestJavaOop(bankTestJavaOopID));
    }

    @GetMapping("/test-java-oop/get-bank-test-java-oop-id-by-exam-id/{examID}")
    public ResponseEntity<Long> getBankTestJavOopID_By_ExamID(@PathVariable Long examID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.getBankTestJavOopID_By_ExamID(examID));
    }

    @PatchMapping("/test-java-oop/update-bank-test-java-oop-id-by-exam-id/{examID}/{newBankTestJavaOopID}")
    public ResponseEntity<Exam> updateBankTestJavOopID_By_ExamID(@PathVariable Long examID,
                                                                 @PathVariable Long newBankTestJavaOopID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.updateBankTestJavaOop_To_Exam_By_ExamID(examID, newBankTestJavaOopID));
    }

    @GetMapping("/test-java-oop/get-bank-test-java-oop-by-exam-id/{examID}")
    public ResponseEntity<BankTestJavaOop> getBankTestJavaOop_By_ExamID(@PathVariable Long examID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(bankTestJavaOopService.getBankTestJavaOop_By_ExamID(examID));
    }
}
