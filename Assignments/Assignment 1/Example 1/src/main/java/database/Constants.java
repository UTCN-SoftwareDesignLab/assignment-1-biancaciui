package database;

import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class Constants {

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";
        public static final String ACCOUNT = "account";
        public static final String CLIENT = "client";
        public static final String EMPLOYEE_ACTIONS = "employee_actions";
        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{CLIENT,USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE,ACCOUNT,EMPLOYEE_ACTIONS};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";
        public static final String CLIENT = "client";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE, CLIENT};
    }

    public static class Rights {
        //regular employee
        public static final String CREATE_CLIENT = "create_client";
        public static final String VIEW_CLIENT = "view_client";
        public static final String UPDATE_CLIENT = "update_client";

        public static final String CREATE_CLIENT_ACCOUNT = "create_client_account";
        public static final String VIEW_CLIENT_ACCOUNT = "view_client_account";
        public static final String UPDATE_CLIENT_ACCOUNT = "update_client_account";
        public static final String DELETE_CLIENT_ACCOUNT = "delete_client_account";

        public static final String TRANSFER_MONEY = "transfer_money";

        public static final String PROCESS_UTILITY_BILLS = "process_bills";

        //admin
        public static final String CREATE_EMPLOYEE = "create_employee";
        public static final String DELETE_EMPLOYEE = "delete_employee";
        public static final String UPDATE_EMPLOYEE = "update_employee";
        public static final String VIEW_EMPLOYEE = "view_employee";

        public static final String CREATE_REPORT = "create_report";

        public static final String[] RIGHTS = new String[]{CREATE_CLIENT, VIEW_CLIENT, UPDATE_CLIENT,DELETE_CLIENT_ACCOUNT, CREATE_CLIENT_ACCOUNT,VIEW_CLIENT_ACCOUNT,UPDATE_CLIENT_ACCOUNT, TRANSFER_MONEY, PROCESS_UTILITY_BILLS, CREATE_EMPLOYEE,DELETE_EMPLOYEE,UPDATE_EMPLOYEE, VIEW_EMPLOYEE,CREATE_REPORT};
    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(RIGHTS));

        ROLES_RIGHTS.get(EMPLOYEE).addAll(Arrays.asList(CREATE_CLIENT, VIEW_CLIENT, UPDATE_CLIENT,DELETE_CLIENT_ACCOUNT, CREATE_CLIENT_ACCOUNT,VIEW_CLIENT_ACCOUNT,UPDATE_CLIENT_ACCOUNT, TRANSFER_MONEY, PROCESS_UTILITY_BILLS));

        ROLES_RIGHTS.get(CLIENT).addAll(Arrays.asList(TRANSFER_MONEY));

        return ROLES_RIGHTS;
    }
    public static class Currency {
        public static final String LEU = "leu";
        public static final String EURO = "euro";
        public static final String DOLLAR = "dollar";
        public static final String[] CURRENCIES = new String[]{LEU,EURO,DOLLAR};
//        public static Currency getCurrency(String s){
//            switch (s){
//                case "lei": return LEU;
//
//            }
//        }
    }
    public static String decodeRight(Long id){
        if (id == 1L) {
            return CREATE_CLIENT;
        } else if (id == 2L) {
            return VIEW_CLIENT;
        } else if (id == 3L) {
            return UPDATE_CLIENT;
        } else if (id == 4L) {
            return CREATE_CLIENT_ACCOUNT;
        } else if (id == 5L) {
            return VIEW_CLIENT_ACCOUNT;
        } else if (id == 6L) {
            return UPDATE_CLIENT_ACCOUNT;
        } else if (id == 7L) {
            return DELETE_CLIENT_ACCOUNT;
        } else if (id == 8L) {
            return TRANSFER_MONEY;
        } else if (id == 9L) {
            return PROCESS_UTILITY_BILLS;
        } else if (id == 10L) {
            return CREATE_EMPLOYEE;
        } else if (id == 11L) {
            return DELETE_EMPLOYEE;
        } else if (id == 12L) {
            return UPDATE_EMPLOYEE;
        } else if (id == 13L) {
            return VIEW_EMPLOYEE;
        } else if (id == 14L) {
            return CREATE_REPORT;
        }
        return null;
    }
    public static Long decodeRight(String s){
        switch (s){
            case CREATE_CLIENT: return 1L;
            case VIEW_CLIENT: return 2L;
            case UPDATE_CLIENT: return 3L;
            case CREATE_CLIENT_ACCOUNT: return 4L;
            case VIEW_CLIENT_ACCOUNT: return 5L;
            case UPDATE_CLIENT_ACCOUNT: return 6L;
            case DELETE_CLIENT_ACCOUNT: return 7L;
            case TRANSFER_MONEY: return 8L;
            case PROCESS_UTILITY_BILLS: return 9L;
            case CREATE_EMPLOYEE: return 10L;
            case DELETE_EMPLOYEE: return 11L;
            case UPDATE_EMPLOYEE: return 12L;
            case VIEW_EMPLOYEE: return 13L;
            case CREATE_REPORT: return 14L;


        }
        return -1L;
    }
}
