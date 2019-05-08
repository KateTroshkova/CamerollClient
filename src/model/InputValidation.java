package model;

public class InputValidation {

    public boolean validName(String name){
        return !name.isEmpty() && !tooShort(name) && !tooLong(name) && startWithLetter(name);
    }

    public boolean validPassword(String password){
        return !password.isEmpty() && !tooLong(password) && !tooShort(password) && validSymbols(password);
    }

    public boolean validConfirm(String password, String confirm){
        return validPassword(confirm) && password.equals(confirm);
    }

    public boolean validManager(String pass){
        return pass.equals("42");
    }

    private boolean tooLong(String data){
        return  data.length()>24;
    }

    private boolean tooShort(String data){
        return data.length()<3;
    }

    private boolean startWithLetter(String data){
        return Character.isLetter(data.charAt(0));
    }

    private boolean validSymbols(String data){
        for(int i=0; i<data.length(); i++){
            if (!Character.isLetter(data.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
