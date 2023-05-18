package com.dailynovel.dailynovelapi.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
    public class EmailVerificationService {
        private Map<String, String> verificationCodes = new HashMap<>();
    
        public void saveVerificationCode(String email, String verificationCode) {
            verificationCodes.put(email, verificationCode);
        }
    
        public boolean getVerificationCodeResult(String email,String emailCheckNum) {
            boolean result = false;
            String savedVerificationCode = verificationCodes.get(email);
            System.out.println(verificationCodes.get(email));
            System.out.println(emailCheckNum);
            if(savedVerificationCode != null && savedVerificationCode.equals(emailCheckNum))
                result =true;
            
            System.out.println(result);

            return result;
        }
    
        public void removeVerificationCode(String email) {
            verificationCodes.remove(email);
        }
    }

