/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radicalsac.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Akinwale Agbaje The response model, used to convey middle tier
 * response to the front end.
 */
public class Response implements Serializable {

    private int retn;
    private String desc;
    private List<?> body;
    private List<?> restBody;
    private List<?> report;
    private Map<?, ?> errorReport;
    private boolean forceStopDueToError;
    private boolean chargeUser;

    /**
     * Gets the return code.
     *
     * @return the return code
     */
    public int getRetn() {
        return retn;
    }

    /**
     * Sets the response return code. The return code denotes the type of
     * response the middle sends, whether successful or an error.
     *
     * @param retn
     */
    public void setRetn(int retn) {
        this.retn = retn;
    }

    /**
     * Gets the response description.
     *
     * @return the response description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the response description. The response description describes the
     * nature of the response being sent from the middle-tier.
     *
     * @param desc the response description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets the response body.
     *
     * @return the response body
     */
    public List<?> getBody() {
        return body;
    }

    /**
     * Sets the body of the response. In some cases, responses from the
     * middle-tier come with objects (models). These objects are stored in the
     * body list.
     *
     * @param body the response body
     */
    public void setBody(List<?> body) {
        this.body = body;
    }

    /**
     * Gets the rest response body.
     *
     * @return the response body
     */
    public List<?> getRestBody() {
        return restBody;
    }

    /**
     * Sets the body of the rest response. In some cases, responses from the
     * middle-tier come with objects (models). These objects are stored in the
     * body list.
     *
     * @param restBody the response body
     */
    public void setRestBody(List<?> restBody) {
        this.restBody = restBody;
    }

    /**
     * Gets the response report
     *
     * @return the response report
     */
    public List<?> getReport() {
        return report;
    }

    /**
     * Sets the response report. Mostly used to carry multiple error /
     * consideration reports, which contain list indexes and error descriptions
     *
     * @param report the response report
     */
    public void setReport(List<?> report) {
        this.report = report;
    }

    /**
     * Gets the response error report.
     *
     * @return the response error report
     */
    public Map<?, ?> getErrorReport() {
        return errorReport;
    }

    /**
     * Sets the error report of the response. This error report structure is
     * designed for list structures whereby an error report must be sent back
     * after checking each value with the list.
     *
     * @param errorReport the response error report
     */
    public void setErrorReport(Map<?, ?> errorReport) {
        this.errorReport = errorReport;
    }

    /**
     * Gets the notification for the front-end to stop operations as a result of
     * certain error presence
     *
     * @return the error notification
     */
    public boolean isForceStopDueToError() {
        return forceStopDueToError;
    }

    /**
     * Sets the error notification of the response. This notification informs
     * the front-end or consumer that the back-end has encountered a specific
     * processing error that needs to be addressed before allowing users to
     * proceed
     *
     * @param forceStopDueToError
     */
    public void setForceStopDueToError(boolean forceStopDueToError) {
        this.forceStopDueToError = forceStopDueToError;
    }

    /**
     * Gets the charge status for a USSD call.
     * @return the charge status
     */
    public boolean isChargeUser() {
        return chargeUser;
    }

    /**
     * Sets the charge status meant for USSD interpretation.
     * @param chargeUser the charge status
     */
    public void setChargeUser(boolean chargeUser) {
        this.chargeUser = chargeUser;
    }
}
