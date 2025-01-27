<h1 align="center" style="font-weight: bold;">Arrecadou 💰</h1>

<div align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java">
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" alt="hibernate">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="spring">
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</div>

<br> 
<p align="center">
 <a href="#started">Getting Started</a> • 
 <a href="#features">Features</a> •
 <a href="#libraries">External Libraries</a>
</p>

<br>

<p align="center">
  <b>A desktop system for managing fundraising actions by entities and groups.</b>
</p>

---

<h2 id="started">🚀 Getting Started</h2>

### Prerequisites

Make sure you have installed:

- Java 17 or later
- Maven for dependency management
- MySQL for the database

### Cloning the Project

Clone the repository:

```bash
git clone https://github.com/RafaYudi33/Arrecadou.git
cd Arrecadou
```

### Database Configuration

Before running the application, configure the database in the **`application.properties`** file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/arrecadou
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Running the Application

1. Compile the project with Maven:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   java -jar target/Arrecadou.jar
   ```

---

<h2 id="features">📍 Features</h2>

> ### 🔹 **1. Entity Registration**
> - When starting the system for the first time, it requests **entity/group data** that will conduct fundraising.

---

> ### 🧑‍💼 **2. Coordinator Registration**
> - Allows registering **coordinators** responsible for actions, providing:
>   - Name
>   - CPF
>   - Phone number

---

> ### 🎭 **3. Types of Actions**
> - **Event Production Action:**
>   - Events for fundraising, such as **charity events**.
>   - Registration of **expected supplies**.
>   - Definition of **collaborators** (cooks, attendants, etc.).
>   - Registered information:
>     - Event name
>     - Objective
>     - Description
>     - Start and end date
> - **Direct Contribution Action:**
>   - Calls for **soliciting financial donations**.

---

> ### 💸 **4. Donation Registration**
> - **For Direct Contributions:**
>   - Selects the action for which the donation is intended.
>   - Donor information:
>     - Name
>     - Phone number
>     - Donation amount
>     - Option for **anonymous donation**.
> - **For Event Production:**
>   - Donations can be **in money** or **in items**.
>   - For item donations:
>     - Item name
>     - Quantity (kg)
>     - Donor name
>     - Phone number
>     - Option for **anonymous donation**.

---

> ### 📊 **5. Fundraising Reports**
> - Generation of **final reports for direct contribution actions**, containing:
>   - Action name, description, start and end dates, objective.
>   - Coordinators and their contacts.
>   - Donor names (if not anonymous).
>   - Total amount raised.
>   - List of donations and their values.
> - Reports for **charity events** include:
>   - Action name, description, start and end dates, objective.
>   - Coordinators and their contacts.
>   - Donor names (if not anonymous).
>   - Total sales value.
>   - Net profit (including monetary and item donations).
>   - List of registered collaborators.
>   - Required supplies.
>   - Profit if no donations (neither money nor items) were made.
>   - Detailed list of received donations (money and items).

---

<h2 id="libraries">🔌 External Libraries</h2>

To enhance the system's functionality and performance, the following external libraries have been integrated:

- **Apache PDFBox**: Used for generating PDF reports.
---

📌 *Project developed to facilitate fundraising management by entities and organized groups.* 🚀
