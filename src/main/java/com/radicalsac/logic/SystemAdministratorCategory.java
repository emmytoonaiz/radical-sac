/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radicalsac.logic;

import com.easycoop.radical.database.layer.entity.Cooperative;
import com.easycoop.radical.database.layer.entity.CooperativeLicense;
import com.easycoop.radical.database.layer.entity.PeriodType;
import com.radicalsac.models.CooperativeApplication;
import com.radicalsac.models.CooperativeLicenceActivation;
import com.radicalsac.models.CooperativeDetails;
import com.radicalsac.models.ForwardMemberApplication;
import com.radicalsac.models.MemberCooperativeApplication;
import com.radicalsac.models.MemberProfile;
import com.radicalsac.models.SystemAdminProfile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.exchangepoint.resource.EmailSender;
import org.greenpole.entity.response.Response;
import org.greenpole.entity.security.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author emmanuel.idoko
 */
public class SystemAdministratorCategory {

    private final EmailSender sender = new EmailSender();
    private static final Logger logger = LoggerFactory.getLogger(SystemAdministratorCategory.class);
    private final Util util = new Util();

    /**
     * Request to create system administrator account
     *
     * @param login the logged in user
     * @param profile the system administrator details to be created
     * @return response to the create administrator request
     */
    public Response createSystemAdmin_Request(Login login, SystemAdminProfile profile) {
        Response resp = new Response();
        logger.info("Request to create system administrator account, invoked by [{}]", login.getUserId());

        String desc = "";
        boolean flag;

        try {
            if (profile.getFirstName() == null || profile.getFirstName().trim().isEmpty()) {
                desc = "\nFirstname must be specified.";
                flag = true;
            } else if (profile.getLastName() == null || profile.getLastName().trim().isEmpty()) {
                desc += "\nLastname must be specified.";
                flag = true;
            } else if (profile.getEmail() == null || profile.getEmail().trim().isEmpty()) {
                desc += "\nEmail must be specified.";
                flag = true;
            } else if (profile.getPhoneNumber() == null || profile.getPhoneNumber().trim().isEmpty()) {
                desc += "\nPhone number must be specified.";
                flag = true;
            } else if (profile.getPosition() == null || profile.getPosition().trim().isEmpty()) {
                desc += "\nPosition must be specified.";
                flag = true;
            } else {
                flag = false;
                logger.info("All checks passed in system administrator creation, invoked by [{}]", login.getUserId());
            }
            if (flag) {
                resp.setRetn(300);
                resp.setDesc("Found error while carrying out necessary checks.\n" + desc);
                logger.info("Found error while carrying out necessary checks: [{}], invoked by [{}]", desc, login.getUserId());
                return resp;

            } else {
                MemberProfile profile_hib = new MemberProfile();//replace this with hibernate entity
                profile_hib.setFirstName(profile.getFirstName());
                profile_hib.setMiddleName(profile.getMiddleName());
                profile_hib.setLastName(profile.getLastName());
                profile_hib.setEmailAddress(profile.getEmail());
                profile_hib.setPhoneNumber(profile.getPhoneNumber());
                profile_hib.setCooperativeId(0);

                //call hibernate method to create system administrator
                boolean created = false;
                if (created) {

                    String admin_full_name = profile.getLastName();
                    if (profile.getMiddleName() != null && !profile.getMiddleName().trim().isEmpty()) {
                        admin_full_name += " " + profile.getMiddleName();
                    }
                    admin_full_name += " " + profile.getLastName();

                    List<SystemAdminProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here
                    List<String> admin_emails = new ArrayList<>();
                    for (SystemAdminProfile email : all_system_admin_hib) {
                        admin_emails.add(email.getEmail());
                    }
                    String subject = "System Admin Creation!";
                    String emailBody = "<p>The system has successfully created the system admin </p>\n" + admin_full_name;
                    sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                    logger.info("Email sent to admin(s) {}, for system admin account creation. [{}]", admin_emails, login.getUserId());

                    //send notification message here
                    resp.setRetn(0);
                    resp.setDesc("Successful.");
                    return resp;

                } else {
                    resp.setRetn(300);
                    resp.setDesc("Cooperative creation failed due to error.");
                    logger.info("Cooperative creation failed due to error. [{}] ", login.getUserId());
                    return resp;
                }

            }
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to create system administrator account. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error creating system administrator account. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error creating system administrator account - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to create a new cooperative
     *
     * @param login the logged in user
     * @param coopDetails the cooperative details
     * @return response to the create cooperative request
     */
    public Response setUpCooperative_Request(Login login, CooperativeDetails coopDetails) {
        Response resp = new Response();
        logger.info("Request to setup cooperative, invoked by [{}]", login.getUserId());

        String desc = "";
        boolean flag;
        try {
            if (coopDetails.getName() == null || coopDetails.getName().trim().isEmpty()) {
                desc = "\nCooperative name must be specified.";
                flag = true;
            } else if (coopDetails.getEmail() == null || coopDetails.getEmail().trim().isEmpty()) {
                desc += "\nEmail must be specified.";
                flag = true;
            } else if (coopDetails.getAddressLine1() == null || coopDetails.getAddressLine1().trim().isEmpty()) {
                desc += "\nAddress Line one must be specified.";
                flag = true;
            } else if (coopDetails.getState() == null || coopDetails.getState().trim().isEmpty()) {
                desc += "\nState must be specified.";
                flag = true;
            } else if (coopDetails.getCountry() == null || coopDetails.getCountry().trim().isEmpty()) {
                desc += "\nCountry must be specified.";
                flag = true;
            } else if (coopDetails.getContributionFrequency() == null || coopDetails.getContributionFrequency().trim().isEmpty()) {
                desc += "\nFrequency of contribution must be specified.";
                flag = true;
            } else if (coopDetails.getContibutionCurrency() == null || coopDetails.getContibutionCurrency().trim().isEmpty()) {
                desc += "\nContribution currency must be specified.";
                flag = true;
            } else if (coopDetails.getContibutionCurrency() == null || coopDetails.getContibutionCurrency().trim().isEmpty()) {
                desc += "\nContribution currency must be specified.";
                flag = true;
            } else if (coopDetails.getContributionAmount() <= 0) {
                desc += "\nContribution amount is improperly set.";
                flag = true;
            } else {
                flag = false;
                logger.info("All checks passed in cooperative account creation, invoked by [{}]", login.getUserId());
            }

            if (flag) {
                resp.setRetn(300);
                resp.setDesc("Found error while carrying out necessary checks.\n" + desc);
                logger.info("Found error while carrying out necessary checks: [{}], invoked by [{}]", desc, login.getUserId());
                return resp;

            } else {
                CooperativeDetails coop_profile_hib = new CooperativeDetails();//replace this with hibernate entity
                coop_profile_hib.setName(coopDetails.getName());
                coop_profile_hib.setEmail(coopDetails.getEmail());
                coop_profile_hib.setPhoneNumber(coopDetails.getPhoneNumber());
                coop_profile_hib.setActive(false);
                coop_profile_hib.setAddressLine1(coopDetails.getAddressLine1());
                coop_profile_hib.setAddressLine2(coopDetails.getAddressLine2());
                coop_profile_hib.setAddressLine3(coopDetails.getAddressLine3());
                coop_profile_hib.setState(coopDetails.getState());
                coop_profile_hib.setCountry(coopDetails.getCountry());
                coop_profile_hib.setInterestRate(coopDetails.getInterestRate());
                coop_profile_hib.setContributionFrequency(coopDetails.getContributionFrequency());
                coop_profile_hib.setContibutionCurrency(coopDetails.getContibutionCurrency());
                coop_profile_hib.setContributionAmount(coopDetails.getContributionAmount());
                coop_profile_hib.setOverrideMemContribFreq(coopDetails.isOverrideMemContribFreq());
                coop_profile_hib.setLoanPayOffAllowed(coopDetails.isLoanPayOffAllowed());
                coop_profile_hib.setOverrideMemberContributionAmount(coopDetails.isOverrideMemberContributionAmount());
                //call hibernate method to create system administrator
                boolean created = false;
                if (created) {

                    List<SystemAdminProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here
                    List<String> admin_emails = new ArrayList<>();
                    for (SystemAdminProfile email : all_system_admin_hib) {
                        admin_emails.add(email.getEmail());
                    }
                    String subject = "Cooperative creation";
                    String emailBody = "<p>The system has successfully created the cooperative with name [" + coopDetails.getName() + "] </p>\n";
                    sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                    logger.info("Email sent to admin(s) {}, for cooperative account creation. [{}]", admin_emails, login.getUserId());

                    //send notification message here
                    resp.setRetn(0);
                    resp.setDesc("Cooperative created successfully.");
                    return resp;

                }

            }

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to create cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error creating cooperative. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error creating cooperative - [" + login.getUserId() + "]", ex);
            return resp;
        }
        return resp;
    }

    /**
     * Request to query all cooperatives
     *
     * @param login the logged in user
     * @return response to the query all cooperatives request
     */
    public Response queryAllCooperatives_Request(Login login) {
        Response resp = new Response();
        logger.info("Request to query all cooperatives, invoked by [{}]", login.getUserId());

        try {
            List<CooperativeDetails> coop_list_hib = new ArrayList<>();//rerplace with hibernate method
            List<CooperativeDetails> coop_list_out = new ArrayList<>();
            for (CooperativeDetails coop : coop_list_hib) {
                CooperativeDetails coop_model = new CooperativeDetails();
                coop_model.setName(coop.getName());
                coop_model.setEmail(coop.getEmail());
                coop_model.setPhoneNumber(coop.getPhoneNumber());
                coop_model.setActive(coop.isActive());

                String address = coop.getAddressLine1();
                if (coop.getAddressLine2() != null && !coop.getAddressLine2().trim().isEmpty()) {
                    address += " " + coop.getAddressLine2();
                }
                if (coop.getAddressLine3() != null && !coop.getAddressLine3().trim().isEmpty()) {
                    address += " " + coop.getAddressLine3();
                }
                if (coop.getState() != null && !coop.getState().trim().isEmpty()) {
                    address += " " + coop.getState();
                }
                if (coop.getCountry() != null && !coop.getCountry().trim().isEmpty()) {
                    address += " " + coop.getCountry();
                }
                coop_model.setFullAddress(address);
                coop_list_out.add(coop_model);
            }
            resp.setRetn(0);
            resp.setDesc("Successful");
            resp.setBody(coop_list_out);
            logger.info("Cooperatives queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query all cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying cooperative. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying cooperative - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    public Response queryCooperativeById_Request(Login login, int cooperativeId) {
        Response resp = new Response();
        logger.info("Request to query all cooperatives, invoked by [{}]", login.getUserId());

        try {
            List<CooperativeDetails> coop_list_hib = new ArrayList<>();//rerplace with hibernate method
            List<CooperativeDetails> coop_list_out = new ArrayList<>();

            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified cooperative does not exist, please contact system administrator.");
                logger.info("The specified cooperative [{}] does not exist, please contact system administrator. [{}] ", cooperativeId, login.getUserId());
                return resp;
            }

            for (CooperativeDetails coop : coop_list_hib) {
                CooperativeDetails coop_model = new CooperativeDetails();
                coop_model.setName(coop.getName());
                coop_model.setEmail(coop.getEmail());
                coop_model.setPhoneNumber(coop.getPhoneNumber());
                coop_model.setActive(coop.isActive());
                coop_model.setAddressLine1(coop.getAddressLine1());
                coop_model.setAddressLine2(coop.getAddressLine2());
                coop_model.setAddressLine3(coop.getAddressLine3());
                coop_model.setState(coop.getState());
                coop_model.setCountry(coop.getCountry());
                coop_model.setInterestRate(coop.getInterestRate());
                coop_model.setContributionFrequency(coop.getContributionFrequency());
                coop_model.setContibutionCurrency(coop.getContibutionCurrency());
                coop_model.setContributionAmount(coop.getContributionAmount());
                coop_list_out.add(coop_model);
            }
            resp.setRetn(0);
            resp.setDesc("Successful");
            resp.setBody(coop_list_out);
            logger.info("Cooperative queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying cooperative by ID. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying cooperative by ID - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to edit an existing cooperative
     *
     * @param login the logged in user
     * @param coopDetails the cooperative details
     * @return response to the edit cooperative request
     */
    public Response editCooperative_Request(Login login, CooperativeDetails coopDetails) {
        Response resp = new Response();
        logger.info("Request to edit cooperative, invoked by [{}]", login.getUserId());

        String desc = "";
        boolean flag;
        try {
            if (!true) {//replace with check for cooperative exist by ID
                resp.setRetn(300);
                resp.setDesc("The specified cooperative does not exist.");
                logger.info("The specified cooperative [{}] does not exist.", coopDetails.getId(), login.getUserId());
                return resp;
            }

            if (coopDetails.getName() == null || coopDetails.getName().trim().isEmpty()) {
                desc = "\nCooperative name must be specified.";
                flag = true;
            } else if (coopDetails.getEmail() == null || coopDetails.getEmail().trim().isEmpty()) {
                desc += "\nEmail must be specified.";
                flag = true;
            } else if (coopDetails.getAddressLine1() == null || coopDetails.getAddressLine1().trim().isEmpty()) {
                desc += "\nAddress Line one must be specified.";
                flag = true;
            } else if (coopDetails.getState() == null || coopDetails.getState().trim().isEmpty()) {
                desc += "\nState must be specified.";
                flag = true;
            } else if (coopDetails.getCountry() == null || coopDetails.getCountry().trim().isEmpty()) {
                desc += "\nCountry must be specified.";
                flag = true;
            } else if (coopDetails.getContributionFrequency() == null || coopDetails.getContributionFrequency().trim().isEmpty()) {
                desc += "\nFrequency of contribution must be specified.";
                flag = true;
            } else if (coopDetails.getContibutionCurrency() == null || coopDetails.getContibutionCurrency().trim().isEmpty()) {
                desc += "\nContribution currency must be specified.";
                flag = true;
            } else if (coopDetails.getContibutionCurrency() == null || coopDetails.getContibutionCurrency().trim().isEmpty()) {
                desc += "\nContribution currency must be specified.";
                flag = true;
            } else if (coopDetails.getContributionAmount() <= 0) {
                desc += "\nContribution amount is improperly set.";
                flag = true;
            } else {
                flag = false;
                logger.info("All checks passed in cooperative account creation, invoked by [{}]", login.getUserId());
            }

            if (flag) {
                resp.setRetn(300);
                resp.setDesc("Found error while carrying out necessary checks.\n" + desc);
                logger.info("Found error while carrying out necessary checks: [{}], invoked by [{}]", desc, login.getUserId());
                return resp;

            } else {
                CooperativeDetails coop_profile_hib = new CooperativeDetails();//replace this with hibernate entity
                coop_profile_hib.setName(coopDetails.getName());
                coop_profile_hib.setEmail(coopDetails.getEmail());
                coop_profile_hib.setPhoneNumber(coopDetails.getPhoneNumber());
                coop_profile_hib.setActive(coopDetails.isActive());
                coop_profile_hib.setAddressLine1(coopDetails.getAddressLine1());
                coop_profile_hib.setAddressLine2(coopDetails.getAddressLine2());
                coop_profile_hib.setAddressLine3(coopDetails.getAddressLine3());
                coop_profile_hib.setState(coopDetails.getState());
                coop_profile_hib.setCountry(coopDetails.getCountry());
                coop_profile_hib.setInterestRate(coopDetails.getInterestRate());
                coop_profile_hib.setLoanPayOffAllowed(coopDetails.isLoanPayOffAllowed());
                coop_profile_hib.setContributionFrequency(coopDetails.getContributionFrequency());
                coop_profile_hib.setOverrideMemContribFreq(coopDetails.isOverrideMemContribFreq());
                coop_profile_hib.setContibutionCurrency(coopDetails.getContibutionCurrency());
                coop_profile_hib.setContributionAmount(coopDetails.getContributionAmount());
                coop_profile_hib.setOverrideMemberContributionAmount(coopDetails.isOverrideMemberContributionAmount());
                //call hibernate method to create system administrator
                boolean created = false;
                if (created) {

                    List<SystemAdminProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here
                    List<String> admin_emails = new ArrayList<>();
                    for (SystemAdminProfile email : all_system_admin_hib) {
                        admin_emails.add(email.getEmail());
                    }
                    String subject = "Cooperative creation";
                    String emailBody = "<p>The system has successfully created the cooperative with name [" + coopDetails.getName() + "] </p>\n";
                    sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                    logger.info("Email sent to admin(s) {}, for cooperative account creation. [{}]", admin_emails, login.getUserId());

                    //send notification message here
                    resp.setRetn(0);
                    resp.setDesc("Cooperative created successfully.");
                    logger.info("Cooperative created successfully. [{}] ", login.getUserId());
                    return resp;

                } else {
                    resp.setRetn(300);
                    resp.setDesc("Cooperative creation failed due to error.");
                    logger.info("Cooperative creation failed due to error. [{}] ", login.getUserId());
                    return resp;
                }

            }

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to create cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error creating cooperative. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error creating cooperative - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to view requests to create cooperatives
     *
     * @param login the logged in user
     * @return response to the query all applications to create cooperative
     * request
     */
    public Response viewRequestToCreateCooperatives_Request(Login login) {
        Response resp = new Response();
        logger.info("Request to query all applications to create cooperative, invoked by [{}]", login.getUserId());

        try {
            List<CooperativeApplication> coop_app_hib_list = new ArrayList<>();//rerplace with hibernate query to get all pending application
            List<CooperativeApplication> coop_list_out = new ArrayList<>();
            if (coop_app_hib_list.isEmpty()) {
                resp.setRetn(300);
                resp.setDesc("There are no pending application for cooperative creation.");
                logger.info("There are no pending application for cooperative creation - [{}] ", login.getUserId());
                return resp;
            }

            for (CooperativeApplication coop : coop_app_hib_list) {
                CooperativeApplication coop_model = new CooperativeApplication();
                coop_model.setFirstName(coop.getFirstName());
                coop_model.setMiddlieName(coop.getMiddlieName());
                coop_model.setLastName(coop.getLastName());
                coop_model.setEmail(coop.getEmail());
                coop_model.setPhoneNumber(coop.getPhoneNumber());
                coop_model.setApplicationDate(coop.getApplicationDate());
                coop_model.setNameOfCooperative(coop.getNameOfCooperative());
                coop_list_out.add(coop_model);
            }
            resp.setRetn(0);
            resp.setDesc("Successful");
            resp.setBody(coop_list_out);
            logger.info("Applications to create cooperatives queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query applications to create cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying applications to create cooperative. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying applications to create cooperatives - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to view request to create cooperative by application ID
     *
     * @param login the logged in user
     * @param applicationId the application ID
     * @return response to the query application to create cooperative by
     * application ID request
     */
    public Response viewRequestToCreateCooperativeByAppId_Request(Login login, int applicationId) {
        Response resp = new Response();
        logger.info("Request to query application to create cooperative by application ID, invoked by [{}]", login.getUserId());

        try {

            if (!true) {//replace with check for application exist
                resp.setRetn(300);
                resp.setDesc("The specified application does not exist.");
                logger.info("The specified application [{}] does not exist - [{}] ", applicationId, login.getUserId());
                return resp;
            }

            CooperativeApplication coop_app_hib = new CooperativeApplication();//rerplace with hibernate query to get application by ID           
            List<CooperativeApplication> coop_list_out = new ArrayList<>();

            CooperativeApplication coop_model = new CooperativeApplication();
            coop_model.setFirstName(coop_app_hib.getFirstName());
            coop_model.setMiddlieName(coop_app_hib.getMiddlieName());
            coop_model.setLastName(coop_app_hib.getLastName());
            coop_model.setEmail(coop_app_hib.getEmail());
            coop_model.setPhoneNumber(coop_app_hib.getPhoneNumber());
            coop_model.setApplicationDate(coop_app_hib.getApplicationDate());
            coop_model.setNameOfCooperative(coop_app_hib.getNameOfCooperative());
            coop_list_out.add(coop_model);

            resp.setRetn(0);
            resp.setDesc("Successful");
            resp.setBody(coop_list_out);
            logger.info("Application to create cooperative queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query applications to create cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying applications to create cooperative. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying applications to create cooperatives - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to view requests to create cooperatives
     *
     * @param login the logged in user
     * @return response to the query all members application to join cooperative
     * request response to query members application to join cooperative
     */
    public Response viewMembersRequestToJoinCooperatives_Request(Login login) {
        Response resp = new Response();
        logger.info("Request to query all members application to join cooperative, invoked by [{}]", login.getUserId());

        try {
            List<MemberCooperativeApplication> member_app_list_hib = new ArrayList<>();//rerplace with hibernate query to get all pending application
            List<MemberCooperativeApplication> member_app_list_out = new ArrayList<>();
            if (member_app_list_hib.isEmpty()) {
                resp.setRetn(300);
                resp.setDesc("There are no pending applications to join cooperative.");
                logger.info("There are no pending applications to join cooperative - [{}] ", login.getUserId());
                return resp;
            }

            for (MemberCooperativeApplication app : member_app_list_hib) {
                CooperativeDetails coop_to_join = new CooperativeDetails();//replace with query to get cooperative by ID

                MemberCooperativeApplication member_app_model = new MemberCooperativeApplication();
                member_app_model.setFirstName(app.getFirstName());
                member_app_model.setMiddlieName(app.getMiddlieName());
                member_app_model.setLastName(app.getLastName());
                member_app_model.setEmail(app.getEmail());
                member_app_model.setPhoneNumber(app.getPhoneNumber());
                member_app_model.setApplicationDate(app.getApplicationDate());
                member_app_model.setNameOfCooperative(app.getNameOfCooperative());
                member_app_model.setCooperativeId(app.getCooperativeId());
                member_app_model.setNameOfCooperative(coop_to_join.getName());
                member_app_model.setAttendedStatus(app.isAttendedStatus());
                member_app_list_out.add(member_app_model);
            }
            resp.setRetn(0);
            resp.setDesc("Successful");
            resp.setBody(member_app_list_out);
            logger.info("Applications to join cooperative queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query applications to join cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying applications to join cooperative. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying applications to join cooperative - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to view members request to join cooperative
     *
     * @param login the logged in user
     * @param applicationId the application ID
     * @return response to the query member application to join cooperative
     * request
     */
    public Response viewMembersRequestToJoinCooperativeById_Request(Login login, int applicationId) {
        Response resp = new Response();
        logger.info("Request to query member application to join cooperative by application ID, invoked by [{}]", login.getUserId());

        try {

            List<MemberCooperativeApplication> member_app_list_out = new ArrayList<>();
            if (!true) {
                resp.setRetn(300);
                resp.setDesc("The specified application ID does not exist.");
                logger.info("The specified application ID {}, does not exist. - [{}] ", applicationId, login.getUserId());
                return resp;
            }

            MemberCooperativeApplication app = new MemberCooperativeApplication();//replace with method to get member application by ID
            CooperativeDetails coop_to_join = new CooperativeDetails();//replace with query to get cooperative by ID
            MemberCooperativeApplication member_app_model = new MemberCooperativeApplication();

            member_app_model.setFirstName(app.getFirstName());
            member_app_model.setMiddlieName(app.getMiddlieName());
            member_app_model.setLastName(app.getLastName());
            member_app_model.setEmail(app.getEmail());
            member_app_model.setPhoneNumber(app.getPhoneNumber());
            member_app_model.setApplicationDate(app.getApplicationDate());
            member_app_model.setNameOfCooperative(app.getNameOfCooperative());
            member_app_model.setCooperativeId(app.getCooperativeId());
            member_app_model.setNameOfCooperative(coop_to_join.getName());
            member_app_model.setAttendedStatus(app.isAttendedStatus());
            member_app_list_out.add(member_app_model);

            resp.setRetn(0);
            resp.setDesc("Successful");
            resp.setBody(member_app_list_out);
            logger.info("Member application to join cooperative queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query member application to join cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying member application to join cooperative. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying member application to join cooperative - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to forward member request to join cooperative to cooperative
     * administrator
     *
     * @param login the logged in user
     * @param requestToForward the details of the request to forward
     * @return response to the forward member application request
     */
    public Response forwardMembereApplicationToCooperativeAdmin_Request(Login login, List<ForwardMemberApplication> requestToForwards) {
        Response resp = new Response();
        logger.info("Request to forward member application to cooperative admin, invoked by [{}]", login.getUserId());

        String desc = "";
        boolean flag = false;
        int index = 0;

        try {
            for (ForwardMemberApplication requestToForward : requestToForwards) {
                if (requestToForward.getCooperativeId() <= 0) {
                    desc = "\nNo cooperative specified ID at index [" + index + "]";
                    flag = true;
                } else if (requestToForward.getCooperativeAdminUsername() == null || requestToForward.getCooperativeAdminUsername().trim().isEmpty()) {
                    desc += "\nCooperative amin must be specified at index [" + index + "]";
                    flag = true;
                } else if (requestToForward.getAdditionalInfo() == null || requestToForward.getAdditionalInfo().trim().isEmpty()) {
                    desc += "\nAdditinal note must be specified at index [" + index + "] ";
                    flag = true;
                } else if (requestToForward.getNameOfCooperative() == null || requestToForward.getNameOfCooperative().trim().isEmpty()) {
                    desc += "\nCooperative name must be specified at index [" + index + "]";
                    flag = true;
                } else {
                    flag = false;
                    logger.info("All checks passed in system administrator creation, invoked by [{}]", login.getUserId());
                }
                index++;
            }

            if (flag) {
                resp.setRetn(300);
                resp.setDesc("Found error while carrying out necessary checks.\n" + desc);
                logger.info("Found error while carrying out necessary checks: [{}], invoked by [{}]", desc, login.getUserId());
                return resp;

            } else {

                List<ForwardMemberApplication> requests_hib_to_forward = new ArrayList<>();
                List<MemberProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here to get all system admin
                List<String> admin_emails = new ArrayList<>();
                List<String> cooperative_names = new ArrayList<>();
                List<String> cooperative_administrator = new ArrayList<>();

                for (ForwardMemberApplication requestToForward : requestToForwards) {
                    cooperative_names.add(requestToForward.getNameOfCooperative());
                    cooperative_administrator.add(requestToForward.getCooperativeAdminUsername());

                    ForwardMemberApplication forwardMemberApplication_hib = new ForwardMemberApplication();
                    forwardMemberApplication_hib.setCooperativeId(requestToForward.getCooperativeId());
                    forwardMemberApplication_hib.setCooperativeAdminUsername(requestToForward.getCooperativeAdminUsername());
                    forwardMemberApplication_hib.setAdditionalInfo(requestToForward.getAdditionalInfo());
                    forwardMemberApplication_hib.setDateOfForwarding("");//replace with current date
                    forwardMemberApplication_hib.setForwardedBy(login.getUserId());
                    requests_hib_to_forward.add(forwardMemberApplication_hib);
                }

                boolean created = false;//call hibernate method to save request forwarding
                if (created) {

                    for (MemberProfile email : all_system_admin_hib) {
                        admin_emails.add(email.getEmailAddress());
                    }

                    String subject = "Member Application to Join Cooperative Forwarded.";
                    String emailBody = "<p>The system has successfully forwarded member request to join the cooperatives "
                            + "[" + cooperative_names + "], to their respective cooperative admin with username </p>\n"
                            + "<p><strong>[" + cooperative_administrator + "]</strong></p>";
                    sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                    logger.info("Email sent to system admin(s) {}, for system admin account creation. [{}]", admin_emails, login.getUserId());

                    //send email to CAC here
                    resp.setRetn(0);
                    resp.setDesc("Member request to join cooperative forwarded successfully.");
                    return resp;

                } else {
                    resp.setRetn(300);
                    resp.setDesc("Unable to forward member request to join cooperative due to error, please contact system administrator.");
                    logger.info("Unable to forward member request to join cooperative due to error, please contact system administrator. [{}] ", login.getUserId());
                    return resp;
                }

            }
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to forward member application to join cooperative to cooperative admin. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error forwarding member application to join cooperative to cooperative admin. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error forwarding member application to join cooperative to cooperative admin - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to forward member request to join cooperative to cooperative
     * administrator
     *
     * @param login the logged in user
     * @return response to the forward member application request
     */
    public Response remindCooperativeAdminOnForwardedApplications_Request(Login login) {
        Response resp = new Response();
        logger.info("Request to cooperative admin on forwarded applications, invoked by [{}]", login.getUserId());

        String desc = "";
        boolean flag = false;
        int index = 0;

        try {

            if (flag) {

            } else {

                List<ForwardMemberApplication> forwarded_requests_from_hib = new ArrayList<>();//get all forwarded request not treated
                List<MemberProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here to get all system admin
                List<String> admin_emails = new ArrayList<>();
                List<String> cooperative_names = new ArrayList<>();
                List<String> cooperative_administrator = new ArrayList<>();

//                for (ForwardMemberApplication requestToForward : requestToForwards) {
//                    cooperative_names.add(requestToForward.getNameOfCooperative());
//                    cooperative_administrator.add(requestToForward.getCooperativeAdminUsername());
//
//                    ForwardMemberApplication forwardMemberApplication_hib = new ForwardMemberApplication();
//                    forwardMemberApplication_hib.setCooperativeId(requestToForward.getCooperativeId());
//                    forwardMemberApplication_hib.setCooperativeAdminUsername(requestToForward.getCooperativeAdminUsername());
//                    forwardMemberApplication_hib.setAdditionalInfo(requestToForward.getAdditionalInfo());
//                    forwardMemberApplication_hib.setDateOfForwarding("");//replace with current date
//                    forwardMemberApplication_hib.setForwardedBy(login.getUserId());
//                    forwarded_requests_from hib
//                    .add(forwardMemberApplication_hib);
//                }
                boolean created = false;//call hibernate method to save request forwarding
                if (created) {

                    for (MemberProfile email : all_system_admin_hib) {
                        admin_emails.add(email.getEmailAddress());
                    }

                    String subject = "Member Application to Join Cooperative Forwarded.";
                    String emailBody = "<p>The system has successfully forwarded member request to join the cooperatives "
                            + "[" + cooperative_names + "], to their respective cooperative admin with username </p>\n"
                            + "<p><strong>[" + cooperative_administrator + "]</strong></p>";
                    sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                    logger.info("Email sent to system admin(s) {}, for system admin account creation. [{}]", admin_emails, login.getUserId());

                    //send email to CAC here
                    resp.setRetn(0);
                    resp.setDesc("Member request to join cooperative forwarded successfully.");
                    return resp;

                } else {
                    resp.setRetn(300);
                    resp.setDesc("Unable to forward member request to join cooperative due to error, please contact system administrator.");
                    logger.info("Unable to forward member request to join cooperative due to error, please contact system administrator. [{}] ", login.getUserId());
                    return resp;
                }

            }
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to forward member application to join cooperative to cooperative admin. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error forwarding member application to join cooperative to cooperative admin. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error forwarding member application to join cooperative to cooperative admin - [" + login.getUserId() + "]", ex);
            return resp;
        }
        return null;
    }

    /**
     * Request to save cooperative license
     *
     * @param login the logged in user
     * @param licenceDetails the cooperative details
     * @return response to the save cooperative license request
     */
    public Response saveCooperativeLicense_Request(Login login, CooperativeLicenceActivation licenceDetails) {
        Response resp = new Response();
        logger.info("Request to save cooperative license, invoked by [{}]", login.getUserId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            if (!true) {//replace with a check for if cooperative exist
                resp.setRetn(300);
                resp.setDesc("The specified cooperative does not exist.");
                logger.info("The specified cooperative {}, does not exist. - [{}] ", licenceDetails.getCooperativeId(), login.getUserId());
                return resp;
            }

            List<MemberProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here to get all system admin
            List<String> admin_emails = new ArrayList<>();
            for (MemberProfile email : all_system_admin_hib) {
                admin_emails.add(email.getEmailAddress());
            }

            String subject = "Cooperative Licence Activation.";
            String emailBody = "<p>The licence for the cooperative [" + licenceDetails.getCooperativeName() + "]"
                    + ", has been saved successfully.</p>";

            Cooperative coop = new Cooperative();//get cooperative by ID 
            CooperativeLicense coop_licence_hib;
            PeriodType period = new PeriodType();
            if (!licenceDetails.isInPerpetuity()) {
                period = new PeriodType();//replace with hibernate method
            }

            if (true) {//replace with a check for if cooperative has been activated already
                //cooperative is active already, extend licence information
                coop_licence_hib = new CooperativeLicense();//get cooperative existing licence by ID
                Date existingEndDate = coop_licence_hib.getEndDate();//change to the period set from the front
                coop_licence_hib.setLicenseInformation("");//Is this like some random generated string?

                if (licenceDetails.isInPerpetuity()) {
                    coop_licence_hib.setInPerpetuity(licenceDetails.isInPerpetuity());
                    coop_licence_hib.setDuration(0);
                    coop_licence_hib.setStartDate(null);
                    coop_licence_hib.setEndDate(null);
                    coop_licence_hib.setPeriodType(null);

                } else {

                    coop_licence_hib.setInPerpetuity(false);
                    coop_licence_hib.setDuration(licenceDetails.getDuration());
                    coop_licence_hib.setPeriodType(period);

                    Date newLicencePeriod = formatter.parse(licenceDetails.getEndDate());//change to whatever new period is sent from the front
                    long daysDiff = util.returnDateDiffInDays(existingEndDate, newLicencePeriod);
                    String newEndDate = util.addNumberOfDaysToDate(formatter.format(existingEndDate), daysDiff);
                    coop_licence_hib.setEndDate(formatter.parse(newEndDate));
                }

                boolean save = false;//replace with method to save licence information
                if (save) {

                    sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                    logger.info("Email sent to system admin(s) {} for licence activation, for system admin account creation. [{}]", admin_emails, login.getUserId());

                    //send notification to system admins here
                    resp.setRetn(0);
                    resp.setDesc("Licence activation was successful.");
                    return resp;
                } else {
                    resp.setRetn(300);
                    resp.setDesc("Cooperative licence extension failed, please contact system administrator");
                    logger.info("Cooperative licence extension failed, please contact system administrator. [{}] ", login.getUserId());
                    return resp;
                }

            } else {
                //cooperative has not been activated
                if (!true) {//replace with a check for if cooperative has members
                    resp.setRetn(300);
                    resp.setDesc("The specified cooperative does not have any member");
                    logger.info("The specified cooperative {}, does not have any member. - [{}] ", licenceDetails.getCooperativeId(), login.getUserId());
                    return resp;
                }
                coop_licence_hib = new CooperativeLicense();

                coop_licence_hib.setLicenseInformation("");//Is this like some random generated string?
                coop_licence_hib.setCooperative(coop);
                if (licenceDetails.isInPerpetuity()) {
                    coop_licence_hib.setInPerpetuity(licenceDetails.isInPerpetuity());
                    coop_licence_hib.setDuration(0);
                    coop_licence_hib.setStartDate(null);
                    coop_licence_hib.setEndDate(null);
                    coop_licence_hib.setPeriodType(null);

                } else {
                    coop_licence_hib.setInPerpetuity(false);
                    coop_licence_hib.setDuration(licenceDetails.getDuration());
                    coop_licence_hib.setPeriodType(period);
                    coop_licence_hib.setStartDate(new Date());

                    Date licensePeriod = formatter.parse(licenceDetails.getEndDate());//change to whatever new period is sent from the front
                    long daysDiff = util.returnDateDiffInDays(new Date(), licensePeriod);
                    String newLicenseEndDate = util.addNumberOfDaysToDate(formatter.format(new Date()), daysDiff);
                    coop_licence_hib.setEndDate(formatter.parse(newLicenseEndDate));
                }

                boolean save = false;//replace with method to save licence information
                if (save) {

                    sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                    logger.info("Email sent to system admin(s) {} for licence activation, for system admin account creation. [{}]", admin_emails, login.getUserId());

                    //send notification to system admins here
                    resp.setRetn(0);
                    resp.setDesc("Licence activation was successful.");
                    return resp;
                } else {
                    resp.setRetn(300);
                    resp.setDesc("Cooperative licence saving failed, please contact system administrator");
                    logger.info("Cooperative licence saving failed, please contact system administrator. [{}] ", login.getUserId());
                    return resp;
                }

            }

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to create cooperative. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error saving cooperative license. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error saving cooperative license - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to activate cooperative license
     *
     * @param login the logged in user
     * @param licenceDetails the cooperative details
     * @return response to the activate cooperative license request
     */
    public Response activateCooperativeLicense_Request(Login login, CooperativeLicenceActivation licenceDetails) {
        Response resp = new Response();
        logger.info("Request to activate cooperative license, invoked by [{}]", login.getUserId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            if (!true) {//replace with a check for if cooperative exist
                resp.setRetn(300);
                resp.setDesc("The specified cooperative does not exist.");
                logger.info("The specified cooperative {}, does not exist. - [{}] ", licenceDetails.getCooperativeId(), login.getUserId());
                return resp;
            }

            if (!true) {//replace with a check for if cooperative has members
                resp.setRetn(300);
                resp.setDesc("The specified cooperative does not have any member");
                logger.info("The specified cooperative {}, does not have any member. - [{}] ", licenceDetails.getCooperativeId(), login.getUserId());
                return resp;
            }

            Cooperative coop = new Cooperative();//get cooperative by ID 
            CooperativeLicense coop_licence_hib = new CooperativeLicense();
            PeriodType period = new PeriodType();
            if (!licenceDetails.isInPerpetuity()) {
                period = new PeriodType();//replace with hibernate method
            }

            coop_licence_hib.setLicenseInformation("");//Is this like some random generated string?
            coop_licence_hib.setCooperative(coop);
            if (licenceDetails.isInPerpetuity()) {
                coop_licence_hib.setInPerpetuity(licenceDetails.isInPerpetuity());
                coop_licence_hib.setDuration(0);
                coop_licence_hib.setStartDate(null);
                coop_licence_hib.setEndDate(null);
                coop_licence_hib.setPeriodType(null);

            } else {
                coop_licence_hib.setInPerpetuity(false);
                coop_licence_hib.setDuration(licenceDetails.getDuration());
                coop_licence_hib.setPeriodType(period);
                coop_licence_hib.setStartDate(new Date());

                Date newLicencePeriod = formatter.parse(licenceDetails.getEndDate());//change to whatever new period is sent from the front
                long daysDiff = util.returnDateDiffInDays(new Date(), newLicencePeriod);
                String newEndDate = util.addNumberOfDaysToDate(formatter.format(new Date()), daysDiff);
                coop_licence_hib.setEndDate(formatter.parse(newEndDate));
            }

            boolean save = false;//replace with method to save licence information
            if (save) {
                List<MemberProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here to get all system admin
                List<String> admin_emails = new ArrayList<>();
                for (MemberProfile email : all_system_admin_hib) {
                    admin_emails.add(email.getEmailAddress());
                }

                String subject = "Cooperative Licence Activation.";
                String emailBody = "<p>The licence for the cooperative [" + licenceDetails.getCooperativeName() + "]"
                        + ", has been activated successfully.</p>";

                sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                logger.info("Email sent to system admin(s) {} for licence activation, for system admin account creation. [{}]", admin_emails, login.getUserId());

                //send notification to system admins here
                resp.setRetn(0);
                resp.setDesc("Licence activation was successful.");
                return resp;
            } else {
                resp.setRetn(300);
                resp.setDesc("Cooperative licence activation failed, please contact system administrator");
                logger.info("Cooperative licence activation failed, please contact system administrator. [{}] ", login.getUserId());
                return resp;
            }

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to activate cooperative license. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error activating cooperative license. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error activating cooperative license - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to deactivate cooperative license
     *
     * @param login the logged in user
     * @param licenceDetails the cooperative details
     * @return response to the deactivate cooperative license request
     */
    public Response deactivateCooperativeLicense_Request(Login login, CooperativeLicenceActivation licenceDetails) {
        Response resp = new Response();
        logger.info("Request to deactivate cooperative license, invoked by [{}]", login.getUserId());

        try {
            if (!true) {//replace with a check for if cooperative exist
                resp.setRetn(300);
                resp.setDesc("The specified cooperative does not exist.");
                logger.info("The specified cooperative {}, does not exist. - [{}] ", licenceDetails.getCooperativeId(), login.getUserId());
                return resp;
            }

            if (!true) {//replace with a check for if cooperative license exist
                resp.setRetn(300);
                resp.setDesc("The specified cooperative does not have the specified license");
                logger.info("The specified cooperative {}, does not have the specified license. - [{}] ", licenceDetails.getCooperativeId(), login.getUserId());
                return resp;
            }

            CooperativeLicense coop_licence_hib = new CooperativeLicense();//get cooperating existing licence by ID
            coop_licence_hib.setStartDate(null);
            coop_licence_hib.setEndDate(null);
            coop_licence_hib.setDuration(0);
            //coop_licence_hib     set flag to deactivate license here
            //call hibernate method to deactivate cooperative license / all members and shutdown running loans and every other transactions
            boolean deactivated = false;

            List<MemberProfile> all_system_admin_hib = new ArrayList<>(); //call hibernate method here to get all system admin
            List<String> admin_emails = new ArrayList<>();
            for (MemberProfile email : all_system_admin_hib) {
                admin_emails.add(email.getEmailAddress());
            }

            String subject = "Cooperative Licence Deactivation.";
            String emailBody = "<p>The licence for the cooperative [" + licenceDetails.getCooperativeName() + "]"
                    + ", has been deaactivated successfully.</p>";

            if (deactivated) {
                sender.sendBulkEmail("email@gmail.com", "password", admin_emails, subject, emailBody);
                logger.info("Email sent to system admin(s) {} for licence deactivation, for system admin account creation. [{}]", admin_emails, login.getUserId());
                //send notification to system admins here
                resp.setRetn(0);
                resp.setDesc("Licence deactivation was successful.");
                return resp;
            } else {
                resp.setRetn(300);
                resp.setDesc("Cooperative licence deactivation failed, please contact system administrator");
                logger.info("Cooperative licence deactivation failed, please contact system administrator. [{}] ", login.getUserId());
                return resp;
            }

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to deactivation cooperative license. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error deactivating cooperative license. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error deactivating cooperative license - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to query all licenses of a cooperative
     *
     * @param login the logged in user
     * @param cooperativeId the cooperative ID
     * @return response to the query cooperative licenses request
     */
    public Response queryCooperativeLicenceByCoopId_Request(Login login, int cooperativeId) {
        Response resp = new Response();
        logger.info("Request to query cooperative licence(s) by , invoked by [{}]", login.getUserId());

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified cooperative does not exist, please contact system administrator.");
                logger.info("The specified cooperative [{}] does not exist, please contact system administrator. [{}] ", cooperativeId, login.getUserId());
                return resp;
            }

            List<CooperativeLicense> coop_licence_hib = new ArrayList<>();//rerplace with hibernate method
            List<CooperativeLicenceActivation> coop_licence_model_out = new ArrayList<>();
            CooperativeDetails coop_hib = new CooperativeDetails();//replace with query to get cooperative by ID

            for (CooperativeLicense licence : coop_licence_hib) {
                CooperativeLicenceActivation coop_activation_model = new CooperativeLicenceActivation();

                coop_activation_model.setCooperativeName(coop_hib.getName());
                coop_activation_model.setDuration(licence.getDuration());
                coop_activation_model.setEndDate(formatter.format(licence.getEndDate()));
                coop_activation_model.setStartDate(formatter.format(licence.getStartDate()));
                coop_activation_model.setId(licence.getId());
                coop_activation_model.setInPerpetuity(licence.getInPerpetuity());
                coop_activation_model.setLicenceInformation(licence.getLicenseInformation());
                coop_activation_model.setPeriodTypeId(licence.getPeriodType().getId());
                coop_licence_model_out.add(coop_activation_model);
            }

            resp.setRetn(0);
            resp.setDesc("Cooperative licence queried successfully.");
            resp.setBody(coop_licence_model_out);
            logger.info("Cooperative licence queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query cooperative licence. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying cooperative licence. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying cooperative licence - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to query all licenses of a cooperative
     *
     * @param login the logged in user
     * @param licenseId the license ID
     * @return response to the query cooperative license by ID request
     */
    public Response queryCooperativeLicenceByLicenseId_Request(Login login, int licenseId) {
        Response resp = new Response();
        logger.info("Request to query cooperative licence(s) by license ID , invoked by [{}]", login.getUserId());

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            if (!true) {//replace with checkIfLicenseExist
                resp.setRetn(301);
                resp.setDesc("The specified cooperative license does not exist, please contact system administrator.");
                logger.info("The specified cooperative license [{}] does not exist, please contact system administrator. [{}] ", licenseId, login.getUserId());
                return resp;
            }

            CooperativeLicense licence = new CooperativeLicense();//rerplace with hibernate method to get license by ID
            List<CooperativeLicenceActivation> coop_licence_model_out = new ArrayList<>();
            CooperativeDetails coop_hib = new CooperativeDetails();//replace with query to get cooperative by ID

            CooperativeLicenceActivation coop_activation_model = new CooperativeLicenceActivation();

            coop_activation_model.setCooperativeName(coop_hib.getName());
            coop_activation_model.setDuration(licence.getDuration());
            coop_activation_model.setEndDate(formatter.format(licence.getEndDate()));
            coop_activation_model.setStartDate(formatter.format(licence.getStartDate()));
            coop_activation_model.setId(licence.getId());
            coop_activation_model.setInPerpetuity(licence.getInPerpetuity());
            coop_activation_model.setLicenceInformation(licence.getLicenseInformation());
            coop_activation_model.setPeriodTypeId(licence.getPeriodType().getId());
            coop_licence_model_out.add(coop_activation_model);

            resp.setRetn(0);
            resp.setDesc("Cooperative licence queried successfully.");
            resp.setBody(coop_licence_model_out);
            logger.info("Cooperative licence queried successfully. [{}] ", login.getUserId());
            return resp;
        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query cooperative licence by the ID. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying cooperative licence by the ID. See error log. [{}] - [{}]", resp.getRetn(), login.getUserId());
            logger.error("Error querying cooperative licence by the ID - [" + login.getUserId() + "]", ex);
            return resp;
        }
    }
}
