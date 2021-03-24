package com.banking.banking.controllers;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import com.banking.banking.models.BankAccount;
import com.banking.banking.models.CheckingAccount;
import com.banking.banking.models.LoanAccount;
import com.banking.banking.models.SavingAccount;
import com.banking.banking.models.User;
import com.banking.banking.services.BankAccountService;
import com.banking.banking.services.CheckingAccountService;
import com.banking.banking.services.LoanAccountService;
import com.banking.banking.services.SavingAccountService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final CheckingAccountService checkingAccountService;
    private final SavingAccountService savingAccountService;
    private final LoanAccountService loanAccountService;

    /**
     * Inject all the bank account services to this controller
     * @param bankAccountService
     * @param checkingAccountService
     * @param savingAccountService
     * @param loanAccountService
     */
    public BankAccountController(BankAccountService bankAccountService, CheckingAccountService checkingAccountService, SavingAccountService savingAccountService, LoanAccountService loanAccountService) {
        this.bankAccountService = bankAccountService;
        this.checkingAccountService = checkingAccountService;
        this.savingAccountService = savingAccountService;
        this.loanAccountService = loanAccountService;
    }
    
    /**
     * Display all the current customer bank accounts
     * @return myAccountView
     */
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/my-account")
    public String myAccount(Model model) {

        // Get the current customer id
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User customer = (User) principal;
        String customerId = customer.getId();

        // Get all the accounts of the current customer
        CheckingAccount checkingAccount = checkingAccountService.findCheckingAccountByCustomer(customerId);
        Set<SavingAccount> savingAccounts = savingAccountService.findSavingAccountByCustomer(customerId);
        Set<LoanAccount> loanAccounts = loanAccountService.findLoanAccountsByCustomer(customerId);

        // Add all bank accounts to the model
        model.addAttribute("checkingAccount", checkingAccount);
        model.addAttribute("savingAccounts", savingAccounts);
        model.addAttribute("loanAccounts", loanAccounts);

        // Return the view
        return "bank-account/my-account.html";
    }

    /**
     * Display all the bank accounts in the system in <code>page</code> of <code>size</code> filtered by the attributes selected by the user (customer care, accountant, manager)
     * @param startDate
     * @param endDate
     * @param bankId
     * @param page
     * @param size
     * @return bankAccountsView
     */
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER_CARE', 'ROLE_ACCOUNTANT', 'ROLE_MANAGER')")
    @GetMapping("/bank-accounts")
    public String bankAccounts(@RequestParam("startDate") LocalDateTime startDate, @RequestParam("endDate") LocalDateTime endDate, @RequestParam("bankId") String bankId, @RequestParam("page") int page, @RequestParam("size") int size) {
        // TODO: Get the bank accounts list and return to the view
        return "bank-account/bank-accounts.html";
    }

    /**
     * <p>Display the bank account which has the <code>id</code> to the user.</p>
     * <p>The <code>customer</code> can only see her/his bank account information.</p>
     * @param id
     * @return bankAccountView
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/bank-accounts/{id}")
    public String bankAccount(@PathVariable("id") String id, Model model) {
        // TODO: Get the bank account information and return to the view
        // Get the bank account
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        
        // Get the current user role
        Set<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
        if (roles.contains("ROLE_CUSTOMER")){
            // if the authenticated user is customer, then check if the bank account belongs to this customer
        }
        return "bank-account/bank-account.html";
    }
}
