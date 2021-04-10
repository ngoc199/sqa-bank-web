package com.banking.banking.controller.bankAccountController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.banking.banking.model.bankaccount.loanAccount.LoanAccount;
import com.banking.banking.model.bankaccount.savingsAccount.SavingsAccount;
import com.banking.banking.model.user.customer.Customer;
import com.banking.banking.model.user.UserLogin;
import com.banking.banking.service.bankAccountService.LoanAccountService;
import com.banking.banking.service.bankAccountService.SavingsAccountService;
import com.banking.banking.service.customerService.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BankAccountController {

    private final SavingsAccountService savingsAccountService;
    private final LoanAccountService loanAccountService;
    private final CustomerService customerService;

    @GetMapping("my-account")
    @PreAuthorize(value = "hasAuthority('my_account:read')")
    public String myAccounts(Model model) {
        UserLogin currentUser = (UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findCustomerByUsername(currentUser.getUsername());
        // Seperate bank accounts into savings accounts and loan accounts
        List<SavingsAccount> savingsAccounts = savingsAccountService.getSavingAccountsByCustomerId(customer.getId());
        List<LoanAccount> loanAccounts = loanAccountService.getLoanAccountsByCustomerId(customer.getId());
        model.addAttribute("savingsAccounts", savingsAccounts);
        model.addAttribute("loanAccounts", loanAccounts);
        return "bank-account/my-account.html";
    }

    @GetMapping("my-account/{account-id}")
    @PreAuthorize(value = "hasAuthority('my_account:read')")
    public String myAccountDetails(Model model, @PathVariable("account-id") String accountId,
            @RequestParam("type") String type) {

        // Get the current customer
        UserLogin currentUser = (UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findCustomerByUsername(currentUser.getUsername());

        // Dispatch the view base on account type
        switch (type) {
        case "loan":
            LoanAccount loanAccount = loanAccountService.getLoanAccountById(accountId);

            // If the current customer is not the owner of the account then throws forbidden
            // response
            if (loanAccount.getCustomer().getId() != customer.getId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            model.addAttribute("account", loanAccount);
            return "bank-account/my-account-detail/loan.html";
        case "savings":
        default: // Display the saving account detail by default
            SavingsAccount savingAccount = savingsAccountService.getSavingAccountById(accountId);

            // If the current customer is not the owner of the account then throws forbidden
            // response
            if (savingAccount.getCustomer().getId() != customer.getId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            model.addAttribute("account", savingAccount);
            return "bank-account/my-account-detail/savings.html";
        }
    }

    /**
     * Display a list of accounts base on type. Only the employees can access this
     * view.
     *
     * @param type
     * @param model
     * @return accountListView
     */
    @GetMapping("accounts")
    @PreAuthorize("hasAuthority('accounts:read')")
    public String accounts(@RequestParam(name = "type", defaultValue = "null", required = false) String type,
            Model model, @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            HttpServletRequest request) {
        page = (page < 1) ? 1 : page; // Page must be greater or equal to 1
        size = (size < 10 || size > 100) ? 10 : size; // Size must be greater or equal to 10 and lower or equal to 100

        switch (type) {
        case "loan":
            List<LoanAccount> loanAccounts = loanAccountService.getLoanAccountList(page, size);
            model.addAttribute("accounts", loanAccounts);
            return "accounts/loan-accounts.html";
        case "savings":
            List<SavingsAccount> savingAccounts = savingsAccountService.getSavingAccountList(page, size);
            model.addAttribute("accounts", savingAccounts);
            return "accounts/saving-accounts.html";
        default: // Display list of saving accounts by default
            return "redirect:" + request.getRequestURI() + "?type=savings";
        }
    }
}
