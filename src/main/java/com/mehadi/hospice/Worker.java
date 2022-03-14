package com.mehadi.hospice;

abstract class Worker {
    public String Name;
    public String Phn;

    Worker()
    {
        //Default Constructor
    }
    Worker(String Name, String Phn)
    {
        this.Name = Name;
        this.Phn = Phn;

    }


}
