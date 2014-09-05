@echo off
echo "Building using the offical forge gradle script"
gradle -b build_forge.gradle setupDecompWorkspace
gradle -b build_forge.gradle eclipse
pause
