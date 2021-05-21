package com.banking.banking.controller.bankAccountController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.banking.banking.model.bankaccount.loanAccount.LoanAccount;
import com.banking.banking.model.bankaccount.savingsAccount.SavingsAccount;
import com.banking.banking.model.user.customer.Customer;
import com.banking.banking.model.user.Permission;
import com.banking.banking.model.user.UserLogin;
import com.banking.banking.service.bankAccountService.LoanAccountService;
import com.banking.banking.service.bankAccountService.SavingsAccountService;
import com.banking.banking.service.customerService.CustomerService;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        Customer customer;
        try {
            customer = customerService.findCustomerByUsername(currentUser.getUsername());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        // Seperate bank accounts into savings accounts and loan accounts
        List<SavingsAccount> savingsAccounts = savingsAccountService.getSavingAccountsByCustomerId(customer.getId());
        List<LoanAccount> loanAccounts = loanAccountService.getLoanAccountsByCustomerId(customer.getId());
        model.addAttribute("savingsAccounts", savingsAccounts);
        model.addAttribute("loanAccounts", loanAccounts);
        return "bank-account/my-account.html";
    }

    @GetMapping({ "my-account/{account-id}", "accounts/{account-id}" })
    @PreAuthorize(value = "hasAnyAuthority('my_account:read', 'accounts:read')")
    public String myAccountDetails(Model model, @PathVariable("account-id") String accountId,
            @RequestParam("type") String type) {

        // Check if the user has the read account permission
        boolean canReadAccounts = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority(Permission.ACCOUNTS_READ.getPermission()));

        // Get the current customer
        UserLogin currentUser = (UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = null;
        try {
            customer = customerService.findCustomerByUsername(currentUser.getUsername());
        } catch (Exception e) {
            if (!canReadAccounts) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }

        // Dispatch the view base on account type
        switch (type) {
            case "loan":
                LoanAccount loanAccount = loanAccountService.getLoanAccountById(accountId);

                // If the current customer is not the owner of the account and don't have
                // permission to read account then throws forbidden response
                if ((customer == null || loanAccount.getCustomer().getId() != customer.getId()) && !canReadAccounts) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
                model.addAttribute("account", loanAccount);
                return "bank-account/my-account-detail/loan.html";
            case "savings":
            default: // Display the saving account detail by default
                SavingsAccount savingAccount = savingsAccountService.getSavingAccountById(accountId);

                // If the current customer is not the owner of the account and don't have
                // permission to read account then throws forbidden response
                if ((customer == null || savingAccount.getCustomer().getId() != customer.getId()) && !canReadAccounts) {
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
    public String accounts(@RequestParam(name = "owner_name", required = false) String ownerName,
            @RequestParam(name = "begin_date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate begin,
            @RequestParam(name = "end_date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate end,
            @RequestParam(name = "type", defaultValue = "", required = false) String type, Model model,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam Map<String, String> params, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        page = (page < 0) ? 0 : page; // Page must be greater or equal to 0, because page is counted from 0
        size = (size < 10 || size > 100) ? 10 : size; // Size must be greater or equal to 10 and lower or equal to 100

        // Check if the begin date is after the end date
        if (begin != null && end != null && begin.isAfter(end)) {
            redirectAttributes.addFlashAttribute("errorMessageCategory", "danger");

            // Reserve the current request "type" parameter and redirect to the current url
            return "redirect:" + request.getRequestURI() + "?type=" + request.getParameter("type");
        }

        switch (type) {
            case "loan":
                Page<LoanAccount> loanAccounts = loanAccountService.getLoanAccountList(ownerName, begin, end, page,
                        size);
                model.addAttribute("accounts", loanAccounts);
                return "accounts/loan-accounts.html";
            case "savings":
                Page<SavingsAccount> savingAccountsPage = savingsAccountService.getSavingsAccountList(ownerName, begin,
                        end, page, size);
                model.addAttribute("accounts", savingAccountsPage);
                return "accounts/saving-accounts.html";
            default: // Display list of saving accounts by default
                return "redirect:" + request.getRequestURI() + "?type=savings";
        }
    }

    @GetMapping("reports")
    @PreAuthorize("hasAuthority('report:export')")
    public String showReport(@RequestParam(name = "owner_name", required = false) String ownerName,
            @RequestParam(name = "begin_date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate begin,
            @RequestParam(name = "end_date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate end,
            @RequestParam(name = "type", defaultValue = "", required = false) String type, Model model,
            @RequestParam Map<String, String> params, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        // Check if the begin date is after the end date
        if (begin != null && end != null && begin.isAfter(end)) {
            redirectAttributes.addFlashAttribute("errorMessageCategory", "danger");

            // Reserve the current request "type" parameter and redirect to the current url
            return "redirect:/accounts?type=" + request.getParameter("type");
        }

        BigDecimal totalAmount = BigDecimal.valueOf(0);

        switch (type) {
            case "loan":
                List<LoanAccount> loanAccounts = loanAccountService.getLoanAccountList(ownerName, begin, end);
                if (loanAccounts.size() == 0) {
                    redirectAttributes.addFlashAttribute("noAccountsToReport", "danger");
                    return "redirect:/accounts?type=" + request.getParameter("type");
                }
                for (var account : loanAccounts) {
                    totalAmount = totalAmount.add(account.getCurrentPayingAmount());
                }
                model.addAttribute("accounts", loanAccounts);
                model.addAttribute("totalAmount", totalAmount);
                return "report/loan.html";
            case "savings":
                List<SavingsAccount> savingsAccounts = savingsAccountService.getSavingsAccountList(ownerName, begin,
                        end);
                if (savingsAccounts.size() == 0) {
                    redirectAttributes.addFlashAttribute("noAccountsToReport", "danger");
                    return "redirect:/accounts?type=" + request.getParameter("type");
                }
                for (var account : savingsAccounts) {
                    totalAmount = totalAmount.add(account.getCurrentSavingsInterestAmount());
                }
                model.addAttribute("accounts", savingsAccounts);
                model.addAttribute("totalAmount", totalAmount);
                return "report/savings.html";
            default: // Display list of saving accounts by default
                return "redirect:" + request.getRequestURI() + "?type=savings";
        }
    }

}
