# Functions for building modules
function build-OcrUser {
	$cmd= 'echo " :: Building OcrUser"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrUser";cd ".\OcrUser\";.\gradlew build}'	
}
function build-OcrClient {
	$cmd= 'echo " :: Building OcrClient"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrClient";cd ".\OcrClient\";.\gradlew build}'	
}
function build-OcrEurekaServer {
	$cmd= 'echo " :: Building OcrEurekaServer"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrEurekaServer";cd ".\OcrEurekaServer\";.\gradlew build}'	
}
function build-OcrFood {
	$cmd= 'echo " :: Building OcrFood"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrFood";cd ".\OcrFood\";.\gradlew build}'	
}
function build-OcrGateway {
	$cmd= 'echo " :: Building OcrGateway"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrGateway";cd ".\OcrGateway\";.\gradlew build}'	
}
function build-OcrMenu {
	$cmd= 'echo " :: Building OcrMenu"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrMenu";cd ".\OcrMenu\";.\gradlew build}'	
}
function build-OcrOrder {
	$cmd= 'echo " :: Building OcrOrder"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrOrder";cd ".\OcrOrder\";.\gradlew build}'	
}
function build-OcrRest {
	$cmd= 'echo " :: Building OcrRest"'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "Building OcrRest";cd ".\OcrRest\";.\gradlew build}'	
}
function build-All{
	build-OcrRest
	build-OcrOrder
	build-OcrMenu
	build-OcrEurekaServer
	build-OcrGateway
	build-OcrUser
	build-OcrFood
	build-OcrClient
	$cmd = "Write-Host ' :: Building all modules'"
}

# Functions for running modules
function run-OcrEurekaServer{
	$cmd = "Write-Host ' :: running OcrEurekaServer'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrEurekaServer";cd ".\OcrEurekaServer\build\libs";java -jar eureka-service-0.0.1-SNAPSHOT.jar}'
}
function run-OcrFood{
	$cmd = "Write-Host ' :: running OcrFood'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27013-Food";cd ".\MongoDB";.\mongo3.bat}'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrFood";cd ".\OcrFood\build\libs";java -jar OcrFood-0.0.1-SNAPSHOT.jar}'
}
function run-OcrRest{
	$cmd = "Write-Host ' :: running OcrRest'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27011-Rest";cd ".\MongoDB";.\mongo1.bat}'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrRest";cd ".\OcrRest\build\libs";java -jar OcrRest-0.0.1-SNAPSHOT.jar}'
}
function run-OcrRest2{
	$cmd = "Write-Host ' :: running OcrRest'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrRest";cd ".\OcrRest\build\libs";java -jar OcrRest-0.0.1-SNAPSHOT.jar 91 8091;pause}'
	pause
}
function run-OcrMenu{
	$cmd = "Write-Host ' :: running OcrMenu'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27012-Menu";cd ".\MongoDB";.\mongo2.bat}'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrMenu";cd ".\OcrMenu\build\libs";java -jar OcrMenu-0.0.1-SNAPSHOT.jar}'
}
function run-OcrOrder{
	$cmd = "Write-Host ' :: running OcrOrder'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "MongoDB-27014-Order";cd ".\MongoDB";.\mongo4.bat}'
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrOrder";cd ".\OcrOrder\build\libs";java -jar OcrOrder-0.0.1-SNAPSHOT.jar}'
}
function run-OcrUser{
	$cmd = "Write-Host ' :: running OcrUser'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrUser";cd ".\OcrUser\build\libs";java -jar OcrUser-0.0.1-SNAPSHOT.jar}'
}
function run-OcrGateway{
	$cmd = "Write-Host ' :: running OcrGateway'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrGateWay";cd ".\OcrGateWay\build\libs";java -jar OcrGateway-0.0.1-SNAPSHOT.jar}'

}
function run-OcrClient{
	$cmd = "Write-Host ' :: running OcrClient'"
	invoke-expression 'cmd /c start powershell -Command {$Host.UI.RawUI.WindowTitle = "OcrClient";cd ".\OcrClient\build\libs";java -jar OcrClient-0.0.1-SNAPSHOT.jar}'

}


function run-All{
	$cmd = "Write-Host ' :: running all modules'"
	run-OcrFood
	run-OcrRest
	run-OcrMenu
	run-OcrOrder
	run-OcrUser
	run-OcrGateway
}




# Testing
function find-window{
	echo "Window title?"
	$windowname = Read-Host -Prompt " =>"
	Get-Process | where {$_.mainWindowTitle -eq $windowname} | ForEach {
    	echo $_
	}
	echo ""
	pause
}

# Functions for closing windows
function kill-OcrEurekaServer{
	$windowname = "OcrEurekaServer"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-OcrClient{
	$windowname = "OcrClient"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-OcrFood{
	$windowname = "OcrFood"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
	$windowname = "MongoDB-27013-Food"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-OcrRest{
	$windowname = "OcrRest"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
	$windowname = "MongoDB-27011-Rest"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-OcrMenu{
	$windowname = "OcrMenu"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
	$windowname = "MongoDB-27012-Menu"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-OcrOrder{
	$windowname = "OcrOrder"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
	$windowname = "MongoDB-27014-Order"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-OcrUser{
	$windowname = "OcrUser"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-OcrGateway{
	$windowname = "OcrGateWay"
	Get-Process  | where {$_.mainWindowTitle -eq $windowname} | ForEach {
		Stop-Process $_
	}
}
function kill-All{
	kill-OcrEurekaServer
	kill-OcrClient
	kill-OcrFood
	kill-OcrRest
	kill-OcrMenu
	kill-OcrOrder
	kill-OcrUser
	kill-OcrGateway
}


# Other functions
function install-entities{
	$cmd = "Write-Host ' :: Updating Entities in local Maven repository'"
	invoke-expression 'cmd /c start powershell -Command {cd ./Entities;mvn package;mvn install}' 
}


# Interface code

$quit = $false
$cmd = "Write-Host '' -nonewline"
$Host.UI.RawUI.WindowTitle = "OCR CLI"

Do{
	Clear-Host
	echo " ============================================================================="
	echo " |                                                                           |"
	echo " |             Command line interface for Online Cash Register               |"
	echo " |                                                                           |"
	echo " ============================================================================="
	echo " |  Type 'help' for more information  |"
	echo " ======================================"
	echo ""
	Invoke-Expression $cmd
	echo ""
	$choice = Read-Host -Prompt ' =>'

	switch($choice){
		"help" {$cmd = '
			Write-Host " :: The Possible commands are: ";
			Write-Host " help" -foreground green;
			Write-Host " build" -foreground yellow;
			Write-Host " run" -foreground yellow;
			Write-Host " kill" -foreground yellow;
			Write-Host " quit" -foreground red;
			'
		}
		"build" {
			$cmd = '
			Write-Host " :: Please specify which module to build:";
			Write-Host " build " -nonewline;
			Write-Host "all" -foreground green;
			Write-Host " build " -nonewline;
			Write-Host "user" -foreground yellow;
			Write-Host " build " -nonewline;
			Write-Host "client" -foreground yellow;
			Write-Host " build " -nonewline;
			Write-Host "eureka" -foreground yellow;
			Write-Host " build " -nonewline;
			Write-Host "food" -foreground yellow;
			Write-Host " build " -nonewline;
			Write-Host "gateway" -foreground yellow;
			Write-Host " build " -nonewline;
			Write-Host "menu" -foreground yellow;
			Write-Host " build " -nonewline;
			Write-Host "order" -foreground yellow;
			Write-Host " build " -nonewline;
			Write-Host "rest" -foreground yellow;
			'
		}
		"build user" {build-OcrUser}
		"build client" {build-OcrClient}
		"build eureka" {build-OcrEurekaServer}
		"build food" {build-OcrFood}
		"build gateway" {build-OcrGateway}
		"build menu" {build-OcrMenu}
		"build order" {build-OcrOrder}
		"build rest" {build-OcrRest}
		"build all"{build-All}


		"run" {
			$cmd = '
			Write-Host " :: Please specify which module to run:";
			Write-Host " run " -nonewline;
			Write-Host "all" -foreground green;
			Write-Host " run " -nonewline;
			Write-Host "user" -foreground yellow;
			Write-Host " run " -nonewline;
			Write-Host "client" -foreground yellow;
			Write-Host " run " -nonewline;
			Write-Host "eureka" -foreground yellow;
			Write-Host " run " -nonewline;
			Write-Host "food" -foreground yellow;
			Write-Host " run " -nonewline;
			Write-Host "gateway" -foreground yellow;
			Write-Host " run " -nonewline;
			Write-Host "menu" -foreground yellow;
			Write-Host " run " -nonewline;
			Write-Host "order" -foreground yellow;
			Write-Host " run " -nonewline;
			Write-Host "rest" -foreground yellow;
			'
		}
		"run eureka" {run-OcrEurekaServer}
		"run food" {run-OcrFood}
		"run food2" {run-OcrFood2}
		"run gateway" {run-OcrGateway}
		"run user" {run-OcrUser}
		"run order" {run-OcrOrder}
		"run menu" {run-OcrMenu}
		"run rest" {run-OcrRest}
		"run rest2" {run-OcrRest2}
		"run client" {run-OcrClient}
		"run all" {run-All}

		"kill" {
			$cmd = '
			Write-Host " :: Please specify which module to kill:";
			Write-Host " kill " -nonewline;
			Write-Host "all" -foreground green;
			Write-Host " kill " -nonewline;
			Write-Host "user" -foreground yellow;
			Write-Host " kill " -nonewline;
			Write-Host "client" -foreground yellow;
			Write-Host " kill " -nonewline;
			Write-Host "eureka" -foreground yellow;
			Write-Host " kill " -nonewline;
			Write-Host "food" -foreground yellow;
			Write-Host " kill " -nonewline;
			Write-Host "gateway" -foreground yellow;
			Write-Host " kill " -nonewline;
			Write-Host "menu" -foreground yellow;
			Write-Host " kill " -nonewline;
			Write-Host "order" -foreground yellow;
			Write-Host " kill " -nonewline;
			Write-Host "rest" -foreground yellow;
			'
		}
		"kill eureka" {kill-OcrEurekaServer;kill-OcrEurekaServer}
		"kill food" {kill-OcrFood;kill-OcrFood}
		"kill gateway" {kill-OcrGateway;kill-OcrGateway}
		"kill user" {kill-OcrUser;kill-OcrUser}
		"kill order" {kill-OcrOrder;kill-OcrOrder}
		"kill menu" {kill-OcrMenu;kill-OcrMenu}
		"kill rest" {kill-Ocr rest;kill-Ocr rest}
		"kill client" {kill-OcrClient;kill-OcrClient}
		"kill all" {kill-All;kill-All}



		"find window" {find-window}
		"install entities" {install-entities}

		"quit" {$quit=$true}
		"stop" {$quit=$true}
		
		
		default {$cmd = 'echo " Unknown command, try again."'}

	}

}Until($quit -eq $true)