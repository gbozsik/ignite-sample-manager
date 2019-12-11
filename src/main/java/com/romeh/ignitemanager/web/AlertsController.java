package com.romeh.ignitemanager.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.romeh.ignitemanager.entities.AlertEntry;
import com.romeh.ignitemanager.services.AlertsService;

/**
 * Created by romeh on 08/08/2017.
 */
@RestController
@RequestMapping("/alerts")
public class AlertsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertsController.class);

    @Autowired
    private AlertsService alertsService;

    @RequestMapping(value = "/{serviceId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AlertEntry> getServiceAlerts(@PathVariable final String serviceId) {
        LOGGER.debug("Trying to retrieve alerts by ID: {}", serviceId);
        return alertsService.getAlertForServiceId(serviceId);
    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AlertEntry> getAllAlerts() {
        LOGGER.debug("Trying to retrieve all alerts");
        return alertsService.getAllAlerts();

    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void createAlert(@Valid @RequestBody AlertEntry request) {
        LOGGER.debug("Trying to create a alert: {}", request.toString());
        alertsService.createAlertEntry(request);
    }


    @RequestMapping(value = "/{serviceId}/{errorCodeId}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void updateAlert(@PathVariable final String serviceId, @PathVariable final String errorCodeId,
                            @Valid @RequestBody AlertEntry request) {
        LOGGER.debug("Trying to update a alert: {}", request.toString());
        alertsService.updateAlertEntry(serviceId, errorCodeId, request);

    }


    @RequestMapping(value = "/{alertId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteAlert(@PathVariable final String alertId) {
        LOGGER.debug("Trying to delete a alert: {},{}", alertId);
        alertsService.deleteAlertEntry(alertId);
    }


}
