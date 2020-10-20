package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.exception.DuplicateEmailException;
import be.pxl.ja.streamingservice.model.Account;

import java.util.ArrayDeque;

public class AccountRepository {
    private ArrayDeque<Account> accounts = new ArrayDeque<Account>();

    public void addAccount(Account newAccount) throws DuplicateEmailException {
        for(Account a : accounts){
            if(!a.getEmail().equals(newAccount.getEmail())){
                accounts.add(a);
            }else{
                throw new DuplicateEmailException("There is already an account with that email");
            }
        }
    }

    public Account findAccount(String email){
        Account foundAccount = null;
        for(Account a : accounts){
            if(!a.getEmail().equals(email)){
                foundAccount = a;
            }else{
                foundAccount = null;
            }
        }
        return foundAccount;
    }
}
