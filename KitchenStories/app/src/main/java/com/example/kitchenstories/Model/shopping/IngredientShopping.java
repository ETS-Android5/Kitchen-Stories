package com.example.kitchenstories.Model.shopping;

public class IngredientShopping {
    private int id;
    private int idShopping;
    private String nameIngre;
    private String amountIngre;
    private int Status;

    public IngredientShopping(int id,int idShopping, String nameIngre, String amountIngre, int status) {
        this.id = id;
        this.idShopping = idShopping;
        this.nameIngre = nameIngre;
        this.amountIngre = amountIngre;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameIngre() {
        return nameIngre;
    }

    public void setNameIngre(String nameIngre) {
        this.nameIngre = nameIngre;
    }

    public String getAmountIngre() {
        return amountIngre;
    }

    public void setAmountIngre(String amountIngre) {
        this.amountIngre = amountIngre;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getIdShopping() {
        return idShopping;
    }

    public void setIdShopping(int idShopping) {
        this.idShopping = idShopping;
    }

}

