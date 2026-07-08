# DeXStudio Apps Collection

Welcome to the DeXStudio Multi-App workspace. This repository houses a collection of 15+ powerful, specialized applications built on a shared Kotlin Multiplatform engine.

## 🌟 Featured Apps
- **AppHide**: A secure vault for hiding your applications.
- **Calculator**: A multiplatform edge-to-edge calculation engine.
- **Passport Picture Generator**: An AI-powered tool to generate perfectly formatted passport photos.
- **WhatsApp Status Saver**: A quick utility to save and manage WhatsApp statuses.
- **Resume & Cover Letter Builder**: An AI-driven text generator with exportable templates for job applications.
- **Reverse To-Do List**: An app that logs what you accomplished today, rather than just what you need to do.
- **Receipt Scanner & Organizer**: Extracts data from receipts to track monthly spending.
- *...and many more to come!*

## 🏗 Architecture Overview

This project uses a highly modular **Kotlin Multiplatform (KMP) + Compose Multiplatform** architecture. The structure is separated into a **Core SDK** and isolated **App Repositories**.

### 1. The Core Engine (`dexstudio-core-sdk`)
Contains the foundational, generic building blocks shared across all applications.
- **`shared-ui`**: Design system tokens (colors, typography, spacing) and generic UI components (buttons, nav bars).
- **`shared-data`**: Core infrastructure (Ktor networking, Room databases, authentications).

### 2. The Application Shells (`apps/`)
Each individual app (e.g., `apphide`, `calculator`) is an isolated feature ecosystem. 
To share logic across Android, iOS, and Desktop *without* polluting the Core SDK, an app should ideally contain:
- **`android/`**: The Android execution shell (`MainActivity.kt`).
- **`ios/`**: The Xcode project for the iOS shell.
- **`desktop/`**: The JVM wrapper for Windows/Mac/Linux execution.
- **`shared/`**: *(Optional but recommended)* A specialized KMP library module containing the app's specific MVI Domain Logic and Compose UI screens (e.g., the Calculator math engine).

---

## 🚀 Commands & Development Workflow

### 🟢 Running Apps
To install and launch an app on a connected device/emulator via terminal, use the following generic command pattern (replace `<app_name>` with the folder name of your app, e.g., `apphide`, `calculator`, `todo-planner`):

```bash
.\gradlew :apps:<app_name>:android:installDebug
```
*(Note: To get Hot Reload/Live Edit, you must run the configuration directly through the Android Studio Play button!)*

### 🔨 Building & Compiling
To compile the Android APK without installing:
```bash
.\gradlew :apps:<app_name>:android:assembleDebug
```

### 📸 UI Testing (Roborazzi Screenshots)
We use Roborazzi for blazing-fast, automated UI snapshot testing on the JVM (no emulator required).

**1. Record Baseline Screenshots** (Run this when you intentionally change an app's UI):
```bash
.\gradlew :apps:<app_name>:android:recordRoborazziDebug
```
*Screenshots are saved locally in the app module's `src/test/screenshots/` directory.*

**2. Verify UI against Baseline** (Run this in CI to ensure no accidental UI regressions):
```bash
.\gradlew :apps:<app_name>:android:verifyRoborazziDebug
```

---

## Run Locally

**Prerequisites:**  [Android Studio](https://developer.android.com/studio)


1. Open Android Studio
2. Select **Open** and choose the directory containing this project
3. Allow Android Studio to fix any incompatibilities as it imports the project.
4. Create a file named `.env` in the project directory and set `GEMINI_API_KEY` in that file to your Gemini API key (see `.env.example` for an example)
5. Remove this line from the app's `build.gradle.kts` file: `signingConfig = signingConfigs.getByName("debugConfig")`
6. Run the app on an emulator or physical device
