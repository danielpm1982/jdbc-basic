package com.danielpm1982.dao;
import com.danielpm1982.domain.Address;
import com.danielpm1982.domain.Client;
import java.sql.*;

public class DAO{
    final static String DB_URL = "jdbc:mysql://localhost:3306/clientDB";
    final static String USER = "root";
    final static String PASSWORD = "root";
    final static String T1 = "TRUNCATE TABLE client";
    final static String T2 = "TRUNCATE TABLE address";
    final static String Q1 = "SELECT * FROM client c JOIN address a ON c.address_fk=a.address_id";
    final static String Q2 = "SELECT * FROM (SELECT * FROM client c JOIN address a ON c.address_fk=a.address_id) r WHERE r.name LIKE ? ORDER BY name ASC";
    final static String Q3 = "SELECT * FROM client c WHERE c.client_id=?";
    final static String Q4 = "SELECT * FROM address a WHERE a.address_id=?";
    final static String I1 = "INSERT INTO client(client_id, name, address_fk) VALUES (?, ?, ?)";
    final static String I2 = "INSERT INTO address(address_id, street, number, city, state, country) VALUES (?, ?, ?, ?, ?, ?)";
    final static String D1 = "DELETE FROM client c WHERE c.client_id=?";
    final static String D2 = "DELETE FROM address a WHERE a.address_id=?";
    final static String U1 = "UPDATE client c SET name=?, address_fk=? WHERE c.client_id=?";
    final static String U2 = "UPDATE address a SET street=?, number=?, city=?, state=?, country=? WHERE a.address_id=?";
    public static final Connection connect(){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static final void disconnect(Connection conn){
        try {
            if(conn!=null&&!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static final void truncateDB(Connection conn){
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(T1);
            stmt.execute(T2);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static final ResultSet queryAllClients(Connection conn){
        try {
            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt1.executeQuery(Q1);
            return rs;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static final ResultSet queryPartialClients(Connection conn, String partialName){
        try {
            PreparedStatement pstmt = conn.prepareStatement(Q2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, "%"+partialName+"%");
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            return rs;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static final Client queryClientById(Connection conn, int clientId){
        try {
            PreparedStatement pstmt = conn.prepareStatement(Q3, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, clientId);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            if(rs.next()){
                Address address = queryAddressById(conn, rs.getInt("address_fk"));
                Client client = new Client(rs.getInt("client_id"), rs.getString("name"), address);
                return client;
            } else{
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static final Address queryAddressById(Connection conn, int addressId){
        try {
            PreparedStatement pstmt = conn.prepareStatement(Q4, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, addressId);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            if(rs.next()){
                Address address = new Address(rs.getInt("address_id"), rs.getString("street"), rs.getInt("number"), rs.getString("city"), rs.getString("state"), rs.getString("country"));
                return address;
            } else{
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static final void insert(Connection conn, Client client){
        Address address = client.getAddress();
        try {
            PreparedStatement pstmt1 = conn.prepareStatement(I1);
            pstmt1.setInt(1, client.getClientId());
            pstmt1.setString(2, client.getName());
            pstmt1.setInt(3, address.getAddressId());
            pstmt1.executeUpdate();
            PreparedStatement pstmt2 = conn.prepareStatement(I2);
            pstmt2.setInt(1, address.getAddressId());
            pstmt2.setString(2, address.getStreet());
            pstmt2.setInt(3, address.getNumber());
            pstmt2.setString(4, address.getCity());
            pstmt2.setString(5, address.getState());
            pstmt2.setString(6, address.getCountry());
            pstmt2.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static final void delete(Connection conn, int clientId){
        try {
            Client client = queryClientById(conn, clientId);
            PreparedStatement pstmt1 = conn.prepareStatement(D2);
            pstmt1.setInt(1, client.getAddress().getAddressId());
            pstmt1.executeUpdate();
            PreparedStatement pstmt2 = conn.prepareStatement(D1);
            pstmt2.setInt(1, client.getClientId());
            pstmt2.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static final void update(Connection conn, Client updatingClient){
        try {
            Address updatingAddress = updatingClient.getAddress();
            PreparedStatement pstmt1 = conn.prepareStatement(U2);
            pstmt1.setString(1, updatingAddress.getStreet());
            pstmt1.setInt(2, updatingAddress.getNumber());
            pstmt1.setString(3, updatingAddress.getCity());
            pstmt1.setString(4, updatingAddress.getState());
            pstmt1.setString(5, updatingAddress.getCountry());
            pstmt1.setInt(6, updatingAddress.getAddressId());
            pstmt1.executeUpdate();
            PreparedStatement pstmt2 = conn.prepareStatement(U1);
            pstmt2.setString(1, updatingClient.getName());
            pstmt2.setInt(2, updatingAddress.getAddressId());
            pstmt2.setInt(3, updatingClient.getClientId());
            pstmt2.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static final void print(ResultSet rs){
        try {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-8s\t", resultSetMetaData.getColumnName(i));
            }
            System.out.println();
            if(rs.next()){
                rs.beforeFirst();
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf("%-8s\t", rs.getObject(i));
                    }
                    System.out.println();
                }
            } else{
                System.out.println("No records at the DB. Empty DB !");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
