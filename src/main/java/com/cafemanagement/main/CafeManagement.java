package com.cafemanagement.main;

import com.cafemanagement.BLL.AccountBLL;
import com.cafemanagement.GUI.HomeGUI;
import com.cafemanagement.GUI.LoginGUI;
import com.cafemanagement.utils.Day;
import com.cafemanagement.utils.Settings;

public class CafeManagement {
    public static LoginGUI loginGUI;
    public static HomeGUI homeGUI;
    public static Thread threadTime;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Settings.initialize();
        loginGUI = new LoginGUI();
        homeGUI = new HomeGUI(new AccountBLL().searchAccounts("ACCOUNT_ID = 'AC000'").get(0));
        threadTime = new Thread(CafeManagement::updateState);
        threadTime.start();
    }

    public static void updateState() {
        while (true) {
            Day nextDay = new Day().after(1, 0, 0);
            while (true) {
                if (homeGUI == null)
                    return;
                homeGUI.setTime();
                Day today = new Day();
                if (today.equals(nextDay)) {
                    break;
                }
            }
        }
    }

    public static void exit(int status) {
        if (loginGUI != null)
            loginGUI.dispose();
        loginGUI = null;
        if (homeGUI != null)
            homeGUI.dispose();
        homeGUI = null;
        System.exit(status);
    }
}
