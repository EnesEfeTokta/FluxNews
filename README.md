# FluxNews

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com)
[![Language](https://img.shields.io/badge/Language-Kotlin-orange.svg)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-GPL-blue.svg)](LICENSE)

**FluxNews is a modern Android news reader application developed with Kotlin, bringing the latest news from various RSS feeds and news APIs right to your fingertips.**

It presents news in categories with a user-friendly interface and offers features like liking, saving, and source management for a personalized reading experience. Thanks to Google's powerful Gemini API, FluxNews allows for instant summarization of articles, providing quick access to information.

---

## ✨ Key Features

*   **Multi-Source Support:** Fetches content from popular RSS feeds and news APIs (e.g., NewsAPI, etc. - *specify the ones you use*).
*   **Smart Categorization:** Automatically sorts news into relevant categories (Technology, Sports, World, etc.).
*   **Personalization:**
    *   **Like & Save:** Like and save news articles you're interested in to read later or access easily.
    *   **Content Management:** Report unwanted news or block content from specific sources in the future (*Clarify the detail of this feature: Will the source be blocked, or just the specific news item hidden?*).
*   **🚀 AI-Powered Summarization:** Get summaries of long news articles with a single tap through Google Gemini API integration.
*   **Firebase Integration:** Uses Firebase Firestore/Realtime Database and Authentication to securely store user data (likes, saved items, preferences).
*   **Modern UI/UX:** Clean, intuitive user interface adhering to Material Design principles.
*   **Kotlin First:** Developed entirely in Kotlin, utilizing Coroutines and modern Android development practices.

---

## 📸 Screenshots (Example)

*(Add attractive screenshots or a short GIF demo of your application here)*

| Main Feed                              | Category View                         | Article Detail & Summarization         | Saved Items                          |
| :------------------------------------: | :-----------------------------------: | :------------------------------------: | :-----------------------------------: |
| `[Main Feed Screenshot Here]`          | `[Categories Screenshot Here]`        | `[Article Detail Screenshot Here]`     | `[Saved Items Screenshot Here]`       |
| *(Example: Screen showing news list)* | *(Example: Screen showing categories)* | *(Example: News reading & AI button)* | *(Example: List of saved news items)* |

---

## 🛠️ Tech Stack / Tools

*   **Programming Language:** [Kotlin](https://kotlinlang.org/)
*   **Asynchronous Operations:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
*   **UI:** Android SDK, XML Layouts, [Material Components for Android](https://material.io/develop/android/docs/getting-started)
*   **Architecture Pattern:** MVVM (Model-View-ViewModel)
*   **Database & Backend:** [Firebase](https://firebase.google.com/)
    *   [Firestore](https://firebase.google.com/docs/firestore) or [Realtime Database](https://firebase.google.com/docs/database) (For data storage)
    *   [Firebase Authentication](https://firebase.google.com/docs/auth) (For user management)
*   **AI:** [Google Gemini API](https://ai.google.dev/)
*   **Networking:** [Retrofit 2](https://square.github.io/retrofit/) (For APIs), [Library Name - e.g., Rome, AndroidFeedParser] (For RSS - *specify the one you use*)
*   **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) or [Koin](https://insert-koin.io/) (*specify the one you use or plan to*)
*   **Image Loading:** [Glide](https://github.com/bumptech/glide) or [Coil](https://coil-kt.github.io/coil/) (*specify the one you use*)
*   **Version Control:** Git & GitHub

---

## ⚙️ Setup and Running

Follow the steps below to run the project on your local machine:

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/EnesEfeTokta/FluxNews.git
    cd FluxNews
    ```

2.  **Open in Android Studio:** Open the project with Android Studio (Arctic Fox or newer recommended).

3.  **Firebase Setup:**
    *   Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project.
    *   Add an Android app to your project. Ensure you enter the correct package name (`applicationId` - usually found in the `app/build.gradle.kts` file).
    *   Enable the desired authentication methods (e.g., Email/Password, Google Sign-In) in the Firebase Authentication section.
    *   Create a Firebase Firestore or Realtime Database instance and set up the security rules (It can be in test mode initially, but define more secure rules for production).
    *   Download the `google-services.json` file from your Firebase project settings.
    *   Copy the downloaded `google-services.json` file into the `app/` directory of your project.

4.  **API Keys:**
    *   **Gemini API:**
        *   Obtain a Gemini API key from [Google AI Studio](https://aistudio.google.com/app/apikey).
        *   Create a file named `local.properties` in the root directory of your project (if it doesn't exist).
        *   Add your API key to this file. **Never commit this file to Git!** (Ensure `local.properties` is in your `.gitignore` file).
          ```properties
          # local.properties
          GEMINI_API_KEY="YOUR_GEMINI_API_KEY"
          ```
    *   **News APIs (If any):** Similarly, add the keys for other news APIs you use to the `local.properties` file (e.g., `NEWS_API_KEY="YOUR_NEWS_API_KEY"`).
    *   *Note: You will need to set up how to read these keys within your code (usually via `BuildConfig` as shown in the previous Turkish example's notes).*

5.  **Gradle Synchronization:** Wait for Android Studio to synchronize the project (It usually starts automatically; if not, click the "Sync Project with Gradle Files" button).

6.  **Compile and Run:** Run the application on an emulator or a physical device.

---

## 🏗️ Architecture

FluxNews follows the MVVM (Model-View-ViewModel) architectural pattern to ensure a scalable and maintainable codebase. The layers are generally as follows:

*   **UI Layer (Activity/Fragment):** Receives user interactions, displays data from the ViewModel.
*   **ViewModel Layer:** Prepares data required for the UI, requests data from the Repository, and manages the UI state. It is lifecycle-aware.
*   **Repository Layer:** Abstracts data sources (Remote, Local, Firebase). Provides a single point of access to data for the ViewModel. Contains logic for caching or merging data from different sources.
*   **Data Layer:**
    *   **Remote:** Retrofit (API) and RSS Parser services.
    *   **Local:** (If available) Room database or DataStore (for caching or offline support).
    *   **Firebase:** Firestore/RealtimeDB and Auth services.

---

## 🤝 Contributing

Contributions help make the project better! If you'd like to contribute:

1.  Fork the Project.
2.  Create a new Feature Branch (`git checkout -b feature/AmazingFeature`).
3.  Make your changes and Commit them (`git commit -m 'Add some AmazingFeature'`).
4.  Push your Branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.

Please ensure your code runs and adheres to the project's coding style before opening a Pull Request. For bug reports and feature requests, please use the GitHub Issues section.

---

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.
*(Note: Your badge shows GPL. Please ensure the badge, this text, and the LICENSE file are consistent.)*

---

## 📬 Contact

Email: [enesefetokta@gmail.com](mailto:enesefetokta@gmail.com)

LinkedIn: [https://www.linkedin.com/in/enes-efe-tokta/](https://www.linkedin.com/in/enes-efe-tokta/)

Project Link: [https://github.com/EnesEfeTokta/FluxNews](https://github.com/EnesEfeTokta/FluxNews)

---

_Made with ❤️ using Kotlin and Android_