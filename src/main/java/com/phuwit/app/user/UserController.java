package com.phuwit.app.user;

import com.phuwit.domain.component.PageWrapper;
import com.phuwit.domain.entity.User;
import com.phuwit.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @Secured("ROLE_USER")
    @RequestMapping("/user/user")
    public String user(){
        return "user/user";

    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/admin/user")
    public String admin(){
        return "user/admin";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/list")
    public String list(Model model, Pageable pageable){
        Page<User> pageUserList = userService.getAllUsers(pageable);
        PageWrapper<User> page = new PageWrapper<User>(pageUserList, "/list");
        model.addAttribute("page", page);
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("users", page.getContent());
        return "user/list";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/user/remove/{id}", method = RequestMethod.GET)
    public String removeUser(@PathVariable("id")int user_id){
        userService.removeUser(user_id);
        return "redirect:/list";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
    public String editUser(@PathVariable("id")int id ,@ModelAttribute UserForm userForm){
        System.out.println("update");
        userForm.setId(id);
        userService.update(userForm);
        return "redirect:/list";
    }



}
