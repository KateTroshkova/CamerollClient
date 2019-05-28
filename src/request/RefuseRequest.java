package request;

import data.Session;
import data.User;

public class RefuseRequest extends ChooseRequest {

    public RefuseRequest(User user, Session session, int x, int y) {
        super(user, session, x, y);
    }

    public RefuseRequest(){
        super();
    }

    @Override
    public void updatePattern(){
        String pattern=session.getPattern();
        String[] data=pattern.split(";");
        int width=Integer.valueOf(data[0]);
        int height=Integer.valueOf(data[1]);
        data[2+height*y+x]="0";//+user.getId();
        String result="";
        for(String place:data){
            result+=place+";";
        }
        session.setPattern(result);
    }
}
