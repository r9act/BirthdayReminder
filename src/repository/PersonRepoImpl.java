package repository;

import model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepoImpl implements PersonRepo {

    private static final String URL = "jdbc:postgresql://localhost:5432/BirthdayReminder";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private final List<Person> list;


    public PersonRepoImpl(List<Person> list) {
        this.list = list;
    }

    public void addAll() throws SQLException {

        List<Person> people = list;
        String sql = "INSERT INTO Person (name, birthday) values(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (Person p : list) {
            ps.setString(1, p.getName());
            ps.setDate(2, Date.valueOf(p.getBirthDate()));
            ps.executeUpdate();
        }
    }

    @Override
    public Optional<Person> addOne(String name, int updateDay, int updateMonth, int updateYear) throws SQLException {

        LocalDate birthday = LocalDate.of(updateYear, updateMonth, updateDay);
        Person person = new Person(birthday, name);

        String sql1 = "SELECT * FROM Person WHERE name = ?";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setString(1, name);
        ResultSet resultSet = ps1.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No such person, added new");
            String sql2 = "INSERT INTO Person  (birthday, name) VALUES (?,?)";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setDate(1, Date.valueOf(birthday));
            ps2.setString(2, name);
            ps2.executeUpdate();
            return Optional.of(person);
        } else {
            System.out.println("Person exists");
            return Optional.of(person);
        }
    }

    @Override
    public List<Person> getAll() throws SQLException {
        List<Person> listToShow = new ArrayList<>();
        String sql = "SELECT * FROM Person";
        Statement ps = connection.createStatement();
        ResultSet resultSet = ps.executeQuery(sql);
        while (resultSet.next()) {
            Person person = new Person(resultSet.getDate("birthday").toLocalDate(), resultSet.getString("name"));
            listToShow.add(person);
        }
        return new ArrayList<>(listToShow);
    }

    @Override
    public Optional<Person> findByName(String name) throws SQLException {               //как получить ИМЯ+ДР -- можем изъять это из Optional.of(p)
        Person person;
        String sql = "SELECT * FROM Person WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            person = new Person(resultSet.getDate("birthday").toLocalDate(), resultSet.getString("name"));
            return Optional.of(person);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> updateByName(String name, int updateDay, int updateMonth, int updateYear) throws SQLException {
        LocalDate newBirthday = LocalDate.of(updateYear, updateMonth, updateDay);
        String sql1 = "SELECT * FROM Person WHERE name = ?";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setString(1, name);
        ResultSet resultSet = ps1.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("name").equals(name)) {
                String sql2 = "UPDATE Person SET birthday=? WHERE name =?";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setDate(1, Date.valueOf(newBirthday));
                ps2.setString(2, name);
                ps2.executeUpdate();
                Person person = new Person(newBirthday, name);
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

//Через итератор
//    public void removeByName(String name){
//        Iterator<Person> itr = list.iterator();
//        while (itr.hasNext()){
//            Person next = itr.next();
//            if(next.getName().equals(name)) itr.remove();
//        }
//    }

    @Override
    public Optional<Person> removeByName(String name) throws SQLException {         //вернул вместо Person, имя, потому что из-за лямбды переменная p не в скопе
        String sql1 = "SELECT * FROM Person WHERE name =?";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setString(1, name);
        ResultSet resultSet = ps1.executeQuery();

        Person person;
        while (resultSet.next()) {
            if (resultSet.getString("name").equals(name)) {
                String sql2 = "DELETE FROM Person WHERE name=?";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setString(1, name);
                ps2.executeUpdate();
            }
            person = new Person(resultSet.getDate("birthday").toLocalDate(), resultSet.getString("name"));
            return Optional.of(person);
        }
        return Optional.empty();
    }


    @Override
    public void removeAll() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = "Drop table person";
        ResultSet resultSet = statement.executeQuery(SQL);
    }


}

