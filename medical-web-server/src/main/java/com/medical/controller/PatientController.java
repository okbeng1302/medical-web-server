package com.medical.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.dao.PatientCaseDao;
import com.medical.dao.PatientDao;
import com.medical.domain.Patient;
import com.medical.domain.Patientcase;
import com.medical.domain.PatientcaseTemp;
import com.medical.domain.ResObject;
import com.medical.util.Constant;
import com.medical.util.PageUtil;

@Controller
@RequestMapping("patients")
public class PatientController {

    @Autowired
    PatientDao patientDao;
    @Autowired
    PatientCaseDao patientcaseDao;

    @RequestMapping("/patient_add")
    public ModelAndView new_record() {

        return new ModelAndView("patients/patient_add");

    }

    // post 添加病人信息，同时重定向到添加病例页面
    @PostMapping("/save_patient")
    public ModelAndView savePatient(Patient patient, RedirectAttributes model, HttpSession httpSession) {
        Patient p = patientDao.findPatientByUuid(patient.getUuid());
        model.addAttribute("username", patient.getUsername());
        model.addAttribute("age", patient.getAge());
        model.addAttribute("sex", patient.getSex());
        if (p == null) {
            Date createtime = new Date();
            patient.setCreatetime(createtime);
            patient.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            patientDao.insert(patient);
            model.addAttribute("uuid", patient.getUuid());
            return new ModelAndView("redirect:patient_case");
        } else {
            patientDao.updateAll(patient);
            model.addAttribute("uuid", patient.getUuid());
            return new ModelAndView("redirect:patients_info_0_0_0_" + patient.getUuid());
        }

    }

    // 添加病人后，添加病例
    @RequestMapping("/patient_case")
    public String editPatientCase(@ModelAttribute("username") String username, @ModelAttribute("uuid") String uuid,
            @ModelAttribute("age") String age, @ModelAttribute("sex") String sex, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("uuid", uuid);
        model.addAttribute("age", age);
        model.addAttribute("sex", sex);

        return "patients/patient_illness";
    }

    // 病例列表添加病例
    @RequestMapping("patientcase_add_{uuid}")
    public String addPatientcase(@PathVariable("uuid") String uuid, Model model) {

        Patient p = patientDao.findPatientByUuid(uuid);
        model.addAttribute("username", p.getUsername());
        model.addAttribute("uuid", uuid);
        model.addAttribute("age", p.getAge());
        model.addAttribute("sex", p.getSex());

        return "patients/patient_illness";
    }

    /*
     * @desc:保存病例 重定向 到病人的病例列表
     */
    @PostMapping("/save_patientcase")
    public ModelAndView savePatientcase(PatientcaseTemp patientcaseTemp, RedirectAttributes model,
            HttpSession httpSession) {

        // 病例创建时间
        Date createtime = new Date();

        // 时间转换 字符串转 Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Patientcase patientcase = patientcaseDao.findPatientcaseById1(patientcaseTemp.getId());
        if (patientcase == null) {
            patientcase = new Patientcase();
            patientcase.setCasehis(patientcaseTemp.getCasehis());
            patientcase.setCasename(patientcaseTemp.getCasename());
            patientcase.setCreatetime(createtime);
            patientcase.setDiagnosis(patientcaseTemp.getDiagnosis());
            patientcase.setMaincase(patientcaseTemp.getMaincase());
            try {
                patientcase.setOccurancetime(sdf.parse(patientcaseTemp.getOccurancetime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            patientcase.setRemark(patientcaseTemp.getRemark());
            patientcase.setSuggestion(patientcaseTemp.getSuggestion());
            patientcase.setUuid(patientcaseTemp.getUuid());

            patientcaseDao.insert(patientcase);

            // 更新病人最新病症
            Patient patient = patientDao.findPatientByUuid(patientcaseTemp.getUuid());
            patient.setLastcase(patientcaseTemp.getCasename());
            patientDao.updateLastcase(patient);
        } else {
            patientcase.setCasehis(patientcaseTemp.getCasehis());
            patientcase.setCasename(patientcaseTemp.getCasename());
            patientcase.setCreatetime(createtime);
            patientcase.setDiagnosis(patientcaseTemp.getDiagnosis());
            patientcase.setMaincase(patientcaseTemp.getMaincase());
            try {
                patientcase.setOccurancetime(sdf.parse(patientcaseTemp.getOccurancetime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            patientcase.setRemark(patientcaseTemp.getRemark());
            patientcase.setSuggestion(patientcaseTemp.getSuggestion());
            patientcase.setUuid(patientcaseTemp.getUuid());
            patientcaseDao.updateAll(patientcase);
        }

        model.addAttribute("uuid", patientcaseTemp.getUuid());

        return new ModelAndView("redirect:patientsinfo");
    }

    // 返回 病人的病例列表
    @RequestMapping("/patientsinfo")
    public String getPatientInfo(@ModelAttribute("uuid") String uuid, Model model) {

        Patient patient = patientDao.findPatientByUuid(uuid);

        List<Patientcase> list = patientcaseDao.findPatientcaseByUuid(uuid);
        model.addAttribute(patient);
        model.addAttribute("list", list);
        return "redirect:patients_info_0_0_0_" + uuid;
    }

    // 查看病人列表信息
    @RequestMapping("/patientsinfo_{uuid}")
    public String getPatientcaseInfo(@PathVariable("uuid") String uuid, Model model) {
        Patient patient = patientDao.findPatientByUuid(uuid);

        List<Patientcase> list = patientcaseDao.findPatientcaseByUuid(uuid);
        model.addAttribute(patient);
        model.addAttribute("list", list);
        return "patients/patient_info";
    }

    // 编辑病人信息
    @RequestMapping("patient_edit_{id}")
    public ModelAndView editPatient(Patient patient, @PathVariable("id") int id, Model model) {

        Patient p = patientDao.findUuidById(patient);
        model.addAttribute("patient", p);

        return new ModelAndView("patients/patient_edit");
    }

    // 编辑病例信息
    @RequestMapping("patientcase_edit_{id}_{uuid}")
    public ModelAndView editPatientcase(Patientcase patientcase, @PathVariable("id") int id,
            @PathVariable("uuid") String uuid, Model model) {

        Patient patient = patientDao.findPatientByUuid(uuid);
        model.addAttribute("username", patient.getUsername());
        model.addAttribute("uuid", uuid);
        model.addAttribute("age", patient.getAge());
        model.addAttribute("sex", patient.getSex());
        Patientcase p = patientcaseDao.findPatientcaseById(patientcase);
        model.addAttribute("patientcase", p);
        return new ModelAndView("patients/patientcase_edit");
    }
    // 删除病人

    @ResponseBody
    @PostMapping("/patient_delete")
    public ResObject<Object> delPatient(Patient patient) {
        Patient p = patientDao.findUuidById(patient);
        patientcaseDao.deleteByUuid(p.getUuid());
        patientDao.deleteById(patient);
        return new ResObject<>(Constant.Code01, Constant.Msg01, null, null);
    }

    // 删除病人

    @ResponseBody
    @PostMapping("/patientcase_delete")
    public ResObject<Object> delPatientcase(Patientcase patientcase) {
        patientcaseDao.deleteById(patientcase);
        return new ResObject<>(Constant.Code01, Constant.Msg01, null, null);
    }

    // 翻页
    @RequestMapping("/patient_list_{pageCurrent}_{pageSize}_{pageCount}")
    public String getPatientList(Patient patient, @PathVariable Integer pageCurrent, @PathVariable Integer pageSize,
            @PathVariable Integer pageCount, Model model) {

        if (patient.getUsername() == null) {
            patient.setUsername("");
        }
        // 判断
        if (pageSize == 0)
            pageSize = 10;
        if (pageCurrent == 0)
            pageCurrent = 1;
        int rows = patientDao.count(patient);
        if (pageCount == 0)
            pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;

        // 查询
        patient.setStart((pageCurrent - 1) * pageSize);
        patient.setEnd(pageSize);
        if (patient.getOrderBy() == null) {
            patient.setOrderBy(Constant.OrderByAddDateDesc);
        }
        List<Patient> patientList = patientDao.list(patient);

        model.addAttribute("list", patientList);
        String pageHTML = PageUtil.getPageContent(
                "patient_list_{pageCurrent}_{pageSize}_{pageCount}?username=" + patient.getUsername(), pageCurrent,
                pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("patient", patient);
        return "patients/patientsList";
    }

    @RequestMapping("/patients_info_{pageCurrent}_{pageSize}_{pageCount}_{uuid}")
    public String getPatientcase(Patientcase patientcase, @PathVariable("pageCurrent") Integer pageCurrent,
            @PathVariable("pageSize") Integer pageSize, @PathVariable("pageCount") Integer pageCount,
            @PathVariable("uuid") String uuid, Model model) {
        patientcase.setUuid(uuid);
        Patient patient = patientDao.findPatientByUuid(uuid);
        // 判断
        if (pageSize == 0)
            pageSize = 10;
        if (pageCurrent == 0)
            pageCurrent = 1;
        int rows = patientcaseDao.count(patientcase);
        if (pageCount == 0)
            pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        // 查询
        patientcase.setStart((pageCurrent - 1) * pageSize);
        patientcase.setEnd(pageSize);
        if (patientcase.getOrderBy() == null) {
            patientcase.setOrderBy(Constant.OrderByCreatetimeDesc);
        }
        List<Patientcase> patientcaseList = patientcaseDao.list(patientcase);
        model.addAttribute("uuid", uuid);

        model.addAttribute("list", patientcaseList);
        String pageHTML = PageUtil.getPageContent("patientsinfo_" + uuid + "_{pageCurrent}_{pageSize}_{pageCount}"
                + "&orderBy=" + patientcase.getOrderBy(), pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("patient", patient);
        return "patients/patient_info";
    }
}
