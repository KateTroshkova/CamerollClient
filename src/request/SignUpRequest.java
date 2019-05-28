package request;


import data.Registration;

public class SignUpRequest implements Request {

    private Registration registration;

    public SignUpRequest(Registration registration){
        this.registration=registration;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
