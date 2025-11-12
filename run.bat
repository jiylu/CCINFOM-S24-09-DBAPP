@echo off
REM
REM

set "PROJECT_DIR=C:\Users\Jeremy\Documents\dlsu\ccinfom\dbapp"
set "JAVA_EXE=C:\Program Files\Java\jdk-24\bin\java.exe"
set "ARGFILE=C:\Users\Jeremy\AppData\Local\Temp\cp_36sqcy9gavug02ldlxfjbdh7l.argfile"

pushd "%PROJECT_DIR%" || (
  echo Failed to change directory to %PROJECT_DIR%
  exit /b 1
)

if not exist "%JAVA_EXE%" (
  echo Java executable not found at "%JAVA_EXE%".
  exit /b 2
)

echo Running Driver from %PROJECT_DIR% ...
"%JAVA_EXE%" @"%ARGFILE%" Driver
set "RC=%ERRORLEVEL%"

popd
exit /b %RC%