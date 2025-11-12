@echo off


setlocal EnableExtensions EnableDelayedExpansion

if not "%~1"=="" set "PROJECT_DIR=%~1"
if not "%~2"=="" set "JAVA_EXE=%~2"
if not "%~3"=="" set "ARGFILE=%~3"

if not defined PROJECT_DIR (
  set "PROJECT_DIR=%~dp0"
  if "%PROJECT_DIR:~-1%"=="\" set "PROJECT_DIR=%PROJECT_DIR:~0,-1%"
)

if not defined JAVA_EXE (
  if defined JAVA_HOME if exist "%JAVA_HOME%\bin\java.exe" set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
)

if not defined JAVA_EXE (
  for /f "usebackq delims=" %%J in (`where java 2^>nul`) do (
    set "JAVA_EXE=%%~J"
    goto :_found_java
  )
)
:_found_java

if not defined JAVA_EXE (
  set "JAVA_EXE=C:\Program Files\Java\jdk-24\bin\java.exe"
)

if not defined ARGFILE (
  for /f "delims=" %%F in ('dir "%TEMP%\cp_*.argfile" /b /o-d 2^>nul') do (
    set "ARGFILE=%TEMP%\%%F"
    goto :_argfile_found
  )
)
:_argfile_found

echo Project directory: "%PROJECT_DIR%"
echo Java executable: "%JAVA_EXE%"
if defined ARGFILE (
  echo Argfile: "%ARGFILE%"
) else (
  echo Argfile: (none found or provided)
)

pushd "%PROJECT_DIR%" || (
  echo Failed to change directory to "%PROJECT_DIR%"
  exit /b 1
)

if not exist "%JAVA_EXE%" (
  echo Java executable not found at "%JAVA_EXE%".
  echo Try setting the JAVA_EXE environment variable or pass the path as the second argument.
  popd
  exit /b 2
)

echo Running Driver from %PROJECT_DIR% ...
if defined ARGFILE if exist "%ARGFILE%" (
  "%JAVA_EXE%" @"%ARGFILE%" Driver
) else (
  "%JAVA_EXE%" Driver
)

set "RC=%ERRORLEVEL%"

popd
endlocal & exit /b %RC%