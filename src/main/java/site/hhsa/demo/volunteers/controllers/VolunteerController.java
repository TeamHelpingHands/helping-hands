package site.hhsa.demo.volunteers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.organizations.repositories.CategoryRepo;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

import java.util.Date;

@Controller
public class VolunteerController {

    VolunteerRepo volunteerDao;
    VolunteerDetailsRepo volunteerDetailsDao;
    CategoryRepo categoryDao;

    public VolunteerController(VolunteerRepo volunteerDao, VolunteerDetailsRepo volunteerDetailsDao, CategoryRepo categoryDao) {
        this.volunteerDao = volunteerDao;
        this.volunteerDetailsDao = volunteerDetailsDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/vol/register")
    public String register(Model viewModel) {
        viewModel.addAttribute("volunteer", new Volunteer());
        return "volunteers/register";
    }

    @PostMapping("/vol/register")
    public String registerPost(@ModelAttribute Volunteer vol) {
        Date date = new Date();
        vol.setDateCreated(date.toString());
        vol.setAdmin(false);
        vol.setSuspended(false);
        volunteerDao.save(vol);
        return "redirect:/vol/profile/" + vol.getUsername();
    }

    @GetMapping("/vol/profile/{username}")
    public String volProfile(@PathVariable String username, Model viewModel) {
        Volunteer vol = volunteerDao.findByUsername(username);
        viewModel.addAttribute("vol", vol);
        return "volunteers/profile";
    }

    @GetMapping("/vol/login")
    public String volLogin(){
        return "volunteers/login";
    }

    @GetMapping("/vol/{id}/details/create")
    public String createVolunteerDetails(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("id", id);
        viewModel.addAttribute("volunteerDetails", new VolunteerDetails());
        return "volunteers/details-create";
    }

    @PostMapping("/vol/{id}/details/create")
    public String insertVolunteerDetails(@PathVariable long id, @ModelAttribute VolunteerDetails volunteerDetails){
        volunteerDetails.setVolunteer(volunteerDao.findOne(id));
        volunteerDetailsDao.save(volunteerDetails);
        return "redirect:/vol/profile/" + volunteerDetails.getVolunteer().getUsername();
    }


}
