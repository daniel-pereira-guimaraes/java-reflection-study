package permissions;

import permissions.Annotations.MethodOperations;
import permissions.Annotations.Permissions;

@Permissions(role = Role.CLERK, allowed = OperationType.READ)
@Permissions(role = Role.MANAGER, allowed = {OperationType.READ, OperationType.WRITE})
@Permissions(role = Role.SUPPORT_ENGINEER, allowed = {OperationType.READ, OperationType.WRITE, OperationType.DELETE})
public class CompanyDataStore {
    private User user;
 
    public void CompanyDataStore(User user) {
        this.user = user;
    }
 
    @MethodOperations(OperationType.READ)
    public AccountRecord[] readAccounts(String [] accountIds) throws Throwable {
        PermissionsChecker.checkPermissions(this, "readAccounts");
        // ...
        return null;
    }
 
    @MethodOperations({OperationType.READ, OperationType.WRITE})
    public void updateAccount(String accountId, AccountRecord record) throws Throwable {
        PermissionsChecker.checkPermissions(this, "updateAccount");
        // ...
    }
 
    @MethodOperations(OperationType.READ)
    public Summary readAccountSummary(Role callerRole, String accountId) throws Throwable {
        PermissionsChecker.checkPermissions(this, "readAccountSummary");
        // ...
        return null;
    }
 
    @MethodOperations(OperationType.DELETE)
    public void deleteAccount(String accountId) throws Throwable {
        PermissionsChecker.checkPermissions(this, "deleteAccount");
        // ...
    }
}