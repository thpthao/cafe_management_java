package com.cafemanagement.BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cafemanagement.DAL.AccountDAL;
import com.cafemanagement.DTO.Account;

public class AccountBLL extends Manager<Account> {
    private AccountDAL accountDAL;
    private List<Account> accountList;

    public AccountBLL() {
        accountDAL = new AccountDAL();
        accountList = searchAccounts("DELETED = 0", "ACCOUNT_ID != 'AC000'");
    }

    public AccountDAL getAccountDAL() {
        return accountDAL;
    }

    public void setAccountDAL(AccountDAL accountDAL) {
        this.accountDAL = accountDAL;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Object[][] getData() {
        return getData(accountList);
    }

    public boolean addAccount(Account account) {
        accountList.add(account);
        return accountDAL.addAccount(account) != 0;
    }

    public boolean updateAccount(Account account) {
        accountList.set(getIndex(account, "ACCOUNT_ID", accountList), account);
        return accountDAL.updateAccount(account) != 0;
    }

    public boolean updateAccountPassword(Account account) {
        accountList.set(getIndex(account, "ACCOUNT_ID", accountList), account);
        return accountDAL.updateAccountPassword(account) != 0;
    }

    public boolean deleteAccount(Account account) {
        accountList.remove(getIndex(account, "ACCOUNT_ID", accountList));
        return accountDAL.deleteAccount("ACCOUNT_ID = '" + account.getAccountID() + "'") != 0;
    }

    public List<Account> searchAccounts(String... conditions) {
        return accountDAL.searchAccounts(conditions);
    }

    public List<Account> findAccounts(String key, String value) {
        List<Account> list = new ArrayList<>();
        for (Account account : accountList) {
            if (getValueByKey(account, key).toString().toLowerCase().contains(value.toLowerCase())) {
                list.add(account);
            }
        }
        return list;
    }

    public List<Account> findAccountsBy(Map<String, Object> conditions) {
        List<Account> accounts = accountList;
        for (Map.Entry<String, Object> entry : conditions.entrySet())
            accounts = findObjectsBy(entry.getKey(), entry.getValue(), accounts);
        return accounts;
    }

    public boolean exists(Account account) {
        return !findAccountsBy(Map.of(
            "USERNAME", account.getUsername(),
            "STAFF_ID", account.getStaffID()
        )).isEmpty();
    }

    public boolean exists(Map<String, Object> conditions) {
        if (conditions.containsKey("USERNAME") && conditions.get("USERNAME").equals("admin")) {
            return true;
        }
        return !findAccountsBy(conditions).isEmpty();
    }

    public String getAutoID() {
        return getAutoID("AC", 3, searchAccounts());
    }

    @Override
    public Object getValueByKey(Account account, String key) {
        return switch (key) {
            case "ACCOUNT_ID" -> account.getAccountID();
            case "USERNAME" -> account.getUsername();
            case "PASSWD" -> account.getPassword();
            case "STAFF_ID" -> account.getStaffID();
            default -> null;
        };
    }
}
