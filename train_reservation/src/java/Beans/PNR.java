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
public class PNR
{

    private int pnr;
    private int train_no;
    private String date;
    private String src;
    private String dest;
    private String s_type;
    private int seat_no;
    private String status;
    private String order_id;
    private int price;
    private String txn_id;
    /**
     * @return the pnr
     */
     public String getTxn_id()
    {
        return txn_id;
    }

    /**
     * @param pnr the pnr to set
     */
    public void setTxn_id(String txn_id)
    {
        this.txn_id = txn_id;
    }
    public int getPnr()
    {
        return pnr;
    }

    /**
     * @param pnr the pnr to set
     */
    public void setPnr(int pnr)
    {
        this.pnr = pnr;
    }
    public int getPrice()
    {
        return price;
    }

    /**
     * @param pnr the pnr to set
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     * @return the train_no
     */
    public int getTrain_no()
    {
        return train_no;
    }

    /**
     * @param train_no the train_no to set
     */
    public void setTrain_no(int train_no)
    {
        this.train_no = train_no;
    }

    /**
     * @return the date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date)
    {
        this.date = date;
    }
 public String getOrder_id()
    {
        return order_id;
    }

    /**
     * @param date the date to set
     */
    public void setOrder_id(String order_id)
    {
        this.order_id = order_id;
    }
    /**
     * @return the src
     */
    public String getSrc()
    {
        return src;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(String src)
    {
        this.src = src;
    }

    /**
     * @return the dest
     */
    public String getDest()
    {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(String dest)
    {
        this.dest = dest;
    }

    /**
     * @return the s_type
     */
    public String getS_type()
    {
        return s_type;
    }

    /**
     * @param s_type the s_type to set
     */
    public void setS_type(String s_type)
    {
        this.s_type = s_type;
    }

    /**
     * @return the seat_no
     */
    public int getSeat_no()
    {
        return seat_no;
    }

    /**
     * @param seat_no the seat_no to set
     */
    public void setSeat_no(int seat_no)
    {
        this.seat_no = seat_no;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

}
