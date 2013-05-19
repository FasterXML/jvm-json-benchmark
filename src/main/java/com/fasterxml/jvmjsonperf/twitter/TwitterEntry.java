package com.fasterxml.jvmjsonperf.twitter;

public class TwitterEntry
{
    public int id;
    public String text;
    public String from_user, to_user;
    public int from_user_id, to_user_id;
    public String iso_language_code;
    public String profile_image_url;
    public String created_at;
    
    public TwitterEntry() { }

    public void setId(int v) { id = v; }
    public void setText(String v) { text = v; }

    public void setFrom_user_id(int v) { from_user_id = v; }
    public void setTo_user_id(int v) { to_user_id = v; }
    
    public void setFrom_user(String v) { from_user = v; }
    public void setTo_user(String v) { to_user = v; }
    public void setIso_language_code(String v) { iso_language_code = v; }
    public void setProfile_image_url(String v) { profile_image_url = v; }
    public void setCreated_at(String v) { created_at = v; }

    public int getId() { return id; }
    public String getText() { return text; }
    
    public int getFrom_user_id() { return from_user_id; }
    public int getTo_user_id() { return to_user_id; }
    
    public String getFrom_user() { return from_user; }
    public String getTo_user() { return to_user; }
    public String getIso_language_code() { return iso_language_code; }
    public String getProfile_image_url() { return profile_image_url; }
    public String getCreated_at() { return created_at; }
}