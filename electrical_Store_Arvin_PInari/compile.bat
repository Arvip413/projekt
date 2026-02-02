@echo off
if not exist "out" mkdir out
echo Finding source files...
dir /s /b src\*.java > sources.txt
echo Compiling...
javac --module-path "C:\Users\User\Downloads\openjfx-21.0.10_windows-x64_bin-sdk\javafx-sdk-21.0.10\lib" --add-modules javafx.controls,javafx.fxml -d out @sources.txt
copy src\style.css out\style.css
copy src\logo.png out\logo.png
if %errorlevel% neq 0 (
    echo Compilation Failed!
    pause
    exit /b %errorlevel%
)
echo Compilation Successful!
del sources.txt
pause
