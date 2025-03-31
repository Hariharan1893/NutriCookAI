# **NutriCookAI - Recipe Suggestion API**  
**Version:** 1.0  
**Base URL:** `https://your-api-url.com/api/v1/recipes`  

## **Overview**  
NutriCookAI is an AI-powered API that generates personalized recipes based on user-provided ingredients, cuisine preference, and dietary requirements.

## **Endpoints**  

### **1. Generate Recipe**  
**Endpoint:**  
```http
POST /generate
```

**Description:**  
Generates a recipe based on the given ingredients, cuisine, and dietary preferences.

#### **Request Body (JSON)**
| Parameter    | Type     | Description                                      | Example                        |
|-------------|----------|--------------------------------------------------|--------------------------------|
| `ingredients` | `array`  | List of ingredients available for the recipe.   | `["chicken", "garlic", "tomato", "onion"]` |
| `cuisine`    | `string` | Preferred cuisine type.                         | `"Indian"`                     |
| `diet`       | `string` | Dietary preference (Keto, Vegan, etc.).         | `"Keto"`                        |

**Example Request:**
```json
{
  "ingredients": ["chicken", "garlic", "tomato", "onion"],
  "cuisine": "Indian",
  "diet": "Keto"
}
```

---

### **Response Format**
#### **Success Response (200 OK)**  
| Field         | Type     | Description                                 |
|--------------|----------|---------------------------------------------|
| `recipeName` | `string` | Name of the generated recipe.               |
| `ingredients` | `array`  | List of required ingredients.               |
| `instructions` | `array` | Step-by-step cooking instructions.         |
| `calories`   | `integer` | Approximate calorie count (if available).  |

**Example Response:**
```json
{
  "recipeName": "Keto Chicken Masala",
  "ingredients": [
    "Chicken thighs (boneless, skinless): 500g",
    "Onion (chopped): 1 medium",
    "Garlic (minced): 4 cloves",
    "Tomato (diced): 1 medium",
    "Ghee/Coconut Oil: 2 tbsp",
    "Masala Blend: 2 tsp (Turmeric, Chili powder, Coriander, Garam masala)"
  ],
  "instructions": [
    "1. Sauté onion and garlic in ghee until softened.",
    "2. Add tomatoes and masala blend, cook until soft.",
    "3. Add chicken and cook until browned.",
    "4. Simmer with 1/2 cup water until fully cooked.",
    "5. Stir in heavy cream, simmer for 2 minutes.",
    "6. Serve hot, garnished with fresh cilantro."
  ],
  "calories": 520
}
```

---

### **Error Handling**
| Status Code | Meaning                  | Cause |
|------------|--------------------------|------------------------------------------------|
| `400 Bad Request` | Invalid request format. | Missing or incorrect request parameters. |
| `401 Unauthorized` | Authentication failed. | API key is missing or incorrect. |
| `500 Internal Server Error` | Unexpected server issue. | Temporary issue, try again later. |

**Example Error Response (400 Bad Request):**
```json
{
  "error": "Invalid request. Missing required fields: 'ingredients'."
}
```

---

## **Usage Example (cURL)**
```sh
curl -X POST "https://your-api-url.com/api/v1/recipes/generate" \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_API_KEY" \
-d '{
  "ingredients": ["chicken", "garlic", "tomato", "onion"],
  "cuisine": "Indian",
  "diet": "Keto"
}'
```

---

## **Notes**
- The API provides concise recipes with minimal steps and clear ingredient lists.
- Recipes may vary based on AI-generated responses.
- This API is optimized for efficiency using **Spring WebFlux** for non-blocking async calls.
