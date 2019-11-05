package com.dam.reto2dam;

import java.util.logging.Logger;

import thebestprogramlogiclibrary.Message;
import thebestprogramlogiclibrary.User;
import thebestprogramlogiclibrary.logic.ApplicationLogicImplementation;

public class ClientThread extends Thread {

    private String action;
    private Message message;
    private User user;
    private ApplicationLogicImplementation appLogic;
    private static final Logger LOG = Logger.getLogger(ClientThread.class.getName());

    public void setAppLogic(ApplicationLogicImplementation appLogic) {
        this.appLogic = appLogic;
    }

    public void setMessage(String action) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public Message getMessage() {
        return message;
    }

    @Override
    public synchronized void run() {
        try {
            message = new Message();
            switch (action) {
                case "SIGNIN":
                    message.setContent(appLogic.registerUser(user));
                    break;
                case "LOGIN":
                    message.setContent(appLogic.logIn(user));
                    break;
            }
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }

    }
}
