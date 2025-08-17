# 🩺 Appointment App  

An Android application built with **Java**, **XML**, and **Firebase Realtime Database** to book doctor appointments with ease.  

## 🚀 Features
- 👨‍⚕️ List of doctors with specialization, rating & experience  
- 📅 Date & time slot selection for appointments  
- 🔎 Category-wise filtering (Work, Personal, etc.)  
- 📍 Doctor details page with address, patients count, and biography  
- 🎥 Multiple consultation modes: **Audio, Video, Chat**  
- 📊 Firebase Realtime Database integration for dynamic data  

---

## 🛠️ Tech Stack
- **Language**: Java  
- **UI**: XML (ConstraintLayout, RecyclerView, etc.)  
- **Architecture**: MVVM (Model-View-ViewModel)  
- **Database**: Firebase Realtime Database  
- **Libraries**:  
  - [Glide](https://github.com/bumptech/glide) – for image loading  
  - [Material Components](https://github.com/material-components/material-components-android)  

---

## 📂 Project Structure
AppointmentApp/
├── Activity/ # Activities (Main, Splash, Detail)
├── Adapter/ # RecyclerView Adapters (Date, Time, Doctor, Category)
├── Domain/ # Data Models (DoctorsModel, CategoryModel)
├── Repository/ # Firebase Data Fetch Logic
├── ViewModel/ # Business Logic & LiveData
├── res/ # Layouts, Drawables, Colors, Strings
└── AndroidManifest.xml
