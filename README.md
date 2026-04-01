# Gym Management System

A console-based gym management system built in Java, developed as the final project for Object-Oriented Programming [CIS250].

## Overview

The system supports three user roles — **Admin**, **Coach**, and **Customer** — each with their own login and set of features. Data is loaded from a structured text file (`input.txt`) at startup.

## User Roles & Features

### Customer
- View assigned coach's info
- List all gym equipment
- View membership plan details
- View in-body measurements at a specific date
- Calculate how many kilos need to be reduced based on body data

### Coach
- List all assigned customers
- View in-body history for any customer
- Get full details of a customer by name
- Filter customers by gender (male/female)

### Admin
- **CRUD** operations on gym entities (Add / Edit / Delete by ID)
- View subscription history for a customer
- Display all customers who subscribed in a given month or day
- Display all customers of a specific coach
- Display gym income for a given month
- Display coaches sorted by number of customers

## Data Format

The system reads from `input.txt` (and `input1.txt` for alternate scenarios). The file is structured into sections:

```
Gym, Equipment, MembershipPlan, Subscription, InBody, Coach, Customer
```

Each section contains records for the corresponding entity.

## Project Structure

```
├── src/main/java/        # Java source files (OOP classes)
├── target/classes/       # Compiled output
├── input.txt             # Primary data file
├── input1.txt            # Alternate scenario data
├── pom.xml               # Maven build file
└── README.md
```

## Getting Started

### Prerequisites

- Java 11+
- Maven

### Build & Run

```bash
mvn compile
mvn exec:java
```

Or open in IntelliJ IDEA and run `Main.java` directly.

# Scenarios

### First

- the user chooses what type of user he or she is (Admin, Customer, Coach)
- Then the user chooses to log in or register

## Customer

Login: Mark Doe, pass941

1. Get his coach's info: Coach Johnson
2. Get a List of all Gym Equipments
3. Display his membership plan details
4. Display his in-body information at a specific date:
    1. input1: 03/05/2020
    2. input2: 03/06/2020
5. Display how many kilos need to be reduced according to his body.

## Coach

Login: Coach Johnson, pass333

1. Show the list of the coach customers
2. Get the inbody history of any of his customers.
    1. input: Lisa Wilson
3. Get all the details of a customer by his name.
    1. input: Mark Doe
4. Show a list of all his female/male customers.
    1. input1: female

## Admin

1. (add/edit/delete)
    1. Add: Enter the data of your entity
    2. Edit: Enter the ID of the entity you want to edit, then enter the attribute you want to edit
    3. Delete: Enter the ID of the entity you want to delete
2. Show the subscription history for a customer
    1. Input: Lisa Wilson
3. Display all the customers that subscribed to the gym in a given month/day.
    1. Input1: m, 6
    2. Input2: d, 5 (lsa)
4. Display all the customers of a specific coach
    1. Input: 1
5. Display Gym Income in a given month:
    1. input1: 1, 8, 2022
    2. Input2: 1, 5, 2020
6. Display Sorted Coaches (fix no of customers)
