package com.institute.eazy.response;

public class Errors {

    public static ResponseError InvalidLoginId_1=new ResponseError(1,"Login Id is Incorrect");
    public static ResponseError MultipleRowAgainstGivenLoginId_2 =new ResponseError(2,"There are Multiple rows fetched against Login Id." +
            " Inconsistent DataBase.");
    public static ResponseError PasswordIncorrect_3=new ResponseError(3,"Password is not Correct");

    public static ResponseError IdCannotNull_4 =new ResponseError(4,"Id can not be null");

    public static ResponseError IdDoesNotExist_5 =new ResponseError(5,"Id Does not Exists");

    public static ResponseError EmailCanNotNull_6 =new ResponseError(6,"Email can not be null");

    public static ResponseError EmailAlreadyExist_7 =new ResponseError(7,"Email Already Exist");

    public static ResponseError MobileNoCanNotNull_8 = new ResponseError(8,"Mobile number can not be null");

    public static ResponseError MobileNoAlreadyExist_9 =new ResponseError(9,"Mobile Number Already Exist");

    public static ResponseError loginIdCanNotNull_10 = new ResponseError(10,"Login Id can not be null");

    public static ResponseError LoginIdAlreadyExist_11= new ResponseError(11,"Login Id Already Exist");

    public static ResponseError FirstNameCanNotNull_12 = new ResponseError(12,"First Name can not be null");

    public static ResponseError FirstNameLengthExceeds_13 = new ResponseError(13,"Max length for First name can not be more than 45");

    public static ResponseError LastNameCanNotNull_14 = new ResponseError(14,"Last Name can not be null");

    public static ResponseError LastNameOverSized_15 = new  ResponseError(15,"Max length for Last name can not be more than 45");

    public static ResponseError StatusValueNotCorrect_16 = new  ResponseError(16,"Status value should be Active or Inactive");

    public static ResponseError StatusCanNotNull_17 = new  ResponseError(17,"Status can not be null");

    public static ResponseError GenderCanNotNull_18 = new  ResponseError(18,"Gender can not be null");

    public static ResponseError GenderValueNotCorrect_19 = new  ResponseError(16,"Status value should be from the prescribed list");

    public static ResponseError TypeCanNotNull_20 = new ResponseError(20, "Type can not be null");


    public static ResponseError TypeValueNotCorrect_21 = new ResponseError(21, "Type of the user should be from prescribed list");
}
