/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

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