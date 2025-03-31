# **NutriCookAI API 🍽️🤖**

A lightweight, AI-powered recipe suggestion API that generates personalized meal ideas based on given ingredients, cuisine preferences, and dietary restrictions.

---

## **📌 Features**

- Generates quick and crisp **recipe suggestions** using **Gemini AI**.
- Supports **cuisine** and **diet preferences**.
- Utilizes **Spring Boot WebFlux** for **non-blocking** API calls.
- Implements **caching** for improved performance.
- RESTful API with **JSON-based** input/output.

---

## **🚀 Getting Started**

### **1️⃣ Prerequisites**

Ensure you have the following installed:

- **JDK 17+**
- **Maven** (`mvn`)
- **Spring Boot**
- **Postman** (for API testing)

---

### **2️⃣ Clone the Repository**

```sh
git clone https://github.com/Hariharan1893/NutriCookAI.git
cd NutriCookAI
```

---

### **3️⃣ Configure Environment Variables**

Create an `.env` file (or use `application.properties`) to store API keys:

```properties
gemini.api-key=YOUR_GEMINI_API_KEY
gemini.api-url=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0:generateContent
```

---

### **4️⃣ Install Dependencies**

```sh
mvn clean install
```

---

### **5️⃣ Run the Application**

```sh
mvn spring-boot:run
```

The API will be accessible at:

```
http://localhost:8080
```

---

## **🔗 API Documentation**

For complete API structure, refer to [`docs.md`](./docs.md).

### **📩 Sample Request**

```json
{
  "ingredients": ["chicken", "garlic", "tomato", "onion"],
  "cuisine": "Indian",
  "diet": "Keto"
}
```

### **📤 Sample Response**

```json
{
  "recipeName": "Keto Chicken Masala",
  "ingredients": [
    "Chicken thighs: 500g",
    "Onion (chopped): 1 medium",
    "Garlic (minced): 4 cloves",
    "Tomato (diced): 1 medium",
    "Ghee: 2 tbsp",
    "Spices: Turmeric, Chili powder, Garam masala"
  ],
  "instructions": [
    "Sauté onion and garlic in ghee.",
    "Add tomato and spices, cook until soft.",
    "Add chicken, cook until done.",
    "Serve hot."
  ],
  "calories": 450
}
```

For more input JSON examples, refer to [`NutriCookAI.postman_collection.json`](./NutriCookAI.postman_collection.json).

---

## **🛠️ API Endpoints**

| Method | Endpoint      | Description                                           |
| ------ | ------------- | ----------------------------------------------------- |
| `POST` | `/api/recipe` | Generates a recipe based on ingredients & preferences |

---

## **📜 License**

This project is licensed under the MIT License.

---

## **🤝 Contributing**

Feel free to submit issues and pull requests.

---

## **📧 Contact**

For queries, reach out to **[Hariharan](https://www.linkedin.com/in/hariharanr18/)**.
