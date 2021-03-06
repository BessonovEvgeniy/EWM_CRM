package evg.testt.controller;

import evg.testt.dto.PersonDTO;
import evg.testt.model.*;
//import evg.testt.oval.SpringOvalValidator;
import evg.testt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@PropertySource(value = "classpath:standard.properties")
public class TeacherController {

    @Value("${pagination.page.size}")
    protected int pageSize;
    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PersonService personService;
    @Autowired
    PersonDTOService personDTOService;
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String showTeachers(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Boolean flagSorted,
                               Model model) throws SQLException {

        if (flagSorted == null) flagSorted = false;

        int totalTeachers;
        int pages;
        int currentPage = 1;

        if (page != null && page > 0) {
            currentPage = page;
        }

        totalTeachers = teacherService.count();

        List<Teacher> teachers = Collections.EMPTY_LIST;
        if (!flagSorted) {
            teachers = teacherService.getByPage(currentPage);
        } else {
            teachers = teacherService.getByPageSorted(currentPage);
        }

        pages = ((totalTeachers / pageSize) + 1);

        if (totalTeachers % pageSize == 0) {
            pages--;
        }
        model.addAttribute("teachers", teachers);
        model.addAttribute("pages", pages);
        model.addAttribute("flagSorted", flagSorted);
        return "teachers/all";
    }


    @RequestMapping(value = "/teachers/add")
    public String addTeacher(Model model,
                             HttpServletRequest request) {
        request.getSession().setAttribute("teachers/add", request.getHeader("Referer"));
        PersonDTO person = new PersonDTO();
        model.addAttribute("teacher", person);
        return "teachers/add";
    }

    @RequestMapping(value = "/teachers/save", method = RequestMethod.POST)
    public String saveTeacher(@Valid @ModelAttribute("teacher") PersonDTO personDTO, BindingResult bindingResult,
                              Model model,
                              HttpServletRequest request) throws SQLException, ParseException {

        User u = userService.findByUserLogin(personDTO.getLogin());

        if (u != null)
            bindingResult.rejectValue("login", "1", "Login already exist.");

        if (!bindingResult.hasErrors()) {

            Teacher teacher = new Teacher();
            teacher = personDTOService.updateRegisteredUser(teacher, personDTO);
            teacherService.insert(teacher);

            return "redirect:" + request.getSession().getAttribute("teachers/add").toString();
        } else {
            return "teachers/add";
        }
    }

    @RequestMapping(value = "/teachers/SortByDate", method = RequestMethod.POST)
    public String filterTeachers(Model model) throws SQLException {
        List<Teacher> teachers = teacherService.getSortedByRegistrationDate();
        model.addAttribute("teachers", teachers);
        return "teachers/all";
    }

    @RequestMapping(value = "/teachers/delete")
    public String teacherDelete(@RequestParam Integer id) throws SQLException {
        Teacher teacher = teacherService.getById(id);
        teacherService.delete(teacher);
        return "redirect:/teachers";
    }

    @RequestMapping(value = "/teachers/trash")
    public String teacherTrash(@RequestParam Integer id) throws SQLException {
        Teacher teacher = teacherService.getById(id);
        teacherService.trash(teacher);
        return "redirect:/teachers";
    }

    @RequestMapping(value = "/teachers/info")
    public String teacherInfo(Model model, @RequestParam int teacher_id) throws SQLException {

        Teacher teacher = teacherService.getById(teacher_id);
        List<Group> groups = groupService.getByTeacher(teacher);

        model.addAttribute("teacher", teacher);
        model.addAttribute("groups", groups);

        return "persons/teacher-info";
    }

    @RequestMapping(value = "/teachers/UpdateComments", method = RequestMethod.POST)
    public String studentUpdate(Model model,
                                @RequestParam Integer id,
                                @RequestParam String comments) throws SQLException {
        Teacher teacher = teacherService.getById(id);
        teacher.getPerson().setComments(comments);
        teacherService.update(teacher);
        model.addAttribute("teacher", teacher);

        return "persons/teacher-info";
    }

    @RequestMapping(value = "/teachers/setTeacherLevel")
    public String setTeacherLevel(int level, int teacher_id) throws SQLException {
        Teacher teacher = teacherService.getById(teacher_id);
        TeacherLevelEnum level_Id = TeacherLevelEnum.valueOf(level);
        teacher.setLevel(level_Id);
        teacherService.update(teacher);

        return "redirect:/teachers/info?teacher_id=" + teacher_id;
    }
}