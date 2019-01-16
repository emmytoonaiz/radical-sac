/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radicalsac.logic;

import com.easycoop.radical.database.layer.entity.MemberProfile;
import com.easycoop.radical.lib.model.authentication.UserAuth;
import com.radicalsac.models.LoanGuarantors;
import com.radicalsac.models.LoanType;
import com.radicalsac.models.MemberLoanHistory;
import com.radicalsac.models.MemberLoanRepaymentHistory;
import com.radicalsac.models.MemberContribution;
import com.radicalsac.models.Response;
import com.radicalsac.models.SavingsType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.exchangepoint.resource.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author emmanuel.idoko
 */
public class CooperativeAdminCategory {

    private final EmailSender sender = new EmailSender();
    private static final Logger logger = LoggerFactory.getLogger(CooperativeAdminCategory.class);

    /**
     * Request to query cooperative members
     *
     * @param login the logged in user
     * @param cooperativeId the cooperative ID
     * @param pageSize the page size
     * @param pageNumber the page number
     * @return response to the query all cooperative members request
     */
    public Response viewAllCooperativeMembers_Request(UserAuth login, int cooperativeId, int pageSize, int pageNumber) {
        Response resp = new Response();
        logger.info("Request to query all cooperative members, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified cooperative does not exist, please contact system administrator.");
                logger.info("The specified cooperative [{}] does not exist, please contact system administrator. [{}] ", cooperativeId, login.getUsername());
                return resp;
            }
            if (pageSize <= 0 || pageNumber <= 0) {
                resp.setDesc("The page size or page number is not properyly set.");
                logger.info("The page size or page number is not properyly set page size {}, page number {}, please contact system administrator. [{}] ", pageSize, pageNumber, login.getUsername());
                return resp;
            }

            List<MemberProfile> coop_members_hib = new ArrayList<>();//replace with query to get cooperative members
            List<com.radicalsac.models.MemberProfile> mp_model_out = new ArrayList<>();

            for (MemberProfile mp : coop_members_hib) {
                com.radicalsac.models.MemberProfile mp_model = new com.radicalsac.models.MemberProfile();
                mp_model.setId(mp.getId());
                mp_model.setFirstName(mp.getFirstName());
                mp_model.setMiddleName(mp.getMiddleName());
                mp_model.setLastName(mp.getLastName());
                mp_model.setEmailAddress(mp.getEmailAddress());
                mp_model.setPhoneNumber(mp.getPhoneNumber());
                mp_model_out.add(mp_model);
            }
            resp.setRetn(0);
            resp.setDesc("Cooperative members queried successfully.");
            resp.setBody(mp_model_out);
            logger.info("Cooperative members queried successfully. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query cooperative members. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying cooperative members. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error querying cooperative members - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to query cooperative members
     *
     * @param login the logged in user
     * @param memberAccountId the cooperative member ID
     * @return response to the query all cooperative members request
     */
    public Response viewCooperativeMemberById_Request(UserAuth login, int memberAccountId) {
        Response resp = new Response();
        logger.info("Request to query cooperative member by ID, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified cooperative member does not exist, please contact system administrator.");
                logger.info("The specified cooperative member [{}] does not exist, please contact system administrator. [{}] ", memberAccountId, login.getUsername());
                return resp;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
            MemberProfile coop_member_hib = new MemberProfile();//replace with query to get cooperative member by Id
            List<com.radicalsac.models.MemberProfile> mp_model_out = new ArrayList<>();
            com.radicalsac.models.MemberProfile mp_model = new com.radicalsac.models.MemberProfile();

            mp_model.setId(coop_member_hib.getId());
            mp_model.setFirstName(coop_member_hib.getFirstName());
            mp_model.setMiddleName(coop_member_hib.getMiddleName());
            mp_model.setLastName(coop_member_hib.getLastName());
            mp_model.setUsername(coop_member_hib.getUsername());
            mp_model.setEmailAddress(coop_member_hib.getEmailAddress());
            mp_model.setAlternateEmailAddress(coop_member_hib.getAlternateEmailAddress());
            mp_model.setPhoneNumber(coop_member_hib.getPhoneNumber());
            mp_model.setAlternatePhoneNumber(coop_member_hib.getAlternatePhoneNumber());
            mp_model.setDateOfBirth((coop_member_hib.getDateOfBirth() != null ? (formatter.format(coop_member_hib.getDateOfBirth())) : ""));
            mp_model.setAccountNumber(coop_member_hib.getAccountNumber());
            mp_model.setAddressLine1(coop_member_hib.getAddressLine1());
            mp_model.setAddressLine2(coop_member_hib.getAddressLine2());
            mp_model.setAddressLine3(coop_member_hib.getAddressLine3());
            mp_model.setLga(coop_member_hib.getLga());
            mp_model.setCountry(coop_member_hib.getCountry());
            mp_model.setBankVerificationNumber(coop_member_hib.getBankVerificationNumber());
            mp_model.setCooperativeId(coop_member_hib.getCooperative().getId());
            mp_model.setOccupation(coop_member_hib.getOccupation());

            mp_model.setCooperativeMembershipNumber((coop_member_hib.getCooperativeMembershipNumber() != null ? (coop_member_hib.getCooperativeMembershipNumber()) : 0));
            mp_model.setIsCooperativeAdmin(coop_member_hib.getIsCooperativeAdmin() != null ? (coop_member_hib.getIsCooperativeAdmin()) : false);
            mp_model.setIsSystemAdmin(coop_member_hib.getIsSystemAdmin() != null ? (coop_member_hib.getIsSystemAdmin()) : false);
            mp_model.setYearlyIncome(coop_member_hib.getYearlyIncome() != null ? (coop_member_hib.getYearlyIncome()) : 0);
            mp_model_out.add(mp_model);

            resp.setRetn(0);
            resp.setDesc("Cooperative member queried successfully.");
            resp.setBody(mp_model_out);
            logger.info("Cooperative member queried successfully. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query cooperative member. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying cooperative member. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error querying cooperative member - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to close cooperative member account
     *
     * @param login the logged in user
     * @param memberAccountId the cooperative member ID
     * @return response to the query all cooperative members request
     */
    public Response closeMemberAccount_Request(UserAuth login, int memberAccountId) {
        Response resp = new Response();
        logger.info("Request to query cooperative member by ID, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified cooperative member does not exist, please contact system administrator.");
                logger.info("The specified cooperative member [{}] does not exist, please contact system administrator. [{}] ", memberAccountId, login.getUsername());
                return resp;
            }

            MemberProfile coop_member_hib = new MemberProfile();//replace with query to get cooperative member by Id
            coop_member_hib.setParticipating(false);
            boolean closed = false;

            if (closed) {
                resp.setRetn(0);
                resp.setDesc("Cooperative member account closed successfully.");
                logger.info("Cooperative member account closed successfully. [{}] ", login.getUsername());
                return resp;
            }
            resp.setRetn(300);
            resp.setDesc("Unable to close cooperative member account.");
            logger.info("Unable to close cooperative member account. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to close cooperative member account. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error closing cooperative member account. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error closing cooperative member account- [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to suspend cooperative member account
     *
     * @param login the logged in user
     * @param memberAccountId the cooperative member ID
     * @return response to suspension of cooperative member request
     */
    public Response suspendMemberAccount_Request(UserAuth login, int memberAccountId) {
        Response resp = new Response();
        logger.info("Request to suspend cooperative member account, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified cooperative member does not exist, please contact system administrator.");
                logger.info("The specified cooperative member [{}] does not exist, please contact system administrator. [{}] ", memberAccountId, login.getUsername());
                return resp;
            }

            MemberProfile coop_member_hib = new MemberProfile();//replace with query to get cooperative member by Id
            coop_member_hib.setParticipating(false);
            boolean closed = false;

            if (closed) {
                resp.setRetn(0);
                resp.setDesc("Cooperative member account closed successfully.");
                logger.info("Cooperative member account closed successfully. [{}] ", login.getUsername());
                return resp;
            }
            resp.setRetn(300);
            resp.setDesc("Unable to close cooperative member account.");
            logger.info("Unable to close cooperative member account. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to close cooperative member account. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error closing cooperative member account. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error closing cooperative member account- [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to configure savings type
     *
     * @param login the logged in user
     * @param savingsType the savings type details
     * @return response to configure savings type request
     */
    public Response configureSavingsType_Request(UserAuth login, SavingsType savingsType) {
        Response resp = new Response();
        logger.info("Request to configure savings type, invoked by [{}]", login.getUsername());
        String desc = "";
        boolean flag;
        try {

            if (savingsType.getName() == null || !savingsType.getName().trim().isEmpty()) {
                desc += "\nThe savings type name must be specified.";
                flag = true;
            } else if (savingsType.getDescription() == null || !savingsType.getDescription().trim().isEmpty()) {
                desc += "\nThe savings type description must be specified.";
                flag = true;
            } else if (savingsType.getInterestRate() <= 0) {
                desc += "\nThe savings type interest rate must be specified.";
                flag = true;
            } else {
                flag = false;
                logger.info("All checks passed in savings type configuration, invoked by [{}]", login.getUsername());
            }

            if (flag) {
                resp.setRetn(300);
                resp.setDesc("Found error(s) while carrying out necessary checks.\n" + desc);
                logger.info("Found error(s) while carrying out necessary checks: [{}], invoked by [{}]", desc, login.getUsername());
                return resp;
            } else {
                SavingsType savings_type_hib = new SavingsType();//replace with hibernate object
                savings_type_hib.setName(savingsType.getName());
                savings_type_hib.setDescription(savingsType.getDescription());
                savings_type_hib.setInterestRate(savingsType.getInterestRate());

                boolean created = false; //replace with hibernate query to save savings type

                if (created) {
                    resp.setRetn(0);
                    resp.setDesc("Savings type saved successfully.");
                    logger.info("Savings type saved successfully. [{}] ", login.getUsername());
                    return resp;
                }
                resp.setRetn(300);
                resp.setDesc("Unable to create savings type.");
                logger.info("Unable to create savings type. [{}] ", login.getUsername());
                return resp;
            }

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to close cooperative member account. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error closing cooperative member account. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error closing cooperative member account- [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to configure savings type
     *
     * @param login the logged in user
     * @param loanType the loan type details
     * @return response to configure loan type request
     */
    public Response configureLoanType_Request(UserAuth login, LoanType loanType) {
        Response resp = new Response();
        logger.info("Request to configure loan type, invoked by [{}]", login.getUsername());
        String desc = "";
        boolean flag;
        try {

            if (loanType.getName() == null || !loanType.getName().trim().isEmpty()) {
                desc += "\nThe loan type name must be specified.";
                flag = true;
            } else if (loanType.getDescription() == null || !loanType.getDescription().trim().isEmpty()) {
                desc += "\nThe loan type description must be specified.";
                flag = true;
            } else if (loanType.getInterestRate() <= 0) {
                desc += "\nThe loan type interest rate must be specified.";
                flag = true;
            } else if (loanType.getNumberOfGuarantors() <= 0) {
                desc += "\nThe loan type number of guarantors must be specified.";
                flag = true;
            } else {
                flag = false;
                logger.info("All checks passed in loan type configuration, invoked by [{}]", login.getUsername());
            }

            if (flag) {
                resp.setRetn(300);
                resp.setDesc("Found error(s) while carrying out necessary checks.\n" + desc);
                logger.info("Found error(s) while carrying out necessary checks: [{}], invoked by [{}]", desc, login.getUsername());
                return resp;
            } else {

                LoanType loan_type_hib = new LoanType();//replace with hibernate object
                loan_type_hib.setName(loanType.getName());
                loan_type_hib.setDescription(loanType.getDescription());
                loan_type_hib.setInterestRate(loanType.getInterestRate());
                loan_type_hib.setNumberOfGuarantors(loanType.getNumberOfGuarantors());

                boolean created = false; //replace with hibernate query to save loan type

                if (created) {
                    resp.setRetn(0);
                    resp.setDesc("Loan type saved successfully.");
                    logger.info("Loan type saved successfully. [{}] ", login.getUsername());
                    return resp;
                }
                resp.setRetn(300);
                resp.setDesc("Unable to create loan type.");
                logger.info("Unable to create loan type. [{}] ", login.getUsername());
                return resp;
            }

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to create loan type. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error creating loan type. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error creating loan type - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to capture member savings / contribution
     *
     * @param login the logged in user
     * @param memberSavings the savings / contribution details
     * @return response to capture member savings / contribution
     */
    public Response captureMemberSavingsOrContribution_Request(UserAuth login, MemberContribution memberSavings) {
        Response resp = new Response();
        logger.info("Request to configure loan type, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified cooperative member does not exist, please contact system administrator.");
                logger.info("The specified cooperative member [{}] does not exist, please contact system administrator. [{}] ", memberSavings.getMemberAccountId(), login.getUsername());
                return resp;
            }
            if (!true) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified savings type does not exist, please contact system administrator.");
                logger.info("The specified savings type [{}] does not exist, please contact system administrator. [{}] ", memberSavings.getMemberAccountId(), login.getUsername());
                return resp;
            }
            if (!true) {//replace with check for if member has the savings type
                resp.setRetn(301);
                resp.setDesc("The specified savings type is not configured for the chosen member, please contact system administrator.");
                logger.info("The specified savings type is not configured for the chosen member, please contact system administrator. [{}] ", login.getUsername());
                return resp;
            }
            if (memberSavings.getAmount() <= 0) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The savings amount is improperly set, please contact system administrator.");
                logger.info("The savings amount is improperly set [{}] does not exist, please contact system administrator. [{}] ", memberSavings.getAmount(), login.getUsername());
                return resp;
            }

            MemberContribution mem_savings_hib = new MemberContribution();
            mem_savings_hib.setMemberAccountId(memberSavings.getMemberAccountId());
            mem_savings_hib.setSavingsTypeId(memberSavings.getSavingsTypeId());
            mem_savings_hib.setAmount(memberSavings.getAmount());

            boolean save = false;
            if (save) {
                resp.setRetn(0);
                resp.setDesc("Member savings captured successfully.");
                logger.info("Member savings captured successfully. [{}] ", login.getUsername());
                return resp;
            }
            resp.setRetn(300);
            resp.setDesc("Unable to capture member savings.");
            logger.info("Unable to capture member savings. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to capture member savings. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error to capturing member savings. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error to capture member savings - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to upload members contribution
     *
     * @param login the logged in user
     * @param memberSavings the savings / contribution details
     * @return response to upload members contribution
     */
    public Response uploadMemberContribution_Request(UserAuth login, List<MemberContribution> memberSavings) {
        Response resp = new Response();
        logger.info("Request to upload members contribution, invoked by [{}]", login.getUsername());
        try {

            if (memberSavings == null || memberSavings.isEmpty()) {//replace with checkCooperativeExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The uploaded file is empty.");
                logger.info("The uploaded file is empty. [{}] ", login.getUsername());
                return resp;
            }

            int index = 0;
            int repeating_acct_no_counter = 0;
            Map<Integer, String> error_desc = new HashMap<>();
            boolean memberAcctExist;
            Set repeat_acct_checker = new HashSet();
            List<MemberContribution> ms_hib_list = new ArrayList<>();

            for (MemberContribution ms : memberSavings) {
                String msg = "";
                memberAcctExist = true;//replace with check for if member exist
                if (!memberAcctExist) {
                    resp.setForceStopDueToError(true);
                    msg = "The specified account number [" + ms.getMemberAccountId() + "] does not exist. Please, contact Administrator. ";
                    error_desc.put(index, msg);
                    logger.info("The specified account number {} does not exist. Please, contact Administrator - [{}]", ms.getMemberAccountId(), login.getUsername());

                }
                if (ms.getAmount() <= 0) {
                    if (!"".equals(msg)) {
                        msg += " || The contribution amount [" + ms.getAmount() + "] for member account [" + ms.getMemberAccountId() + "] is improperly set, please contact system administrator.";
                    } else {
                        msg += "The contribution amount [" + ms.getAmount() + "] for member account [" + ms.getMemberAccountId() + "] is improperly set, please contact system administrator.";
                    }
                    resp.setForceStopDueToError(true);
                    error_desc.put(index, msg);
                    logger.info("The contribution amount [" + ms.getAmount() + "] for member account [" + ms.getMemberAccountId() + "] is improperly set, please contact system administrator - [{}]", login.getUsername());
                }
                repeat_acct_checker.add(ms.getMemberAccountId());
                repeating_acct_no_counter++;

                if (repeating_acct_no_counter != repeat_acct_checker.size()) {
                    repeating_acct_no_counter--;

                    if (!"".equals(msg)) {
                        msg += " || The specified account number [" + ms.getMemberAccountId() + "] is repeating.";
                    } else {
                        msg += "The specified account number [" + ms.getMemberAccountId() + "] is repeating.";
                    }
                    resp.setForceStopDueToError(true);
                    logger.info("The specified account number {} is repeating - [{}] ", ms.getMemberAccountId(), login.getUsername());
                    error_desc.put(index, msg);
                }
                index++;
            }

            if (resp.isForceStopDueToError()) {
                resp.setRetn(338);
                resp.setDesc("Error found while uploading members contribution.");
                resp.setErrorReport(error_desc);
                logger.info("Error found while uploading members contribution. [{}] - [{}] ", error_desc, login.getUsername());
                return resp;
            } else {

                for (MemberContribution ms : memberSavings) {
                    MemberContribution mem_savings_hib = new MemberContribution();
                    mem_savings_hib.setMemberAccountId(ms.getMemberAccountId());
                    mem_savings_hib.setSavingsTypeId(ms.getSavingsTypeId());
                    mem_savings_hib.setAmount(ms.getAmount());
                    ms_hib_list.add(mem_savings_hib);
                }
            }

            boolean save = false;//replace with hibernate method here
            if (save) {
                resp.setRetn(0);
                resp.setDesc("Members contribution uploaded successfully.");
                logger.info("Members contribution uploaded successfully. [{}] ", login.getUsername());
                return resp;
            }
            resp.setRetn(300);
            resp.setDesc("Unable to upload members contribution.");
            logger.info("Unable to upload members contribution. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to upload members contribution. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error uploading members contribution. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error uploading members contribution - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to query cooperative member loan history
     *
     * @param login the logged in user
     * @param memberAccountId the member account ID
     * @param pageSize the page size
     * @param pageNumber the page number
     * @return response to the query cooperative member loan history request
     */
    public Response viewMemberLoanHistory_Request(UserAuth login, int memberAccountId, int pageSize, int pageNumber) {
        Response resp = new Response();
        logger.info("Request to query all cooperative members, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeMemberExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified member account does not exist, please contact system administrator.");
                logger.info("The specified member account [{}] does not exist, please contact system administrator. [{}] ", memberAccountId, login.getUsername());
                return resp;
            }

            MemberProfile memberProfile_hib = new MemberProfile();//replace with query to get member profile

            String m_fullName = memberProfile_hib.getFirstName();
            if (memberProfile_hib.getMiddleName() != null && !memberProfile_hib.getMiddleName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getMiddleName();
            }
            if (memberProfile_hib.getLastName() != null && !memberProfile_hib.getLastName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getLastName();
            }

            if (pageSize <= 0 || pageNumber <= 0) {
                resp.setDesc("The page size or page number is not properyly set.");
                logger.info("The page size or page number is not properyly set page size {}, page number {}, please contact system administrator. [{}] ", pageSize, pageNumber, login.getUsername());
                return resp;
            }

            List<MemberLoanHistory> coop_members_hib = new ArrayList<>();//replace with query to get cooperative member loan history
            List<com.radicalsac.models.MemberLoanHistory> mp_model_out = new ArrayList<>();

            for (MemberLoanHistory mlh : coop_members_hib) {
                com.radicalsac.models.MemberLoanHistory mp_model = new com.radicalsac.models.MemberLoanHistory();
                LoanType loan_type_hib = new LoanType(); //replace with query to get loantype
                List<LoanGuarantors> guarantors_hib = new ArrayList<>();//replace with query to get loan guarantors by loan type and member account Id

                mp_model.setId(mlh.getId());
                mp_model.setMemberAccountId(mlh.getMemberAccountId());
                mp_model.setMemberName(m_fullName);
                mp_model.setAmount(mlh.getAmount());
                mp_model.setAppliedDate(mlh.getAppliedDate());
                mp_model.setApprovedBy(mlh.getApprovedBy());
                mp_model.setApprovedDate(mlh.getApprovedDate());
                mp_model.setDatePaid(mlh.getDatePaid());
                mp_model.setExpectedDisbursementDate(mlh.getExpectedDisbursementDate());
                mp_model.setLoanType(loan_type_hib.getName());

                //guarantors information here
                List<LoanGuarantors> g_list_out = new ArrayList<>();
                for (LoanGuarantors g : guarantors_hib) {
                    LoanGuarantors g_model = new LoanGuarantors();
                    g_model.setMemberAccountId(g.getMemberAccountId());
                    g_model.setMemberName(g.getMemberName());
                    g_list_out.add(g_model);
                }
                mp_model.setGuarantors(g_list_out);

            }
            resp.setRetn(0);
            resp.setDesc("Member loan history queried successfully.");
            resp.setBody(mp_model_out);
            logger.info("Member loan history queried successfully. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query member loan history. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying member loan history. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error querying member loan history - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to query cooperative member loan repayment history
     *
     * @param login the logged in user
     * @param memberAccountId the member account ID
     * @param pageSize the page size
     * @param pageNumber the page number
     * @return response to the query cooperative member loan history request
     */
    public Response viewMemberLoanRepaymentHistory_Request(UserAuth login, int memberAccountId, int pageSize, int pageNumber) {
        Response resp = new Response();
        logger.info("Request to query member loan repayment history, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeMemberExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified member account does not exist, please contact system administrator.");
                logger.info("The specified member account [{}] does not exist, please contact system administrator. [{}] ", memberAccountId, login.getUsername());
                return resp;
            }
            if (pageSize <= 0 || pageNumber <= 0) {
                resp.setDesc("The page size or page number is not properyly set.");
                logger.info("The page size or page number is not properyly set page size {}, page number {}, please contact system administrator. [{}] ", pageSize, pageNumber, login.getUsername());
                return resp;
            }

            MemberProfile memberProfile_hib = new MemberProfile();//replace with query to get member profile
            String m_fullName = memberProfile_hib.getFirstName();
            if (memberProfile_hib.getMiddleName() != null && !memberProfile_hib.getMiddleName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getMiddleName();
            }
            if (memberProfile_hib.getLastName() != null && !memberProfile_hib.getLastName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getLastName();
            }

            List<MemberLoanRepaymentHistory> coop_members_hib = new ArrayList<>();//replace with query to get cooperative member loan history
            List<com.radicalsac.models.MemberLoanRepaymentHistory> mp_model_out = new ArrayList<>();

            for (MemberLoanRepaymentHistory mlh : coop_members_hib) {
                com.radicalsac.models.MemberLoanRepaymentHistory mp_model = new com.radicalsac.models.MemberLoanRepaymentHistory();
                //LoanType loan_type_hib = new LoanType(); //replace with query to get loantype

                mp_model.setId(mlh.getId());
                mp_model.setMemberAccountId(mlh.getMemberAccountId());
                mp_model.setMemberName(m_fullName);
                mp_model.setAmount(mlh.getAmount());
                mp_model.setLoanId(mlh.getLoanId());
                mp_model.setLoanType(mlh.getLoanType());
                mp_model.setNarration(mlh.getNarration());
                mp_model.setValueDate(mlh.getValueDate());
                mp_model_out.add(mp_model);
            }
            resp.setRetn(0);
            resp.setDesc("Member loan repayment history queried successfully.");
            resp.setBody(mp_model_out);
            logger.info("Member loan repayment history queried successfully. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query member loan repayment history. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying member loan repayment history. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error querying member loan repayment history - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to query member contribution
     *
     * @param login the logged in user
     * @param memberAccountId the member account ID
     * @param pageSize the page size
     * @param pageNumber the page number
     * @return response to the query member contribution request
     */
    public Response viewMemberContributions_Request(UserAuth login, int memberAccountId, int pageSize, int pageNumber) {
        Response resp = new Response();
        logger.info("Request to query member contribution, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeMemberExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified member account does not exist, please contact system administrator.");
                logger.info("The specified member account [{}] does not exist, please contact system administrator. [{}] ", memberAccountId, login.getUsername());
                return resp;
            }
            if (pageSize <= 0 || pageNumber <= 0) {
                resp.setDesc("The page size or page number is not properyly set.");
                logger.info("The page size or page number is not properyly set page size {}, page number {}, please contact system administrator. [{}] ", pageSize, pageNumber, login.getUsername());
                return resp;
            }

            MemberProfile memberProfile_hib = new MemberProfile();//replace with query to get member profile
            String m_fullName = memberProfile_hib.getFirstName();
            if (memberProfile_hib.getMiddleName() != null && !memberProfile_hib.getMiddleName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getMiddleName();
            }
            if (memberProfile_hib.getLastName() != null && !memberProfile_hib.getLastName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getLastName();
            }

            List<MemberContribution> coop_members_hib = new ArrayList<>();//replace with query to get cooperative member loan history
            List<com.radicalsac.models.MemberContribution> mp_model_out = new ArrayList<>();

            for (MemberContribution mlh : coop_members_hib) {
                com.radicalsac.models.MemberContribution mp_model = new com.radicalsac.models.MemberContribution();
                //LoanType loan_type_hib = new LoanType(); //replace with query to get loantype

                mp_model.setId(mlh.getId());
                mp_model.setMemberAccountId(mlh.getMemberAccountId());
                mp_model.setMemberName(m_fullName);
                mp_model.setAmount(mlh.getAmount());
                mp_model.setBalance(mlh.getBalance());
                mp_model.setNarration(mlh.getNarration());
                mp_model.setValueDate(mlh.getValueDate());
                mp_model.setSavingsType(mlh.getSavingsType());
                mp_model.setSavingsTypeId(mlh.getSavingsTypeId());
                mp_model_out.add(mp_model);
            }
            resp.setRetn(0);
            resp.setDesc("Member contribution queried successfully.");
            resp.setBody(mp_model_out);
            logger.info("Member contribution queried successfully. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query member contributions. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying member contributions. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error querying member contribution - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }

    /**
     * Request to query member withdrawals
     *
     * @param login the logged in user
     * @param memberAccountId the member account ID
     * @param pageSize the page size
     * @param pageNumber the page number
     * @return response to the query member withdrawal request
     */
    public Response viewMemberWithdrawals_Request(UserAuth login, int memberAccountId, int pageSize, int pageNumber) {
        Response resp = new Response();
        logger.info("Request to query member withdrawal, invoked by [{}]", login.getUsername());
        try {
            if (!true) {//replace with checkCooperativeMemberExist(cooperativeId)
                resp.setRetn(301);
                resp.setDesc("The specified member account does not exist, please contact system administrator.");
                logger.info("The specified member account [{}] does not exist, please contact system administrator. [{}] ", memberAccountId, login.getUsername());
                return resp;
            }
            if (pageSize <= 0 || pageNumber <= 0) {
                resp.setDesc("The page size or page number is not properyly set.");
                logger.info("The page size or page number is not properyly set page size {}, page number {}, please contact system administrator. [{}] ", pageSize, pageNumber, login.getUsername());
                return resp;
            }

            MemberProfile memberProfile_hib = new MemberProfile();//replace with query to get member profile
            String m_fullName = memberProfile_hib.getFirstName();
            if (memberProfile_hib.getMiddleName() != null && !memberProfile_hib.getMiddleName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getMiddleName();
            }
            if (memberProfile_hib.getLastName() != null && !memberProfile_hib.getLastName().trim().isEmpty()) {
                m_fullName += " " + memberProfile_hib.getLastName();
            }

            List<MemberContribution> coop_members_hib = new ArrayList<>();//replace with query to get cooperative member loan history
            List<com.radicalsac.models.MemberContribution> mp_model_out = new ArrayList<>();

            for (MemberContribution mlh : coop_members_hib) {
                com.radicalsac.models.MemberContribution mp_model = new com.radicalsac.models.MemberContribution();
                //LoanType loan_type_hib = new LoanType(); //replace with query to get loantype

                mp_model.setId(mlh.getId());
                mp_model.setMemberAccountId(mlh.getMemberAccountId());
                mp_model.setMemberName(m_fullName);
                mp_model.setAmount(mlh.getAmount());
                mp_model.setBalance(mlh.getBalance());
                mp_model.setNarration(mlh.getNarration());
                mp_model.setValueDate(mlh.getValueDate());
                mp_model.setSavingsType(mlh.getSavingsType());
                mp_model.setSavingsTypeId(mlh.getSavingsTypeId());
                mp_model_out.add(mp_model);
            }
            resp.setRetn(0);
            resp.setDesc("Member withdrawal queried successfully.");
            resp.setBody(mp_model_out);
            logger.info("Member withdrawal queried successfully. [{}] ", login.getUsername());
            return resp;

        } catch (Exception ex) {
            resp.setRetn(99);
            resp.setDesc("General Error: Unable to query member withdrawal. Contact system administrator." + "\nMessage: " + ex.getMessage());
            logger.info("Error querying member withdrawal. See error log. [{}] - [{}]", resp.getRetn(), login.getUsername());
            logger.error("Error querying member withdrawal - [" + login.getUsername() + "]", ex);
            return resp;
        }
    }
}
