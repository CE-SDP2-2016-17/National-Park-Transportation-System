/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author sagar
 */
public class Avail_transactions implements Serializable
{

    public int train_no;
    public String name;
    public String from;
    public String to;
    public String date;
    public String class_type;
    public int price;

    public Avail_transactions(int train_no, String name, String from, String to, String date, String class_type, int price)
    {
        this.train_no = train_no;
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.class_type = class_type;
        this.price = price;
    }

}
