package ru.stqa.pft.addressbook;

public class ContactData {
  private final String surName;
  private final String name;
  private final String address;
  private final String phone;
  private final String email;
  private final String day;
  private final String month;
  private final String year;

  public ContactData(String surName, String name, String address, String phone, String email, String day, String month, String year) {
    this.surName = surName;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public String getSurName() {
    return surName;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getDay() {
    return day;
  }

  public String getMonth() {
    return month;
  }

  public String getYear() {
    return year;
  }
}
