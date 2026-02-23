# 🌱 Plant Guard AI – Backend

Plant Guard AI is a backend service designed to help farmers identify plant diseases and find the right pesticides. By uploading an image of a plant leaf, the system predicts the disease using a custom-trained CNN model (served via Flask) and provides nearby shops where farmers can purchase the recommended pesticides (managed via Spring Boot).

---

## 📑 Project Metadata
- **University:** University of Computer Studies, Mandalay (Cu-Mdy)
- **Event:** Project Show 2026–2027
- **Year:** 4th Year Senior Project
- **Author (Backend):** [Zin Min Thet](https://github.com/zinminteht-s)
- **Author (Frontend Mobile):** [Khan Zayar Soe](https://github.com/khantzayarsoe)
- **Purpose:** Academic showcase demonstrating AI + enterprise backend integration for agriculture

---

## 🚀 Features
- **Image-based disease detection** using a CNN model trained on agricultural datasets.
- **Flask microservice (`aiservieapi`)** for AI model inference (`trained_model1.h5`).
- **Spring Boot service (`plantguardai`)** for:
  - Account creation and authentication
  - Data insertion and management
  - Pesticide mapping and shop management
  - REST APIs for prediction, shop lookup, and user operations
- **Scalable architecture** designed to integrate seamlessly with mobile or web applications.

---

## 📂 Project Structure

- **Plant-Guard-Ai/**
  - **aiservieapi/** → Python Flask service
    - `main.py` → Flask entrypoint
    - `trained_model1.h5` → Custom CNN model
  - **plantguardai/** → Java Spring Boot service
    - **src/**
      - **main/java/com/zinminthet/plantguardai/**
        - `advices/`
        - `auth/`
          - `eventListeners/`
          - `exceptionHandlers/`
          - `providers/`
          - `services/`
        - `bootstrap/`
        - `configs/`
        - `controllers/`
        - `dtos/`
          - `requests/`
          - `responses/`
            - `shopResponseDto/`
        - `entities/`
        - `exceptions/`
        - `filters/`
        - `repositories/`
        - `services/`
        - `utils/`
      - **resources/**
    - `pom.xml` → Maven dependencies

---

## 📱 Mobile Integration

The mobile application interacts with the backend **through Spring Boot endpoints**.  
Spring Boot acts as the gateway, handling authentication, data management, and shop lookup, while internally delegating disease prediction requests to the Flask AI microservice.

The mobile app repository is available here:  
👉 [Plant Guard AI Mobile](https://github.com/khantzayarsoe/plant-guard-ai-mobile)

---

## 📜 License
This project is licensed under the MIT License.  
You are free to use, modify, and distribute this software, but **attribution is required**.  
Please credit the original authors when using this project:

- Frontend Mobile: [Khan Zayar Soe](https://github.com/khantzayarsoe)
- UI/UX: Ye Yint Zaw Oo
- Backend: [Zin Min Thet](https://github.com/zinminthet-s), [Aung Thaw](https://github.com/aungthaw11)
- AI: [Aung Thaw](https://github.com/aungthaw11), [Zin Min Thet](https://github.com/zinminthet-s)


See the [LICENSE](./LICENSE) file for full details.

---

## 🙏 Acknowledgements
- University of Computer Studies, Mandalay (UCSM) for providing the platform to showcase this project.  
- Faculty and mentors for their guidance throughout the academic journey.
