package entity;

public class Contact {
    private String phoneNumber;
    private String GroupOfContacts;
    private String ContactName;
    private String ContactSex;
    private String ContactAddress;
    private String ContactBirthday;
    private String ContactEmail;

    public Contact() {
    }

    public Contact(String phoneNumber, String groupOfContacts, String contactName, String contactSex, String contactAddress, String contactBirthday, String contactEmail) {
        this.phoneNumber = phoneNumber;
        GroupOfContacts = groupOfContacts;
        ContactName = contactName;
        ContactSex = contactSex;
        ContactAddress = contactAddress;
        ContactBirthday = contactBirthday;
        ContactEmail = contactEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroupOfContacts() {
        return GroupOfContacts;
    }

    public void setGroupOfContacts(String groupOfContacts) {
        GroupOfContacts = groupOfContacts;
    }

    public String getContactSex() {
        return ContactSex;
    }

    public void setContactSex(String contactSex) {
        ContactSex = contactSex;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactAddress() {
        return ContactAddress;
    }

    public void setContactAddress(String contactAddress) {
        ContactAddress = contactAddress;
    }

    public String getContactBirthday() {
        return ContactBirthday;
    }

    public void setContactBirthday(String contactBirthday) {
        ContactBirthday = contactBirthday;
    }

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String contactEmail) {
        ContactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "số điện thoại: "+this.getPhoneNumber()+","+
                "nhóm: "+this.getGroupOfContacts()+","+
                "họ và tên: "+this.getContactName()+","+
                "giới tính: "+this.getContactSex()+","+
                "địa chỉ: "+this.getContactAddress()+","+
                "ngày sinh: "+this.getContactBirthday()+","+
                "email: "+this.getContactEmail();
    }
}
