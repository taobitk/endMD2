package view;

import entity.Contact;
import repository.ContactsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static repository.ContactsRepository.*;

public class view {
    public static void main(String[] args) {
        while (true) {
            System.out.println("---------CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ--------- ");
            System.out.println("chọn chức năng theo số để tiếp tục");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. cập nhập");
            System.out.println("4. xóa");
            System.out.println("5. tìm kiếm");
            System.out.println("6. đọc từ file");
            System.out.println("7. ghi vào file");
            System.out.println("8. thoát");
            System.out.println("Mời bạn nhập lựa chọn");
            System.out.print("chọn chức năng");
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());
            List<Contact> contacts = new ArrayList<>();
            switch (choice) {
                case 1:
                    System.out.println("-----------------------------------------");
                    contacts = ContactsRepository.getAll();
                    display(contacts);
                    break;
                case 2:
                    System.out.println("-----------------------------------------");
                    System.out.println("2. Thêm mới");
                    Contact contact;
                    contact = inputCT();
                    ContactsRepository.save(contact);
                    break;
                case 3:
                    System.out.println("-----------------------------------------");
                    System.out.println("3. Cập nhật");
                    System.out.print("Nhập số điện thoại của danh bạ cần sửa: ");
                    upgContact();
                    break;
                case 4:
                    System.out.println("-----------------------------------------");
                    System.out.println("nhập số mà bạn muốn xóa");
                    String nub = inputPhoneNumber();
                    ContactsRepository.deleteByNumber(nub);
                    break;
                case 5:
                    System.out.println("5. Tìm kiếm");
                    System.out.print("Nhập số điện thoại hoặc tên để tìm kiếm: ");
                    String query = scanner.nextLine().trim();
                    contacts = ContactsRepository.getAll();
                    List<Contact> searchResults = ContactsRepository.searchContacts(query, contacts);

                    if (searchResults.isEmpty()) {
                        System.out.println("Không tìm thấy kết quả phù hợp.");
                    } else {
                        System.out.println("Kết quả tìm kiếm:");
                        display(searchResults);
                    }
                    break;
                case 6:
                    System.out.println("6. đã tính hợp");
                    break;
                case 7:
                    System.out.println("7. đã tích hợp");
                    break;
                case 8:
                    return;
                default:
                    System.out.println("mời bạn nhận lại");
            }
        }
    }

    private static void upgContact() {
        List<Contact> contacts;
        String phoneNumber = inputPhoneNumber();
        Contact contactToUpdate = null;
        contacts = ContactsRepository.getAll();
        for (Contact contact2 : contacts) {
            if (contact2.getPhoneNumber().equals(phoneNumber)) {
                contactToUpdate = contact2;
                break;
            }
        }
        if (contactToUpdate == null) {
            System.out.println("Không tìm được danh bạ với số điện thoại trên.");
        } else {
            System.out.print("Nhập nhóm mới: ");
            String newGroup = inputGroup();
            System.out.print("Nhập họ tên mới: ");
            String newName = inputName();
            System.out.print("Nhập giới tính mới: ");
            String newSex = inputSex();
            System.out.print("Nhập địa chỉ mới: ");
            String newAddress = inputAddress();
            System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
            String newBirthday = inputDate();
            System.out.print("Nhập email mới: ");
            String newEmail = inputEmail();
            contactToUpdate.setGroupOfContacts(newGroup);
            contactToUpdate.setContactName(newName);
            contactToUpdate.setContactSex(newSex);
            contactToUpdate.setContactAddress(newAddress);
            contactToUpdate.setContactBirthday(newBirthday);
            contactToUpdate.setContactEmail(newEmail);
            ContactsRepository.save(contactToUpdate);
            System.out.println("Cập nhật thành công.");
        }
    }

    public static void display(List<Contact> list) {
        for (Contact anyThing : list) {
            System.out.println(anyThing);
        }
    }

    private static Contact inputCT() {
        String nub = inputPhoneNumber();
        String group = inputGroup();
        String name = inputName();
        String sex = inputSex();
        String address = inputAddress();
        String birthday = inputDate();
        String email = inputEmail();
        return new Contact(nub, group, name, sex, address, birthday, email);
    }

    public static String inputEmail() {
        Scanner scanner = new Scanner(System.in);
        String email;
        String regex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"; // Biểu thức regex cho email

        while (true) {
            System.out.print("Nhập email (vd: example@domain.com): ");
            email = scanner.nextLine().trim();

            if (email.matches(regex)) {
                break;
            } else {
                System.out.println("Email không hợp lệ. Vui lòng thử lại.");
            }
        }
        return email;
    }

    public static String inputPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;
        String regex = "^[0-9]{3,11}$";
        while (true) {
            System.out.print("Nhập số điện thoại (chỉ chứa các chữ số, độ dài 3-11): ");
            phoneNumber = scanner.nextLine().trim();

            if (phoneNumber.matches(regex)) {
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ. yêu cầu nhập 3-11 số.");
            }
        }
        return phoneNumber;
    }

    private static String inputDate() {
        Scanner scanner = new Scanner(System.in);
        Pattern datePattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$");
        while (true) {
            System.out.print(" nhập ngày sản xuất hợp lệ định dạng dd/mm/yyyy: ");
            String date = scanner.nextLine();
            Matcher nameMatcher = datePattern.matcher(date);
            if (!nameMatcher.matches()) {
                continue;
            }
            return date;
        }
    }

    private static String inputAddress() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Mời bạn nhập địa chỉ: ");
            String address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("làm ơn nhập địa chỉ vào đi mà");
            } else {
                return address;
            }
        }
    }

    private static String inputName() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Mời bạn nhập tên: ");
            String address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("làm ơn nhập tên vào đi mà");
            } else {
                return address;
            }
        }
    }

    private static String inputSex() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Mời bạn nhập giới tính: ");
            String address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("làm ơn nhập giới tính vào đi mà");
            } else {
                return address;
            }
        }
    }

    private static String inputGroup() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Mời bạn nhập nhóm: ");
            String address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("làm ơn nhập nhóm vào đi mà");
            } else {
                return address;
            }
        }
    }

}
