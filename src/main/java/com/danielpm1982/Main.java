package com.danielpm1982;
import com.danielpm1982.dao.DAO;
import com.danielpm1982.domain.Address;
import com.danielpm1982.domain.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

public class Main {
    private static Connection conn;
    private static Set<Client> clientSet;
    public static void main(String[] args) {
        System.out.println("\nConnecting to Database...");
        conn = DAO.connect();
        System.out.println("Connected !");
        System.out.println("\nTruncating Database...");
        DAO.truncateDB(conn);
        System.out.println("Truncated !");
        queryAndPrintClientSet();
        System.out.println("\nCreating population of Client instances...");
        clientSet = createClientSet();
        System.out.println("Population created !");
        System.out.println("\nInserting population into Database...");
        insertClientSet();
        System.out.println("Inserted !");
        queryAndPrintClientSet();
        System.out.println("\nFinding Client with id = 789456123...");
        Client c = findClientById(789456123);
        System.out.println(c);
        System.out.println("Client found !");
        System.out.println("\nUpdating Client with id = 789456123...");
        c.getAddress().setStreet("streetN");
        c.getAddress().setNumber(999);
        c.getAddress().setCity("cityN");
        c.getAddress().setState("stateN");
        c.getAddress().setCountry("countryN");
        c.setName("clientN");
        updateClient(c);
        c = findClientById(789456123);
        System.out.println(c);
        System.out.println("Client updated !");
        System.out.println("\nDeleting client with id = 789456123...");
        deleteClientById(789456123);
        System.out.println("Client deleted !");
        System.out.println("\nDisconnecting from Database...");
        DAO.disconnect(conn);
        System.out.println("Disconnected !");
        System.out.println("\nSuccessfully executed !\n");
    }
    private static Set<Client> createClientSet(){
        Address a1 = new Address(1, "street1", 100, "city1", "state1", "country1");
        Client c1 = new Client(123456789, "client1", a1);
        Address a2 = new Address(2, "street2", 200, "city2", "state2", "country2");
        Client c2 = new Client(456789123, "client2", a2);
        Address a3 = new Address(3, "street3", 300, "city3", "state3", "country3");
        Client c3 = new Client(789456123, "client3", a3);
        Set<Client> clientSet = new TreeSet<>();
        clientSet.addAll(Arrays.asList(c1, c2, c3));
        return clientSet;
    }
    private static void insertClientSet(){
        clientSet.forEach(x->DAO.insert(conn, x));
    }
    private static void queryAndPrintClientSet(){
        System.out.println("\nQuerying all clients...");
        ResultSet rs1 = DAO.queryAllClients(conn);
        System.out.println("Printing all clients...");
        DAO.print(rs1);
        System.out.println("\nQuerying specific clients whose name contains %c%...");
        ResultSet rs2 = DAO.queryPartialClients(conn, "c");
        System.out.println("Printing specific clients whose name contains %c%...");
        DAO.print(rs2);
    }
    private static Client findClientById(int clientId){
        return DAO.queryClientById(conn, clientId);
    }
    private static void updateClient(Client client){
        DAO.update(conn, client);
    }
    private static void deleteClientById(int clientId){
        DAO.delete(conn, clientId);
    }
}
