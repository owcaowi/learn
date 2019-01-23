package com.example.owi.contentprovider;


public class Dogs {

    private Integer id;
    private String name;
    private String cartoon;

    public Dogs( Integer id, String name, String cartoon) {
        this.cartoon = cartoon;
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCartoon() {
        return cartoon;
    }

    public void setCartoon(String cartoon) {
        this.cartoon = cartoon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void  addNewDog(Integer id, String name, String cartoon) {
        this.id = id;
        this.name = name;
        this.cartoon = cartoon;
    }


}
