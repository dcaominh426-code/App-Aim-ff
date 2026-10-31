# Ultra Aim FF Trainer
Independent Android aim trainer. No Free Fire integration, no cheating or game modifications.

## GitHub Actions (phone only)
1. Create a public or private GitHub repository.
2. Upload all files and folders from this project ZIP to the repository root, including `.github/workflows/build.yml`.
3. Open **Actions > Build Android APK > Run workflow**.
4. After the workflow completes, open its run, download **UltraAimFF-debug-APK** from Artifacts, unzip it, then install `app-debug.apk`.

## Controls
Drag anywhere on the play area to move the cyan crosshair. Release to fire. Red moving target gives points. Buttons at bottom adjust target speed. Best score persists.

## Notes
Requires Android SDK 35 and Java 17. GitHub-hosted runner's Gradle availability may change; if `gradle` is unavailable, use a Gradle wrapper or a Gradle setup action.
