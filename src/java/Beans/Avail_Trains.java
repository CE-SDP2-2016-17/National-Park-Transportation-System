/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;

/**
 *
 * @author dell pc
 */
public class Avail_Trains
{

    private int train_no;
    private String name;
    private String arrival_time;
    private String dept_time;

    public Avail_Trains(int train_no, String name, String arrival_time, String dept_time)
    {
        this.train_no = train_no;
        this.name = name;
        this.arrival_time = arrival_time;
        this.dept_time = dept_time;
    }

    public Avail_Trains(int train_no, String name, String arrival_time, String dept_time, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Avail_Trains(int train_no, String name, String arrival_time, String dept_time, String j_date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName()
    {
        return name;
    }

    public int getTrain_no()
    {
        return train_no;
    }

    public String getArrival_time()
    {
        return arrival_time;
    }

    public String getDept_time()
    {
        return dept_time;
    }

}
