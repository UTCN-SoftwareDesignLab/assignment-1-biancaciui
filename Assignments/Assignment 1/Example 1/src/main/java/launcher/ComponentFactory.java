package launcher;

import controller.*;
import database.DatabaseConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.action.ActionRepository;
import repository.action.ActionRepositoryMySQL;
import repository.book.BookRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import services.user.AuthenticationService;
import services.user.AuthenticationServiceMySQL;
import view.*;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final LoginView loginView;
    private final AdminView adminView;
    private final RegularEmployeeView employeeView;
    private final ReportActivityView reportActivityView;
    private final CRUDEmployeeView crudEmployeeView;
    private final ClientInfoView clientInfoView;
    private final ClientAccountView clientAccountView;
    private final TransferMoneyView transferMoneyView;
    private final UtilityBillsProcessView utilityBillsProcessView;

    private final LoginController loginController;
    private final AdminController adminController;
    private final RegularEmployeeController regularEmployeeController;
    private final ReportActivityController reportActivityController;
    private final CRUDEmployeeController crudEmployeeController;
    private final ClientInfoController clientInfoController;
    private final ClientAccountController clientAccountController;
    private final TransferMoneyController transferMoneyController;
    private final UtilityBillsProcessController utilityBillsProcessController;


    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final BookRepositoryMySQL bookRepositoryMySQL;
    private final ActionRepository actionRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DatabaseConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.loginView = new LoginView();
        this.adminView = new AdminView();
        this.employeeView = new RegularEmployeeView();
        this.reportActivityView = new ReportActivityView();
        this.clientAccountView = new ClientAccountView();
        this.crudEmployeeView = new CRUDEmployeeView();
        this.clientInfoView = new ClientInfoView();
        this.transferMoneyView = new TransferMoneyView();
        this.utilityBillsProcessView = new UtilityBillsProcessView();
        this.loginController = new LoginController(loginView, authenticationService,userRepository);
        this.adminController = new AdminController(adminView,userRepository);
        this.actionRepository = new ActionRepositoryMySQL(connection);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.regularEmployeeController = new RegularEmployeeController(employeeView,userRepository);
        this.reportActivityController = new ReportActivityController(reportActivityView,userRepository,actionRepository);
        this.crudEmployeeController = new CRUDEmployeeController(crudEmployeeView,userRepository,authenticationService,actionRepository);
        this.clientInfoController = new ClientInfoController(clientInfoView,userRepository);
        this.clientAccountController = new ClientAccountController(clientAccountView,accountRepository,clientRepository,actionRepository);
        this.transferMoneyController = new TransferMoneyController(transferMoneyView,userRepository);
        this.utilityBillsProcessController = new UtilityBillsProcessController(utilityBillsProcessView,userRepository);
        bookRepositoryMySQL = new BookRepositoryMySQL(connection);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public LoginView getLoginView() {
        return loginView;
    }
    public AdminView getAdminView() {
        return adminView;
    }
    public RegularEmployeeView getRegularEmployeeView() {
        return employeeView;
    }
    public ReportActivityView getReportActivityView(){return reportActivityView;}
    public RegularEmployeeView getEmployeeView() {
        return employeeView;
    }
    public CRUDEmployeeView getCrudEmployeeView() {
        return crudEmployeeView;
    }
    public ClientInfoView getClientInfoView() {
        return clientInfoView;
    }
    public ClientAccountView getClientAccountView() {
        return clientAccountView;
    }
    public TransferMoneyView getTransferMoneyView() {
        return transferMoneyView;
    }
    public UtilityBillsProcessView getUtilityBillsProcessView() {
        return utilityBillsProcessView;
    }

    public BookRepositoryMySQL getBookRepositoryMySQL() {
        return bookRepositoryMySQL;
    }

    public LoginController getLoginController() {
        return loginController;
    }
    public AdminController getAdminController() {
        return adminController;
    }
    public RegularEmployeeController getRegularEmployeeController() {
        return regularEmployeeController;
    }
    public ReportActivityController getReportActivityController(){return reportActivityController;}
    public CRUDEmployeeController getCrudEmployeeController() {
        return crudEmployeeController;
    }
    public ClientInfoController getClientInfoController() {
        return clientInfoController;
    }
    public ClientAccountController getClientAccountController() {
        return clientAccountController;
    }
    public TransferMoneyController getTransferMoneyController() {
        return transferMoneyController;
    }
    public UtilityBillsProcessController getUtilityBillsProcessController() {
        return utilityBillsProcessController;
    }
}
