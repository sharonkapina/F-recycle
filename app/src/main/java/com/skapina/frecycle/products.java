//Add to package
package com.skapina.frecycle;
// This class simply creates an object that will obtain data from the database and store it for use by any of 3 methods.
public class products {
    //Declare variables
    public String name, description, manufactor, barcodefmt, image, barcodecnt;

    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) { this.image = image; }

    //Constructor
    public products(String name, String description, String manufactor, String barcodefmt, String image, String barcodecnt) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
    //Empty constructor
    public products(){}

}//End class
