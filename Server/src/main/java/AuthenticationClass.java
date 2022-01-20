import java.util.ArrayList;
import java.util.List;

public class AuthenticationClass  implements Authentication{
    private List<UserData> users;
    public AuthenticationClass() {
        this.users = new ArrayList<>();
        users.add(new UserData("user1", "pass1", "nick1"));
        users.add(new UserData("user2", "pass2", "nick2"));
        users.add(new UserData("user3", "pass3", "nick3"));
        users.add(new UserData("user4", "pass4", "nick4"));
        users.add(new UserData("user5", "pass5", "nick5"));
    }

    private class UserData{
        String login;
        String password;
        String nickname;


        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;

        }

    }

    @Override
    public String getNickName(String login, String password) {

        for (UserData user: users) {
            if(user.login.equals(login)&&user.password.equals(password)){
                return user.nickname;
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nick) {
        for (UserData user: users) {
            if(user.login.equals(login)&&user.nickname.equals(nick)){
                return false;
            }
        }
        users.add(new UserData(login, password, nick));
        return true;
    }
}
