package com.institute.eazy.validator;


import com.institute.eazy.dao.UserDao;
import com.institute.eazy.entity.User;
import com.institute.eazy.response.Errors;
import com.institute.eazy.response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.institute.eazy.response.Errors.IdDoesNotExist_5;

@Service
public class UserValidator extends Validator{
    @Autowired
    UserDao userDao;


    Boolean isNotNullAndNotEmpty(String value){
    return value != null && value.length()>=1;
    }
    private boolean isAllowedLength(String value,Integer allowedLength) {
        if(value == null )
            return true;

        if(value !=null && value.length()<=allowedLength ) {
            System.out.println(" value :: "+value);
            return true;
        }
            return false;
        }
    private boolean isValidId(Object value, List<ResponseError> errors ) {
        if(value == null) {
            errors.add(Errors.IdCannotNull_4);
            return false;
        }
        Boolean isIdExist = true;
        isIdExist = userDao.isIdExist((Long) value);
        if(!isIdExist){
            errors.add(IdDoesNotExist_5);
            return false;
        }
      return true;
    }
    public boolean validateUpdateUser(Map<String, Object> params, List<ResponseError> errors) {
        Boolean returnValue = true;
        User user = (User) params.get("user");
        if(!isValidId(user.getId(), errors))
            returnValue = false;
        if(!isValidEmail(user.getEmailId(),user.getId(),errors))
            returnValue = false;
        if(!isValidMobile(user.getMobileNo(),user.getId(),errors))
            returnValue = false;
        if(!isValidLoginId(user.getLoginId(),user.getId(),errors))
            returnValue = false;
        if(! isValidFirstName(user.getFirstName(), errors))
            returnValue = false;
        if(! isValidLastName(user.getLastName(), errors))
            returnValue = false;
        if(!isValidUserType(user.getType(),errors))
            returnValue = false;
        if(!isValidStatus(user.getStatus(), errors))
            returnValue = false;
        if(! isValidGenderValue(user.getGender(), errors))
            returnValue = false;
            return returnValue;
           }

    public boolean validatePatchUser(Map<String, Object> params, List<ResponseError> errors) {
        Boolean returnValue = true;

        if(!isValidId(params.get("id"), errors))
         returnValue = false;

        if (params.get("firstName")!=null) {
            if(!isValidFirstName(params.get("firstName").toString(), errors))
                returnValue = false;
        }
        if (params.get("lastName")!=null){
            if(! isValidLastName(params.get("lastName").toString(), errors))
                returnValue = false;
        }
        if (params.get("emailId")!=null){
            if(!isValidEmail(params.get("emailId").toString(),(Long) params.get("id"),errors))
                returnValue = false;
        }
        if (params.get("loginId")!=null){
            if(!isValidLoginId(params.get("loginId").toString(),(Long) params.get("id"),errors))
                returnValue = false;
        }
        if (params.get("gender")!=null){
            if(! isValidGenderValue(params.get("gender").toString(), errors))
                returnValue = false;
        }
        if (params.get("status")!=null){
            if(!isValidStatus(params.get("status").toString(), errors))
                returnValue = false;
        }
        if (params.get("type")!=null){
            if(!isValidUserType(params.get("type").toString(),errors))
                returnValue = false;
        }
        if (params.get("mobileNo")!=null){
            if(!isValidMobile(params.get("mobileNo").toString(),(Long) params.get("id"),errors))
                returnValue = false;
        }
        return returnValue;
    }

    public boolean validateDeleteUser(Map<String, Object> params, List<ResponseError> errors) {
        Boolean returnValue = true;
        if(! isValidId(params.get("id"), errors) )

        returnValue = false;
        return returnValue;
    }

    public boolean validateAddUser(Map<String, Object> params, List<ResponseError> errors) {
        boolean returnValue = true;
        User user = (User) params.get("user");
        if(!isValidEmail(user.getEmailId(),user.getId(),errors))
            returnValue = false;
        if(!isValidMobile(user.getMobileNo(),user.getId(),errors))
            returnValue = false;
        if(user.getLoginId() != null && !isValidLoginId(user.getLoginId(),user.getId(),errors))
            returnValue = false;
        if(! isValidFirstName(user.getFirstName(), errors))
            returnValue = false;
        if(! isValidLastName(user.getLastName(), errors))
            returnValue = false;
        if(!isValidUserType(user.getType(),errors))
            returnValue = false;
        if(! isValidGenderValue(user.getGender(), errors))
            returnValue = false;

        return returnValue;
    }
    private boolean isValidUserType(String type, List<ResponseError> errors) {
        if (!validateIsTypeValueAllowed(type)) {
            errors.add(Errors.TypeValueNotCorrect_21);
            return false;
        }
        if (!isNotNullAndNotEmpty(type)) {
            errors.add(Errors.TypeCanNotNull_20);
            return false;
        }
        return true;
    }
    private boolean validateIsTypeValueAllowed(String value) {
        boolean returnValue = false;
        for (int i = 0; i < AppTypes.UserType.length; i++) {
            if (value.equalsIgnoreCase(AppTypes.UserType[i])) {
                returnValue = true;
                break;
            }

        }
        return returnValue;
    }

    private boolean isValidGenderValue(String gender, List<ResponseError> errors) {
        if(!validateIsGenderAllowed(gender))
        {
            errors.add(Errors.GenderValueNotCorrect_19);
                    return false;
        }
        if(!isNotNullAndNotEmpty(gender))
        {
            errors.add(Errors.GenderCanNotNull_18);
            return false;
        }
        return true;
    }

    private boolean validateIsGenderAllowed(String value) {
        boolean returnValue = false;
        for (int i=0;i<AppTypes.Gender.length;i++)
        {
            if(value.equalsIgnoreCase(AppTypes.Gender[i]))
            {
                returnValue =true;
                break;
            }
        }
        return returnValue;
    }

        private boolean isValidStatus(String status, List<ResponseError> errors) {
        if(!isStatusInAllowed(status)){
            errors.add(Errors.StatusValueNotCorrect_16);
            return false;
        }
        return true;
    }
    private boolean isStatusInAllowed(String value) {
        boolean returnValue = false;
        for (int i=0;i<AppTypes.Status.length;i++)
        {
            if(value.equalsIgnoreCase(AppTypes.Status[i]))
            {
                returnValue =true;
                break;
            }
        }
        return returnValue;
    }

    private boolean isValidLastName(String lastName, List<ResponseError> errors) {
        boolean validLastName = true;
        if(!isNotNullAndNotEmpty(lastName))
        {
            errors.add(Errors.LastNameCanNotNull_14);
            validLastName = false;
        }
        if(!isAllowedLength(lastName,45))
        {
            errors.add(Errors.LastNameOverSized_15);
            validLastName = false;
        }

        return validLastName;
    }
    private boolean isValidFirstName(String firstName, List<ResponseError> errors) {
        boolean validFirstName = true;
        if(!isNotNullAndNotEmpty(firstName))
        {
            errors.add(Errors.FirstNameCanNotNull_12);
            validFirstName = false;
        }
        if(!isAllowedLength(firstName,45))
        {
            errors.add(Errors.FirstNameLengthExceeds_13);
            validFirstName = false;
        }

        return validFirstName;
    }
    private boolean isValidLoginId(String loginId, Long id, List<ResponseError> errors) {

            boolean isLoginIdExist = false;
            if (id != null) {
                isLoginIdExist = userDao.isLoginIdExist(loginId, id);
            } else {
                isLoginIdExist = userDao.isLoginIdExist(loginId);

            }
            if (isLoginIdExist) {
                errors.add(Errors.LoginIdAlreadyExist_11);
                return false;
            } else
                return true;
        }
        private boolean isValidMobile(String mobileNo, Long id, List<ResponseError> errors) {
        if (!isNotNullAndNotEmpty(mobileNo)) {
            errors.add(Errors.MobileNoCanNotNull_8);
            return false;
        } else {
            boolean isMobileNumberExist = false;
            if (id != null) {
                isMobileNumberExist = userDao.isMobileNumberExist(mobileNo, id);
            } else {
                isMobileNumberExist = userDao.isMobileNumberExist(mobileNo);

            }
            // allowed length 10 check
            if (isMobileNumberExist) {
                errors.add(Errors.MobileNoAlreadyExist_9);
                return false;
            } else
                return true;
        }
    }
    private boolean isValidEmail(String emailId, Long id, List<ResponseError> errors) {
        if (!isNotNullAndNotEmpty(emailId)) {
            errors.add(Errors.EmailCanNotNull_6);
            return false;
        } else {
            // check valid email pattern
            boolean isemailExist = false;
            if (id != null) {
                isemailExist = userDao.isEmailExist(emailId, id);
            } else {
                isemailExist = userDao.isEmailExist(emailId);

            }
            if (isemailExist) {
                errors.add(Errors.EmailAlreadyExist_7);
                return false;
            } else
                return true;
        }
    }

}
