package com.zwyue.controller.studymeet;

import com.zwyue.common.BaseController;
import com.zwyue.entity.Student;
import com.zwyue.entity.StudyMeet;
import com.zwyue.service.StudentService;
import com.zwyue.service.StudyMeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 学委会管理
 * @author yangli
 * @date 2018/12/25
 */
@Controller
@RequestMapping("/studymeet")
public class StudyMeetController extends BaseController {

    @Autowired
    private StudyMeetService studyMeetService;

    @Autowired
    private StudentService studentService;

    /**
     * 查询学委会学生列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public List<StudyMeet> list(Model model){
        List<StudyMeet> list = studyMeetService.getList();
        for (int i = 0; i < list.size(); i++) {
            System.out.print("------1-----"+list.get(i).getSname());
        }
        return list;
    }

    /**
     * 查询未加入学委会的学生列表
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "slist",method = RequestMethod.GET)
    public List<Student> slist(Model model){
        List<Student> list = studentService.getStudentList();
        for (int i = 0; i < list.size(); i++) {
            System.out.print("------2-----"+list.get(i).getStuname());
        }
        return list;
    }

    /**
     * 删除学委会学生
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String delete(Integer id){
        studyMeetService.deleteByPrimaryKey(id);
        return "redirect:/studymeet/list";
    }

    /**
     * 学委会成员新增
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Integer sid){
        Student student = studentService.selectByPrimaryKey(sid);
        StudyMeet studyMeet = new StudyMeet();
        studyMeet.setSid(student.getId());
        studyMeet.setSname(student.getStuname());
        studyMeet.setClassid(student.getClassid());
        studyMeet.setClassname(student.getClassname());
        studyMeet.setPhone(student.getPhone());
        studyMeet.setEmergency(student.getEmergency());
        studyMeetService.insert(studyMeet);
        return "{ \"msg\":\"success\"}";
    }
    
    /**
     * 学委会添加职务
     * @author yangli
     * @date 2018/12/25
     */
    @RequestMapping(value = "addduties",method = RequestMethod.POST)
    @ResponseBody
    public String addduties(StudyMeet studyMeet,Integer id){
        studyMeetService.updateByPrimaryKey(studyMeet);
        return "{ \"msg\":\"success\"}";
    }

}
