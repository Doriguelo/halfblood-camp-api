# 🔱 Camp Half-Blood API (CHB-System)

> *"Look, I didn't want to be a half-blood."* — But managing them is a different story.

This is a backend RESTful API built with **Java** and **Spring Boot** to help Chiron and Mr. D manage the daily operations of **Camp Half-Blood**. It handles camper registration, cabin allocation logic based on divine parentage, quest management, and automated monitoring of ancient oaths.

---

## 🛠️ Tech Stack

* **Java 17** (Core Language)
* **Spring Boot 3** (Framework)
* **Spring Data JPA** (Database Interaction)
* **H2 Database** (In-memory storage for development)
* **Lombok** (Boilerplate reduction)
* **Maven** (Dependency Management)

---

## ⚡ Key Features

### 1. Camper Management & The Sorting Logic 🏕️
The system automatically assigns campers to one of the **20 canon cabins** based on their divine parent.
* **Undetermined Campers:** Automatically sent to **Hermes Cabin (11)** until claimed.
* **The Artemis Rule:** Only female campers with a vow of chastity are accepted into Cabin 8.
* **The "Big Three" Pact:** Children of Zeus, Poseidon, and Hades are flagged with a `High Danger Level`.
* **Canon Accuracy:** Includes cabins for minor gods (Hecate, Hebe, Tyche, etc.) post-*The Last Olympian*.

### 2. The Quest System (Many-to-Many) ⚔️
Campers can form teams to go on quests.
* **The Rule of Three:** The system validates that a quest team must have **exactly three members**, respecting the ancient laws.
* **Database:** Implements a `@ManyToMany` relationship between `Demigod` and `Mission`.

### 3. The Oracle of Delphi 🔮
A specific endpoint that connects to the spirit of Delphi.
* Returns randomized, cryptic prophecies for registered quests.
* **Endpoint:** `GET /api/oracle/prophecy`

### 4. Background Jobs (Automated Monitoring) ⏳
Uses **Spring Scheduler** to run background tasks:
* **The Oath of the River Styx:** Every 20 seconds (simulating time passing), the system scans for campers older than 13 who haven't been claimed yet, alerting the Gods of a pact violation.

---

## 🚀 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Doriguelo/halfblood-camp-api](https://github.com/YOUR-USERNAME/camp-half-blood-api.git)
    ```
2.  **Navigate to the folder:**
    ```bash
    cd halfblood-camp-api
    ```
3.  **Run with Maven:**
    ```bash
    mvn spring-boot:run
    ```
4.  The API will be available at `http://localhost:8080`.

---

## 📡 API Endpoints & Examples

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **Campers (Semideuses)** | | |
| `POST` | `/api/camp/enter` | Registers a new camper (Auto-sorting logic). |
| `GET` | `/api/camp` | Lists all campers. |
| `GET` | `/api/camp/{id}` | Retrieves details of a specific camper. |
| `DELETE`| `/api/camp/{id}` | Expels a camper (Protected by Mission Lock). |
| **Quests (Missões)** | | |
| `POST` | `/api/missions` | Starts a new Quest (Requires 3 campers). |
| `GET` | `/api/mission` | Lists all active and completed quests. |
| `PUT` | `/api/missoes/{id}/complete` | Marks a quest as completed. |
| `DELETE`| `/api/missions/{id}` | Cancels a quest. |
| **Oracle** | | |
| `GET` | `/api/oraculo/prophecy` | Consults the Oracle manually. |

### 1. Register a Camper (POST)
**Route:** `/api/camp/enter`

*Scenario: Travis Stoll arrives (Undetermined).*
```json
{
  "name": "Travis Stoll",
  "age": 14,
  "genre": "M"
  // No divine parent provided -> Goes to Hermes (11)
}
```

*Scenario: Percy Jackson arrives (Big Three).*
```json
{
  "name": "Percy Jackson",
  "age": 12,
  "genre": "M",
  "divineRelative": "Poseidon"
  // Danger Level set to HIGH automatically
}
```

---

### 2. Start a mission (POST)
**Route:** `/api/missions`

*Rule: Must provide exactly 3 valid Camper IDs.*
```json
{
  "name": "The Labyrinth of Daedalus",
  "oracle": "You'll delve in the labyrinth, unending and deep, The dead, the traitor, and the lost shall rise up, Ascend or fall by the ghost king's hand, The final stand of Athena's child's command, Destruction comes with the hero's last breath, And lose a love for something worse than death.",
  "group": [
    {"id": 1},
    {"id": 2},
    {"id": 3}
  ]
}
```

---

### 3. Consult the Oracle (GET)
**Route:** `/api/oracle/prophecy`
```json
--- THE GREEN MIST SWIRLS AND THE ORACLE SPEAKS ---
"Seven half-bloods shall answer the call.To storm or fire, the world must fall.An oath to keep with a final breath,And foes bear arms to the Doors of Death"
```

---

## Data Integrity Protocols 🛡️
The system protects the timeline and database consistency:
* **Mission Lock:** You cannot delete/expel a camper if they are currently assigned to an active Quest. The system returns a `400 Bad Request` with a specific denial message.
* **Empty State Handling:** Background jobs intelligently detect if the database is empty before running logic, preventing "false positives" in logs.

---

## 📜 Database (H2 Console)
Since this project uses H2 (In-Memory Database), you can inspect the tables directly in your browser while the app is running.

* Go to: http://localhost:8080/h2-console
* JDBC URL: jdbc:h2:mem:campdb
* User: sr_d
* Password: wine

---

## ⚠️ Warning for Developers

**Do not delete the `src/main/resources/application.properties` file.**
If you do, the H2 Database configuration will be lost, and the campers will be lost in the Labyrinth (data persistence failure).

**System Rules:**
1.  A Quest **must** have exactly 3 members.
2.  Camper age must be > 0.
3.  Do not look directly at the Oracle output if you are not prepared for the truth.

---

## 🤝 Contributing
Demigods from all cabins are welcome to contribute.

* Fork the project
* Create your feature branch (git checkout -b feature/NewCabin).
* Commit your changes (git commit -m 'feat: Added Hypnos Cabin logic').
* Push to the branch.
* Open a Pull Request.

#
_Developed by Tiago Doriguelo_
