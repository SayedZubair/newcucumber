package steps;

import pages.*;

public class PageInitializer {
    // as we are extending common method in every page, thats y we extend this page to common method
    public static LoginPage login;
    public static DashboardPage dashboard;
    public static AddEmployeePage addEmployee;
    public static EmployeeListPage employeeList;

    public static Addlanguagepage addLanguage;
    public static void intializePageObjects(){


        login = new LoginPage();
        dashboard=new DashboardPage();
        addEmployee=new AddEmployeePage();
        employeeList=new EmployeeListPage();
        addLanguage=new Addlanguagepage();
    }
}
