package com.assessment.web.dto;

public class Payment_request {
    private String id;

    private String phone;

    private String email;

    private String buyer_name;

    private String amount;

    private String purpose;

    private String expires_at;

    private String status;

    private boolean send_sms;

    private boolean send_email;

    private String sms_status;

    private String email_status;

    private String shorturl;

    private String longurl;

    private String redirect_url;

    private String webhook;

    private boolean allow_repeated_payments;

    private String customer_id;

    private String created_at;

    private String modified_at;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setBuyer_name(String buyer_name){
        this.buyer_name = buyer_name;
    }
    public String getBuyer_name(){
        return this.buyer_name;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }
    public String getAmount(){
        return this.amount;
    }
    public void setPurpose(String purpose){
        this.purpose = purpose;
    }
    public String getPurpose(){
        return this.purpose;
    }
    public void setExpires_at(String expires_at){
        this.expires_at = expires_at;
    }
    public String getExpires_at(){
        return this.expires_at;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setSend_sms(boolean send_sms){
        this.send_sms = send_sms;
    }
    public boolean getSend_sms(){
        return this.send_sms;
    }
    public void setSend_email(boolean send_email){
        this.send_email = send_email;
    }
    public boolean getSend_email(){
        return this.send_email;
    }
    public void setSms_status(String sms_status){
        this.sms_status = sms_status;
    }
    public String getSms_status(){
        return this.sms_status;
    }
    public void setEmail_status(String email_status){
        this.email_status = email_status;
    }
    public String getEmail_status(){
        return this.email_status;
    }
    public void setShorturl(String shorturl){
        this.shorturl = shorturl;
    }
    public String getShorturl(){
        return this.shorturl;
    }
    public void setLongurl(String longurl){
        this.longurl = longurl;
    }
    public String getLongurl(){
        return this.longurl;
    }
    public void setRedirect_url(String redirect_url){
        this.redirect_url = redirect_url;
    }
    public String getRedirect_url(){
        return this.redirect_url;
    }
    public void setWebhook(String webhook){
        this.webhook = webhook;
    }
    public String getWebhook(){
        return this.webhook;
    }
    public void setAllow_repeated_payments(boolean allow_repeated_payments){
        this.allow_repeated_payments = allow_repeated_payments;
    }
    public boolean getAllow_repeated_payments(){
        return this.allow_repeated_payments;
    }
    public void setCustomer_id(String customer_id){
        this.customer_id = customer_id;
    }
    public String getCustomer_id(){
        return this.customer_id;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setModified_at(String modified_at){
        this.modified_at = modified_at;
    }
    public String getModified_at(){
        return this.modified_at;
    }
}
