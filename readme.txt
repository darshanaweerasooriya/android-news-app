# Android News App

This is an Android application developed using **Kotlin** that fetches news from the (https://newsapi.org/) and displays it to users in a clean and interactive interface. The app follows **MVVM architecture** and **Clean Architecture principles** while utilizing **Jetpack components**, **coroutines**, and **Room Database** for local storage.

---

## Features

- **User Registration & Login**
  - Users can register and login.
  - User data is stored locally using **SharedPreferences** or **Room Database**.

- **Home Screen**
  - Displays **Latest News** (top 5 news).
  - News feed section with pagination.
  - Users can search for news.
  - News filters by **Health, Technology, Finance, Art, Country, and Category**.

- **News Details**
  - Displays **news image, title, author, description, published date**, and full content.
  - Users can **add news to favorites**.

- **Favorites Section**
  - View and manage favorite news articles.

- **Profile Section**
  - View registered user details.

---

## Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM + Clean Architecture
- **Libraries & Frameworks:**
  - Retrofit (API calls)
  - Coroutines (Asynchronous programming)
  - Glide/Picasso (Image loading)
  - Room Database (Local storage)
  - Jetpack Components (LiveData, ViewModel, Navigation)
- **API:** [News API](https://newsapi.org/)
- **UI:** Material Design, ConstraintLayout

---

## Screens

1. **Registration & Login**
2. **Home Screen**
   - Latest News
   - News Feed
   - Filters & Search
3. **News Details**
4. **Favorites**
5. **Profile**

---

## Installation & Setup

1. Clone the repository:

```bash
git clone https://github.com/darshanaweerasooriya/android-news-app.git
