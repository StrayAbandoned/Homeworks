import java.sql.*;

public class DbAuthentication implements Authentication{

    private Connection connection;
    private Statement stmt;

    public DbAuthentication() {
        try {
            connectDB();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //disconnectDB();
        }
    }



    private void connectDB () throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        stmt = connection.createStatement();
        System.out.println("Database connected");
    }
    private void disconnectDB(){
        try {
            if(!stmt.isClosed()){
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean changeNickname (String currentNick, String futureNick){
        try {
            PreparedStatement ps3 = connection.prepareStatement("SELECT nickname FROM userdata WHERE nickname = ?;");
            ps3.setString(1, futureNick);
            ResultSet rs = ps3.executeQuery();

            if(rs.next()){
                return false;
            }
            PreparedStatement ps = connection.prepareStatement("UPDATE userdata SET nickname = ? WHERE nickname = ?;");
            ps.setString(1, futureNick);
            ps.setString(2,currentNick);
            ps.executeUpdate();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String getNickName(String login, String password) {
        String nick = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT nickname FROM userdata WHERE login = ? AND password = ?;");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                nick = rs.getString(1);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nick;
    }

    @Override
    public boolean registration(String login, String password, String nick) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT login, nickname FROM userdata WHERE login = ? AND nickname = ?;");
            ps.setString(1, login);
            ps.setString(2, nick);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return false;
            }
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO userdata (login, password, nickname)  VALUES (?,?,?);");
            ps2.setString(1, login);
            ps2.setString(2, password);
            ps2.setString(3, nick);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
