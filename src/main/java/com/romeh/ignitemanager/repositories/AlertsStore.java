package com.romeh.ignitemanager.repositories;

import java.util.List;

import com.romeh.ignitemanager.entities.AlertEntry;

/**
 * Created by romeh on 08/08/2017.
 */
public interface AlertsStore {
    List<AlertEntry> getAlertForServiceId(String serviceId);

    void updateAlertEntry(String serviceId, String errorCode, AlertEntry alertEntry);

    List<AlertEntry>  getAllAlerts();

    void deleteAlertEntry(String alertId);

    void createAlertEntry(AlertEntry alertEntry);


}
