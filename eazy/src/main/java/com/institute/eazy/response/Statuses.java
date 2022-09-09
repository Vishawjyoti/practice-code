package com.institute.eazy.response;

public class Statuses {
    /*  ##################### STATUSES FOR USER API STARTS ########################  */
    /*  #####################        CODES :: 1-200        ########################  */

    public static Status LoginSuccesful_101 = new Status(101, "Login Successful");
    public static Status LoginFailed_102 = new Status(102,     "Login Failed");

    public static Status UserAddingUnSuccessfull_103 = new Status(103, "Error while adding the user");

    public static Status UserAddedSuccessfully_104 = new Status(104, "User added Successfully");

    public static Status UserFetchingUnSuccessfull_105 = new Status(105, "User is not  Fetched Successfully");

    public static Status UserFetchingSuccessfull_106 = new Status(106, "User Fetched Successfully");

    public static Status UserNotUpdateSuccessfully_107 = new Status(107, "User is not updated successfully");

    public static Status  UserUpdatedSuccessfully_108 = new Status(108, "User is updated successfully");

    public static Status  UserDeletionUnsuccessful_109 = new Status(109, "User does not deleted successfully");

    public static Status  UserDeletedSuccessfully_110 = new Status(110, "User is deleted successfully");


    /*  ##################### STATUSES FOR USER API STARTS ########################  */




}
