package com.danielpm1982.domain;

public class Client implements Comparable<Client>{
    private int clientId;
    private String name;
    private Address address;
    public Client(int clientId, String name, Address address) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
    @Override
    public int compareTo(Client client) {
        if(this.clientId==client.clientId){
            return 0;
        } else if(this.clientId>clientId){
            return 1;
        } else{
            return -1;
        }
    }
}
