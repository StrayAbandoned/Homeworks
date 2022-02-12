public interface Authentication {
    String getNickName(String login, String password);
    boolean registration (String login, String password, String nick);
}
