package com.banking.banking.controller.configController;

import java.util.List;

import javax.validation.Valid;

import com.banking.banking.model.interest.Interest;
import com.banking.banking.service.interestService.InterestService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {

    private final InterestService interestService;

    /**
     * Display all the interests
     *
     * @param model
     * @return interestListView
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('config:read')")
    public String index(Model model) {
        List<Interest> interests = interestService.getAllInterests();
        model.addAttribute("interests", interests);
        return "config/index.html";
    }

    /**
     * Display add interest form
     *
     * @param model
     * @return addInterestView
     */
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('config:write')")
    public String addInterestDisplay(Model model) {
        Interest interest = new Interest();
        model.addAttribute("interest", interest);
        return "config/add-interest.html";
    }

    /**
     * Add new interest
     *
     * @param interest
     * @return configView
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('config:write')")
    public String addInterest(@ModelAttribute @Valid Interest interest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "config/add-interest.html";
        }
        boolean result = interestService.addInterest(interest);
        if (!result) {
            return "redirect:/add";
        }
        return "redirect:/config?success";
    }
}
