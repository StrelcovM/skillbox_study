import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "ьщылщмлдшт";
        String query = "select p.course_name, count(p.subscription_date) / (max(month(p.subscription_date)) - min(month(p.subscription_date)) + 1)\n" +
                "from purchaselist p \n" +
                "where year(p.subscription_date) = 2018\n" +
                "group by p.course_name;";

        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}