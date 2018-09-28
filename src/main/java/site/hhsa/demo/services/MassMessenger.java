package site.hhsa.demo.services;

import site.hhsa.demo.users.models.User;
import site.hhsa.demo.volunteers.models.Volunteer;

import java.util.List;

public class MassMessenger {

    public MassMessenger(List<User> followers, String message, String sid, String token, String phnNum) {
        for(User follower : followers){
            try {
                new SmsSender().SmsSender(follower.getPhnNum(), message, sid, token, phnNum);
            }catch(Exception e){
                continue;
            }
        }
    }
}
