package com.bsil.swaroop.mycontacts;

/**
 * Created by swaroop on 15/04/15.
 */
public class Contact
{
    public String name;
    public String cell_no;


    Contact(){}

    public Contact(String name, String cell_no) {
        this.name = name;
        this.cell_no = cell_no;

    }

    public String getCell_no() {
        return cell_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCell_no(String cell_no) {
        this.cell_no = cell_no;
    }
}
