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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @Secured("ROLE_USER")
    @ResponseBody
    @RequestMapping("/user/user")
    public String user(){
        return "Only ROLE_USER can access this page";
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @RequestMapping("/admin/user")
    public String admin(){
        return "Only ROLE_AMIN can access this page";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/list")
    public String list(Model model, Pageable pageable){
        Page<User> pageUserList = userService.getAllUsers(pageable);
        PageWrapper<User> page = new PageWrapper<User>(pageUserList, "/list");
        model.addAttribute("page", page);
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
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable("id")int user_id){
        User user = userService.findOne(user_id);
        return "redirect:/list";
    }

}
