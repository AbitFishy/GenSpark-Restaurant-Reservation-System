package com.genspark.backend.Controller;

import com.genspark.backend.Entity.UserAccount;
import com.genspark.backend.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/users")
public class UserManagementController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/user")
    // using 'ROLE_ --> because of ApplicationUserRole class --> method getGrantedAuthority()
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN')")
    public List<UserAccount> getUserAccounts() {
        return this.userAccountService.getAllUserAccount();
    }

    @GetMapping("/userAccounts/{userID}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN', 'ROLE_USER')")
    public UserAccount getUserAccount(@PathVariable String userID) {
        return this.userAccountService.getUserAccountById(Long.parseLong(userID));
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('admin:create', 'employ:create')")
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.addUserAccount(userAccount);
    }

    @PostMapping("/login")
    @PreAuthorize("hasAuthority('admin:create', 'employ_create', 'user_create')")
    public UserAccount login(@RequestBody UserAccount userAccount) {
        return this.userAccountService.login(userAccount);
    }

    @PostMapping("/auth")
    @PreAuthorize("hasAuthority('admin:create', 'employ:create', 'user:create')")
    public String authenticateUserAccount(@RequestBody String email, @RequestBody String clearPassword) {
        return this.userAccountService.authenticateUserAccount(email, clearPassword) ? "true" : "false";
    }

    @PutMapping("/userAccounts/{userID}")
    @PreAuthorize("hasAuthority('admin:edit', 'employ:edit')")
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount, @PathVariable Long userID) {
        return this.userAccountService.updateUserAccount(userAccount, userID);
    }

    @DeleteMapping(path="{userID}")
    @PreAuthorize("hasAuthority('admin:delete', 'employ:delete')")
    public String deleteAccount(@PathVariable String userID)
    {
        return this.userAccountService.deleteUserAccountById(Long.parseLong(userID));
    }
}
