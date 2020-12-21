package io.mintit.myapplication.Model;

public class ItemPost {
    private String name;
    private String offres;
    private String description;
    private String photo;
    private String dateEnd;
    private String dateStart;
    private int nbpart;
    private boolean participate;
    private int reservation;
    private String titre;
    private String datejj;
    private String datemm;

    public ItemPost(String name,String offres,String description,String photo,String dateStart,String dateEnd,int nbpart,boolean participate,int reservation,String titre,String datejj,String datemm){
        this.titre=titre;
        this.name=name;
        this.offres=offres;
        this.photo=photo;
        this.dateStart=dateStart;
        this.dateEnd=dateEnd;
        this.nbpart=nbpart;
        this.reservation=reservation;
        this.description=description;
        this.participate=participate;
        this.datejj=datejj;
        this.datemm=datemm;
    }

    public String getDatejj() {
        return datejj;
    }

    public void setDatejj(String datejj) {
        this.datejj = datejj;
    }

    public String getDatemm() {
        return datemm;
    }

    public void setDatemm(String datemm) {
        this.datemm = datemm;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffres() {
        return offres;
    }

    public void setOffres(String offres) {
        this.offres = offres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public int getNbpart() {
        return nbpart;
    }

    public void setNbpart(int nbpart) {
        this.nbpart = nbpart;
    }

    public boolean isParticipate() {
        return participate;
    }

    public void setParticipate(boolean participate) {
        this.participate = participate;
    }
}

