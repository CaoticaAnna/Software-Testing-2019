package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String surName;
  private final String name;
  private final String address;
  private final String phone;
  private final String email;
  private final String day;
  private final String month;
  private final String year;
  private String group;

  public ContactData(String surName, String name, String address, String phone, String email, String day, String month, String year, String group) {
    this.id = Integer.MAX_VALUE;
    this.surName = surName;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.day = day;
    this.month = month;
    this.year = year;
    this.group = group;
  }
  public ContactData(int id, String surName, String name) {
    this.id = id;
    this.surName = surName;
    this.name = name;
    this.address = null;
    this.phone = null;
    this.email = null;
    this.day = null;
    this.month = null;
    this.year = null;
    this.group = null;
  }

  public ContactData(String surName, String name) {
    this.id = Integer.MAX_VALUE;
    this.surName = surName;
    this.name = name;
    this.address = null;
    this.phone = null;
    this.email = null;
    this.day = null;
    this.month = null;
    this.year = null;
    this.group = null;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
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

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(surName, that.surName) &&
            Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(surName, name);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", surName='" + surName + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

}
