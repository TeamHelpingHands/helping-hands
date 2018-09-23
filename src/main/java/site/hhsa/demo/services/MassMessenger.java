package site.hhsa.demo.services;

import site.hhsa.demo.users.models.User;

import java.util.List;

public class MassMessenger {

    public MassMessenger(List<User> followers, String message) {
        for(User follower : followers){
            new SmsSender().SmsSender(follower.getPhnNum(),message);
        }
    }
}
