//package src.main.scheduler.model;
//
//import scheduler.db.ConnectionManager;
//import java.sql.Date;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Arrays;
//
//public class Availabilities {
//    private final Date date;
//    private final String care_username;
//
//    private Availabilities(AvailBuilder builder) {
//        this.date = builder.date;
//        this.care_username = builder.care_username;
//    }
//
//    private Availabilities(AvailGetter getter) {
//        this.date = getter.date;
//        this.care_username = getter.care_username;
//    }
//
//    //Getter
//    public Date getDate() {
//        return date;
//    }
//
//    public String getCare_username() {
//        return care_username;
//    }
//
//    public void saveToDB() throws SQLException {
//        ConnectionManager cm = new ConnectionManager();
//        Connection con = cm.createConnection();
//
//        String addCaregiver = "INSERT INTO Availabilities VALUES (?, ?)";
//        try {
//            PreparedStatement statement = con.prepareStatement(addCaregiver);
//            statement.setDate(1, this.date);
//            statement.setString(2, this.care_username);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new SQLException();
//        } finally {
//            cm.closeConnection();
//        }
//    }
//
//    public void uploadAvailability(Date d) throws SQLException {
//        ConnectionManager cm = new ConnectionManager();
//        Connection con = cm.createConnection();
//
//        String addAvailability = "INSERT INTO Availabilities VALUES (? , ?)";
//        try {
//            PreparedStatement statement = con.prepareStatement(addAvailability);
//            statement.setDate(1, this.date);
//            statement.setString(2, this.care_username);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new SQLException();
//        } finally {
//            cm.closeConnection();
//        }
//    }
//
//    public static class AvailBuilder {
//        private final Date date;
//        private final String care_username;
//
//        public AvailBuilder(Date date, String care_username) {
//            this.date = date;
//            this.care_username = care_username;
//        }
//
//        public Availabilities build() {
//            return new Availabilities(this);
//        }
//    }
//
//    public static class AvailGetter {
//        private final String username;
//        private final String password;
//        private byte[] salt;
//        private byte[] hash;
//
//        public AvailGetter(String username, String password) {
//            this.username = username;
//            this.password = password;
//        }
//
//        public Availabilities get() throws SQLException {
//            ConnectionManager cm = new ConnectionManager();
//            Connection con = cm.createConnection();
//
//            String getCaregiver = "SELECT Salt, Hash FROM Availabilities WHERE Username = ?";
//            try {
//                PreparedStatement statement = con.prepareStatement(getCaregiver);
//                statement.setString(1, this.username);
//                ResultSet resultSet = statement.executeQuery();
//                while (resultSet.next()) {
//                    byte[] salt = resultSet.getBytes("Salt");
//                    // we need to call Util.trim() to get rid of the paddings,
//                    // try to remove the use of Util.trim() and you'll see :)
//                    byte[] hash = scheduler.util.Util.trim(resultSet.getBytes("Hash"));
//                    // check if the password matches
//                    byte[] calculatedHash = scheduler.util.Util.generateHash(password, salt);
//                    if (!Arrays.equals(hash, calculatedHash)) {
//                        return null;
//                    } else {
//                        this.salt = salt;
//                        this.hash = hash;
//                        return new Availabilities(this);
//                    }
//                }
//                return null;
//            } catch (SQLException e) {
//                throw new SQLException();
//            } finally {
//                cm.closeConnection();
//            }
//        }
//    }
//
//}
