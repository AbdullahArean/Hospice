package com.aem.hospice.Classes;

public interface ClassDBConnector{
    void InsertIntoDatabase(RealEntity person);
    void UpdateIntoDatabase(RealEntity person);
    void InsertFromDatabase(RealEntity person);
}
