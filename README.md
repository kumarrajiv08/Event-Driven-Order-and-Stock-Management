

# Event-Driven Order and Stock Management

## Overview

An Event Driven microservices architecture project that 

## Key Features

### 1. **Streamlined Order Placement**

Our system provides a user-friendly API through the Order Service, allowing users to place orders effortlessly. The moment an order is placed, a cascade of event-driven processes is set into motion.

### 2. **Real-Time Stock Availability**

The Stock Service plays a critical role in assessing stock availability in response to incoming orders. It promptly communicates the results back to the Order Service, ensuring that orders are fulfilled efficiently.

### 3. **Event-Driven Architecture**

Our project is entirely event-driven, enabling responsiveness and scalability. Kafka acts as the central nervous system for real-time communication among the various microservices.

### 4. **Dynamic Responses**

In a world where speed and accuracy are paramount, our system provides dynamic responses to order placements. It considers the real-time stock availability, ensuring an optimal user experience. Orders are either confirmed with success or flagged for further action based on stock availability.

### 5. **Data Storage and Management**

The project leverages a PostgreSQL database for robust data storage and management. It ensures that critical information about orders and stock is secure and accessible.

