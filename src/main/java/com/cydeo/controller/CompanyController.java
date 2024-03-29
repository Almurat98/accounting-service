package com.cydeo.controller;


import com.cydeo.dto.CompanyDto;
import com.cydeo.service.CompanyService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Controller
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService=companyService;
    }


    @GetMapping("/list")
    public String listAllCompanies(Model model){
        List<CompanyDto> company = companyService.getAllCompany();
        model.addAttribute("companies", company);
        return "/company/company-list";
    }

    @GetMapping("/update/{companyId}")
    public String editCompany(@PathVariable Long companyId, Model model) {
        CompanyDto companyDto = companyService.findById(companyId);
        model.addAttribute("company", companyDto);

        return "company/company-update";
    }

    @PostMapping("/update/{companyId}")
    public String updateCompany(){ return "redirect:/company/company-list"; }


    @GetMapping("/create")
    public String getCreateCompanyForm(Model model) {
        model.addAttribute("newCompany", new CompanyDto());

        return "/company/company-create";
    }

    @PostMapping("/create")
    public String createCompany(){return "redirect:/company/company-list";}



    @GetMapping("delete/{companyId}")
    public String deleteCompany(@PathVariable("companyId") Long companyId, Model model){
        model.addAttribute("company", companyService.findById(companyId));
        companyService.delete(companyId);
        return "redirect:/company/company-list";
    }

}
