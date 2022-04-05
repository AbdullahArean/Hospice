package com.aem.hospice.Classes;

public interface ClassDBConnector{
    void InsertIntoDatabase(RealEntity entity);
    void UpdateIntoDatabase(RealEntity entity);
    void InsertFromDatabase(RealEntity entity);
}
