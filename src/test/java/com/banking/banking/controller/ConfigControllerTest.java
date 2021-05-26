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
public class ConfigControllerTest {

    /** Service bean name */
    private static final String USER_DETAILS_SERVICE_BEAN_NAME = "userLoginService";

    /** Expected URL pattern */
    private static final String LOGIN_PAGE_URL_PATTERN = "**/login";

    /** Expected view names */
    private static final String VIEW_INDEX = "config/index.html";
    private static final String VIEW_ADD_INTEREST = "config/add-interest.html";
    private static final String VIEW_EDIT_INTEREST = "config/edit-interest.html";

    /** Request URLs */
    private static final String REQUEST_INDEX = "/config";
    private static final String REQUEST_ADD_INTEREST = REQUEST_INDEX + "/add";
    private static final String REQUEST_EDIT_INTEREST = REQUEST_INDEX + "/{id}/edit";
    // private static final String REQUEST_DELETE_INTEREST = REQUEST_INDEX + "/{id}/delete";

    /** Usernames */
    private static final String USERNAME_CUSTOMER = "customer";
    private static final String USERNAME_EMP_MGR = "employee3";

    @Autowired
    private MockMvc mockMvc;

    /** Display Index */

    @Test
    @WithAnonymousUser
    public void index_AnonymousUser_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get(REQUEST_INDEX)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(LOGIN_PAGE_URL_PATTERN));
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void index_UnauthorizedUser_ShouldReturn403() throws Exception {
        mockMvc.perform(get(REQUEST_INDEX)).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_MGR, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void index_AuthorizedUser_ShouldReturnIndexPage() throws Exception {
        mockMvc.perform(get(REQUEST_INDEX)).andExpect(status().isOk()).andExpect(view().name(VIEW_INDEX));
    }

    /** Display Add Interest Page */

    @Test
    @WithAnonymousUser
    public void addInterestDisplay_AnonymousUser_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get(REQUEST_ADD_INTEREST)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(LOGIN_PAGE_URL_PATTERN));
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void addInterestDisplay_UnauthorizedUser_ShouldReturn403() throws Exception {
        mockMvc.perform(get(REQUEST_ADD_INTEREST)).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_MGR, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void addInterestDisplay_AuthorizedUser_ShouldReturnAddInterestPage() throws Exception {
        mockMvc.perform(get(REQUEST_ADD_INTEREST)).andExpect(status().isOk())
                .andExpect(view().name(VIEW_ADD_INTEREST));
    }

    /** TODO Add Interest */

    /** Display Edit Interest */

    @Test
    @WithAnonymousUser
    public void editInterestDisplay_AnonymousUser_ShouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get(REQUEST_EDIT_INTEREST, 7)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrlPattern(LOGIN_PAGE_URL_PATTERN));
    }

    @Test
    @WithUserDetails(value = USERNAME_CUSTOMER, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void editInterestDisplay_UnauthorizedUser_ShouldReturn403() throws Exception {
        mockMvc.perform(get(REQUEST_EDIT_INTEREST, 7)).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = USERNAME_EMP_MGR, userDetailsServiceBeanName = USER_DETAILS_SERVICE_BEAN_NAME)
    public void editInterestDisplay_AuthorizedUser_ShouldReturnEditPage() throws Exception {
        mockMvc.perform(get(REQUEST_EDIT_INTEREST, 7)).andExpect(status().isOk()).andExpect(view().name(VIEW_EDIT_INTEREST));
    }

    /** TODO Edit Interest */
    /** TODO Delete Interest */

}
