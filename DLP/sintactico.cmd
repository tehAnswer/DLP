cls
cd %~dp0
cd src\syntax
..\..\tool\yacc -J -v -Jpackage=syntax -Jsemantic=Object .\sinj.y
pause
