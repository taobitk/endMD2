package repository;

import entity.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactsRepository {
    public static final String SRC_contact = "src/data/data.csv";
    File file = new File(SRC_contact);
    public static List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(SRC_contact);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            Contact contact;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                contact = new Contact(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6]);
                contacts.add(contact);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return contacts;
    }
    public static void save(Contact s) {
        File file = new File(SRC_contact);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(s.getPhoneNumber() + ","
                    + s.getGroupOfContacts() + ","
                    + s.getContactName() + ","
                    + s.getContactSex() + ","
                    + s.getContactAddress()+","
                    + s.getContactBirthday()+","
                    +s.getContactEmail());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }

    private static void writeFile(List<Contact> contacts, boolean append) {
        File file = new File(SRC_contact);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append))) {
            for (Contact temp : contacts) {
                bufferedWriter.write(toString(temp));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }
    private static String toString(Contact s) {
      return s.getPhoneNumber()+","+s.getGroupOfContacts()+","+s.getContactName()+","+ s.getContactSex()+","+s.getContactAddress()+","+s.getContactBirthday()+","+ s.getContactEmail();
    }

    public static void deleteByNumber(String number) {
        List<Contact> contact = getAll();
        boolean found = false;
        for (int i = 0; i < contact.size(); i++) {
            if (contact.get(i).getPhoneNumber().equals(number)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("nhập Y để xóa nhập phím bất kỳ để Hủy");
                String Y = scanner.nextLine();
                if (Y.equalsIgnoreCase("Y")) {
                    contact.remove(i);
                    found = true;
                    writeFile(contact, false);
                    System.out.println("Xóa thành công.");
                    break;
                }else {
                    found = true;
                    System.out.println("bạn đã hủy");
                }

            }
        }
        if (!found) {
            System.out.println("số điện thoại không tồn tại.");
        }

    }
    public static List<Contact> searchContacts(String query, List<Contact> contacts) {
        List<Contact> results = new ArrayList<>();
        query = query.toLowerCase();
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().toLowerCase().contains(query) ||
                    contact.getContactName().toLowerCase().contains(query)) {
                results.add(contact);
            }
        }
        return results;
    }

}
