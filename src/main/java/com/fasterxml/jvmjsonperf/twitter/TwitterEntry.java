package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.StdItem;

public class TwitterEntry
    extends StdItem<TwitterEntry>
{
    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean _equals(TwitterEntry other) {
        return (id == other.id)
                && _eq(text, other.text)
                && (from_user_id == other.from_user_id)
                && (to_user_id == other.to_user_id)
                && _eq(from_user, other.from_user)
                && _eq(to_user, other.to_user)
                && _eq(iso_language_code, other.iso_language_code)
                && _eq(profile_image_url, other.profile_image_url)
                && _eq(created_at, other.created_at)
            ;
    }

    private boolean _eq(Object o1, Object o2)
    {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null) {
            return false;
        }
        return o1.equals(o2);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        sb.append("id=").append(id);
        sb.append("; Text=").append(text);

        sb.append(";from_user_id =").append(from_user_id);
        sb.append(";from_user_id =").append(to_user_id);
        sb.append(";from_user =").append(from_user);
        sb.append(";to_user =").append(to_user);
        sb.append(";iso_language_code =").append(iso_language_code);
        sb.append(";iso_language_code =").append(profile_image_url);
        sb.append(";created_at =").append(created_at);
        
        sb.append("}");
        return sb.toString();
    }
}