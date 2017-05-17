$Host.UI.RawUI.WindowTitle = "Management-Server"
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrEurekaServer";cd ".\OcrEurekaServer\build\libs";java -jar eureka-service-0.0.1-SNAPSHOT.jar}'
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27011-Rest";cd ".\MongoDB";.\mongo1.bat}'
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27012-Menu";cd ".\MongoDB";.\mongo2.bat}'
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27013-Food";cd ".\MongoDB";.\mongo3.bat}'
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27014-Order";cd ".\MongoDB";.\mongo4.bat}'
start-sleep -s 3
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrGateWay";cd ".\OcrGateWay\build\libs";java -jar OcrGateway-0.0.1-SNAPSHOT.jar}'
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrRest";cd ".\OcrRest\build\libs";java -jar OcrRest-0.0.1-SNAPSHOT.jar}'
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrMenu";cd ".\OcrMenu\build\libs";java -jar OcrMenu-0.0.1-SNAPSHOT.jar}'
invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrOrder";cd ".\OcrOrder\build\libs";java -jar OcrOrder-0.0.1-SNAPSHOT.jar}'
pause
Get-Process | Where-Object {$_.MainWindowTitle -ne "OcrGateWay"} | stop-process