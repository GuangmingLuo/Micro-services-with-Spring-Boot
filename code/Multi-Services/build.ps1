echo "================================================"
echo "=              Building Projects               ="
echo "================================================"
echo ""
echo ":: Building OcrEurekaServer"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrEurekaServer";cd ".\OcrEurekaServer\";.\gradlew build;pause}'

echo ":: Building OcrFood"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrFood";cd ".\OcrFood\";.\gradlew build}'

echo ":: Building OcrGateway"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrGateway";cd ".\OcrGateway\";.\gradlew build}'

echo ":: Building OcrMenu"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrMenu";cd ".\OcrMenu\";.\gradlew build}'

echo ":: Building OcrOrder"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrOrder";cd ".\OcrOrder\";.\gradlew build}'

echo ":: Building OcrRest"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrRest";cd ".\OcrRest\";.\gradlew build}'

echo ":: Building OcrUser"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrUser";cd ".\OcrUser\";.\gradlew build}'

echo ":: Building OcrClient"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrClient";cd ".\OcrClient\";.\gradlew build}'
echo ""
pause