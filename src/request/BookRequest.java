package request;

import data.Session;
import data.User;

public class BookRequest extends ChooseRequest {

    public BookRequest(User user, Session session, int x, int y) {
        super(user, session, x, y);
    }

    public BookRequest(){
        super();
    }

    @Override
    public void updatePattern(){
        String pattern=session.getPattern();
        String[] data=pattern.split(";");
        int width=Integer.valueOf(data[0]);
        int height=Integer.valueOf(data[1]);
        data[2+height*y+x]=""+user.getId();
        String result="";
        for(String place:data){
            result+=place+";";
        }
        session.setPattern(result);
    }
}
