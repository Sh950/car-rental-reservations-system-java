Car Rental System - README

This project provides a simple Java-based implementation for a car rental system, demonstrating the use of object-oriented programming concepts such as encapsulation, constructors, inheritance, and polymorphism.

Project Structure

Classes

Car

Represents a car with attributes like license number, type, brand, and gear type.

Key Features:

Validation for license number (7-digit requirement) and type (allowed: A, B, C, D).

Ability to compare cars based on type and gear preference.

Methods for string representation and equality checks.

Important Methods:

getId(), getType(), getBrand(), isManual()

toString(), equals(Car other), better(Car other), worse(Car other)

Rent

Represents a car rental, including the customer name, rented car, pickup date, and return date.

Key Features:

Enforces valid rental periods (return date must be after pickup date).

Calculates rental duration and cost with discounts for full weeks.

Supports car upgrades and checks for overlapping rental periods.

Important Methods:

howManyDays(), getPrice(), upgrade(Car newCar)

overlap(Rent other), toString(), equals(Rent other)

Date

(Implementation is assumed based on the provided details in the PDF.)

Represents a date with support for validity checks, comparisons, and difference calculations.

Key Features:

Ensures proper date validation (leap years, valid days/months).

Provides utility methods like equals(Date other), before(Date other), and after(Date other).

Features

Data Validation: Ensures all inputs (dates, car types, etc.) adhere to specified rules.

Rental Price Calculation: Computes total cost with per-day rates and weekly discounts.

Car Comparison: Allows better/worse comparison based on car type and gear.

Rental Overlaps: Detects overlapping rentals for the same car and customer.

Encapsulation: Uses private fields and accessor/mutator methods to enforce encapsulation.

How to Use

Clone or download the project files (Car.java, Rent.java, Date.java).

Compile the files using a Java compiler:

bash


