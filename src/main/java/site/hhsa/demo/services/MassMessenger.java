package site.hhsa.demo.services;

import site.hhsa.demo.users.models.User;
import site.hhsa.demo.volunteers.models.Volunteer;

import java.util.List;

public class MassMessenger {

    public MassMessenger(List<Volunteer> followers, String message) {
        for(Volunteer follower : followers){
            new SmsSender().SmsSender(follower.getUser().getPhnNum(),message);
        }
    }
}
