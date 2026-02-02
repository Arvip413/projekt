@echo off
echo Running Application...
java --module-path "C:\Users\User\Downloads\openjfx-21.0.10_windows-x64_bin-sdk\javafx-sdk-21.0.10\lib" --add-modules javafx.controls,javafx.fxml -cp out com.arvin.store.Launcher
pause
