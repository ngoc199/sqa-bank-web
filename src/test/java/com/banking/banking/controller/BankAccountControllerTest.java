package com.banking.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTest {

    /** Service bean name */
    private static final String USER_DETAILS_SERVICE_BEAN_NAME = "userLoginService";

    /** Expected URL pattern */
    private static final String LOGIN_PAGE_URL_PATTERN = "**/login";

    /** Expected view names */
    private static final String VIEW_MY_ACCOUNT = "bank-account/my-account.html";
    private static final String VIEW_SAVINGS_ACCOUNT_DETAILS = "bank-account/my-account-detail/savings.html";
    private static final String VIEW_LOAN_ACCOUNT_DETAILS = "bank-account/my-account-detail/loan.html";
    private static final String VIEW_SAVINGS_ACCOUNT_LIST = "accounts/saving-accounts.html";
    private static final String VIEW_LOAN_ACCOUNT_LIST = "accounts/loan-accounts.html";
    private static final String VIEW_SAVINGS_REPORT = "report/savings.html";
    private static final String VIEW_LOAN_REPORT = "report/loan.html";

    /** Request URLs */
    private static final String REQUEST_MY_ACCOUNT = "/my-account";
    private static final String REQUEST_MY_ACCOUNT_SAVINGS_DETAILS = "/my-account/{id}?type=savings";
    private static final String REQUEST_MY_ACCOUNT_LOAN_DETAILS = "/my-account/{id}?type=loan";
    private static final String REQUEST_SAVINGS_ACCOUNT_DETAILS = "/accounts/{id}?type=savings";
    private static final String REQUEST_LOAN_ACCOUNT_DETAILS = "/accounts/{id}?type=loan";
    private static final String REQUEST_SAVINGS_ACCOUNT_LIST = "/accounts?type=savings";
    private static final String REQUEST_LOAN_ACCOUNT_LIST = "/accounts?type=loan";
    private static final String REQUEST_SAVINGS_REPORT = "/reports?type=savings";
    private static final String REQUEST_LOAN_REPORT = "/reports?type=loan";

    /** Usernames */
    private static final String USERNAME_CUSTOMER = "customer";
    private static final String USERNAME_CUSTOMER_NONE = "customerNone";
    private static final String USERNAME_EMP_CARE = "employee1";
    private static final String USERNAME_EMP_ACC = "employee2";

    @Autowired
    private MockMvc mockMvc;

    /** My Accounts */

    @Test
    @WithAnonymousUser
    public void myAccounts_AnonymousUser_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get(REQUEST_MY_ACCOUNT)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(LOGIN_PAGE_URL_PATTERN));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_CARE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void myAccounts_UnauthorizedUser_ShouldReturn403() throws Exception {
        mockMvc.perform(get(REQUEST_MY_ACCOUNT)).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void myAccounts_AuthorizedUser_ShouldReturnMyAccountPage() throws Exception {
        mockMvc.perform(get(REQUEST_MY_ACCOUNT)).andExpect(status().isOk()).andExpect(view().name(VIEW_MY_ACCOUNT));
    }

    /** My Account Details */

    @Test
    @WithAnonymousUser
    public void myAccountDetails_AnonymousUser_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_ACCOUNT_DETAILS, "BANK_1")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(LOGIN_PAGE_URL_PATTERN));
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER_NONE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void myAccountDetails_UnauthorizedUser_ShouldReturn403() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_ACCOUNT_DETAILS, "BANK_1")).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void myAccountDetails_AuthorizedUser_ShouldReturnSavingsAccountDetailsPage() throws Exception {
        mockMvc.perform(get(REQUEST_MY_ACCOUNT_SAVINGS_DETAILS, "BANK_1")).andExpect(status().isOk())
                .andExpect(view().name(VIEW_SAVINGS_ACCOUNT_DETAILS));
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void myAccountDetails_AuthorizedUser_ShouldReturnLoanAccountDetailsPage() throws Exception {
        mockMvc.perform(get(REQUEST_MY_ACCOUNT_LOAN_DETAILS, "BANK_2")).andExpect(status().isOk())
                .andExpect(view().name(VIEW_LOAN_ACCOUNT_DETAILS));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_CARE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void myAccountDetails_EmployeeUser_ShouldReturnSavingsAccountDetailsPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_ACCOUNT_DETAILS, "BANK_1")).andExpect(status().isOk())
                .andExpect(view().name(VIEW_SAVINGS_ACCOUNT_DETAILS));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_CARE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void myAccountDetails_EmployeeUser_ShouldReturnLoanAccountDetailsPage() throws Exception {
        mockMvc.perform(get(REQUEST_LOAN_ACCOUNT_DETAILS, "BANK_2")).andExpect(status().isOk())
                .andExpect(view().name(VIEW_LOAN_ACCOUNT_DETAILS));
    }

    /** Accounts */

    @Test
    @WithAnonymousUser
    public void accounts_AnonymousUser_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_ACCOUNT_LIST)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(LOGIN_PAGE_URL_PATTERN));
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void accounts_UnauthorizedUser_ShouldReturn403() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_ACCOUNT_LIST)).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_CARE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void accounts_AuthorizedUser_ShouldReturnSavingsListPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_ACCOUNT_LIST)).andExpect(status().isOk())
                .andExpect(view().name(VIEW_SAVINGS_ACCOUNT_LIST));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_CARE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void accounts_AuthorizedUser_ShouldReturnLoanListPage() throws Exception {
        mockMvc.perform(get(REQUEST_LOAN_ACCOUNT_LIST)).andExpect(status().isOk())
                .andExpect(view().name(VIEW_LOAN_ACCOUNT_LIST));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_CARE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void accounts_UndefinedType_ShouldRedirectToSavingsListAccountPage() throws Exception {
        mockMvc.perform(get("/accounts")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(REQUEST_SAVINGS_ACCOUNT_LIST));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_CARE, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void accounts_ValidDateRange_ShouldReturnSavingsAccountListPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_ACCOUNT_LIST + "&begin_date=2020-10-20&end_date=2020-10-20"))
                .andExpect(status().isOk()).andExpect(view().name(VIEW_SAVINGS_ACCOUNT_LIST));
    }

    /** Show report */

    @Test
    @WithAnonymousUser
    public void showReport_AnonymousUser_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_REPORT)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(LOGIN_PAGE_URL_PATTERN));
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void showReport_UnauthorizedUser_ShouldReturn403() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_REPORT)).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_ACC, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void showReport_UndefinedType_ShouldRedirectToSavingsReportPage() throws Exception {
        mockMvc.perform(get("/reports")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(REQUEST_SAVINGS_REPORT));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_ACC, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void showReport_AuthorizedUser_ShouldReturnSavingsReportPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_REPORT)).andExpect(status().isOk())
                .andExpect(view().name(VIEW_SAVINGS_REPORT));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_ACC, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void showReport_AuthorizedUser_ShouldReturnLoanReportPage() throws Exception {
        mockMvc.perform(get(REQUEST_LOAN_REPORT)).andExpect(status().isOk()).andExpect(view().name(VIEW_LOAN_REPORT));
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_ACC, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void showReport_ValidDateRange_ShouldReturnSavingsReportPage() throws Exception {
        mockMvc.perform(get(REQUEST_SAVINGS_REPORT + "&begin_date=2000-10-20&end_date=2020-10-20"))
                .andExpect(status().isOk()).andExpect(view().name(VIEW_SAVINGS_REPORT));
    }
}
