package model;

public class Client extends User {

    private ClientSector sector;

    public Client(String id, String fullName, int sector){
        super(id, fullName);
        switch (sector) {
            case 1 -> this.sector = ClientSector.HEALTH;
            case 2 -> this.sector = ClientSector.FINANCIAL;
            case 3 -> this.sector = ClientSector.SERVICE;
            case 4 -> this.sector = ClientSector.ENERGETIC;
        }
    }

    public void setSector(int sector){
        switch (sector){
            case 1 -> this.sector = ClientSector.HEALTH;
            case 2 -> this.sector = ClientSector.FINANCIAL;
            case 3 -> this.sector = ClientSector.SERVICE;
            case 4 -> this.sector = ClientSector.ENERGETIC;
        }
    }

    public String toString(){
        return super.toString() +
                "Sector: " + sector.name();
    }

}
