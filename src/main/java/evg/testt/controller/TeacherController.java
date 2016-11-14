package evg.testt.controller;

//INSERT INTO hibernate_sequence(next_val) VALUE (0);

import evg.testt.dto.TeacherDTO;
import evg.testt.model.Person;
import evg.testt.model.Role;
import evg.testt.model.Teacher;
import evg.testt.model.User;
import evg.testt.oval.SpringOvalValidator;
import evg.testt.service.*;
import evg.testt.util.JspPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    SpringOvalValidator validator;

    @Autowired
    ManagerService managerService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PersonService personService;

    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ModelAndView showTeachers() {
        List<Teacher> teachers = Collections.EMPTY_LIST;

        try {
            teachers = teacherService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ModelAndView(JspPath.TEACHER_ALL, "teachers", teachers);
    }

    @RequestMapping(value = "/teacherAdd")
    public ModelAndView addTeacher(Model model) {
        TeacherDTO teacher =  new TeacherDTO();
        model.addAttribute("teacher", teacher);
        return new ModelAndView(JspPath.TEACHER_ADD);
    }

    @RequestMapping(value = "/teacherSave", method = RequestMethod.POST)
    public ModelAndView saveTeacher(@ModelAttribute("teacher") @Validated TeacherDTO teacherDto, BindingResult bindingResult) throws SQLException {
        validator.validate(teacherDto, bindingResult);

        // проверка логина на уникальность
        User u = userService.findByUserLogin(teacherDto.getLogin());
        if (u != null)
            bindingResult.rejectValue("login", "1", "Login already exist.");

        if (!bindingResult.hasErrors()) {
            Role role = roleService.getById(3);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            User newUser = new User();
//                newUser.setEmail(managerDto.getEmail());
            newUser.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
            newUser.setLogin(teacherDto.getLogin());
//                newUser.setIsFirstLogin("true");

            Person newPerson = new Person();
            newPerson.setFirstName(teacherDto.getFirstName());
            newPerson.setLastName(teacherDto.getLastName());
            newPerson.setMiddleName(teacherDto.getMiddleName());

            newUser.setRole(role);
            newUser.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
            newUser.setLogin(teacherDto.getLogin());

            Teacher newTeacher = new Teacher();

            newTeacher.setPerson(newPerson);
            newTeacher.setUser(newUser);

            teacherService.insert(newTeacher);

            try {
                teacherService.insert(newTeacher);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return showTeachers();
        } else {
            return new ModelAndView(JspPath.TEACHER_ADD);
        }
    }


}
