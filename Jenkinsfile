pipeline {

    agent any

    parameters {

        string(
            name: 'PROJECT_PATH',
            defaultValue: 'D:\\SUJAN\\GitHubRepo\\ai-selenium-test-maintenance',
            description: 'Path of the project'
        )

        string(
            name: 'APP_PORT',
            defaultValue: '8000',
            description: 'Port for the application'
        )

        choice(
            name: 'APP_VERSION',
            choices: ['v1', 'v2'],
            description: 'Application version to test'
        )
    }

    stages {

        stage('Setup Tools') {

            steps {

                powershell '''

                    $ErrorActionPreference = "Stop"

                    $toolsPath = Join-Path $env:PROJECT_PATH "tools"

                    $javaPath = Join-Path $toolsPath "java"

                    $mavenPath = Join-Path $toolsPath "maven"

                    $pythonPath = Join-Path $toolsPath "python"

                    New-Item `
                        -ItemType Directory `
                        -Force `
                        -Path $toolsPath | Out-Null


                    # --------------------------------------------------
                    # Java 21
                    # --------------------------------------------------

                    $javaExe = Join-Path $javaPath "bin/java.exe"

                    if (-not (Test-Path $javaExe)) {

                        Write-Host "Java not found. Downloading Java 21..."

                        New-Item `
                            -ItemType Directory `
                            -Force `
                            -Path $javaPath | Out-Null

                        $javaZip = Join-Path $toolsPath "java.zip"

                        $javaUrl = "https://api.adoptium.net/v3/binary/version/jdk-21.0.10+7/windows/x64/jdk/hotspot/normal/adoptium"

                        Invoke-WebRequest `
                            -Uri $javaUrl `
                            -OutFile $javaZip

                        Expand-Archive `
                            -Path $javaZip `
                            -DestinationPath $toolsPath `
                            -Force

                        $extractedJava = Get-ChildItem $toolsPath -Directory |
                            Where-Object { $_.Name -like "jdk-21*" } |
                            Select-Object -First 1

                        if (-not $extractedJava) {

                            throw "Java extraction failed."

                        }

                        Move-Item `
                            -Path "$($extractedJava.FullName)/*" `
                            -Destination $javaPath `
                            -Force

                        Remove-Item `
                            $extractedJava.FullName `
                            -Recurse `
                            -Force

                        Remove-Item `
                            $javaZip `
                            -Force
                    }

                    Write-Host "Java available at: $javaExe"


                    # --------------------------------------------------
                    # Maven 3.9.12
                    # --------------------------------------------------

                    $mavenPath = Join-Path $toolsPath "maven"

                    $mvnExe = Join-Path $mavenPath "bin/mvn.cmd"

                    if (-not (Test-Path $mvnExe)) {

                        Write-Host "Maven not found. Downloading Maven 3.9.12..."

                        # Remove incomplete Maven installation
                        if (Test-Path $mavenPath) {

                            Write-Host "Removing incomplete Maven installation..."

                            Remove-Item `
                                $mavenPath `
                                -Recurse `
                                -Force
                        }

                        $mavenZip = Join-Path $toolsPath "maven.zip"

                        $mavenUrl = "https://archive.apache.org/dist/maven/maven-3/3.9.12/binaries/apache-maven-3.9.12-bin.zip"

                        Invoke-WebRequest `
                            -Uri $mavenUrl `
                            -OutFile $mavenZip

                        Expand-Archive `
                            -Path $mavenZip `
                            -DestinationPath $toolsPath `
                            -Force

                        $extractedMaven = Join-Path `
                            $toolsPath `
                            "apache-maven-3.9.12"

                        if (-not (Test-Path (Join-Path $extractedMaven "bin/mvn.cmd"))) {

                            throw "Maven extraction failed."

                        }

                        Rename-Item `
                            -Path $extractedMaven `
                            -NewName "maven"

                        Remove-Item `
                            $mavenZip `
                            -Force
                    }

                    Write-Host "Maven available at: $mvnExe"


                    # --------------------------------------------------
                    # Python 3.14.7
                    # --------------------------------------------------

                    $pythonExe = Join-Path $pythonPath "python.exe"

                    if (-not (Test-Path $pythonExe)) {

                        Write-Host "Python not found. Downloading Python 3.14.7..."

                        New-Item `
                            -ItemType Directory `
                            -Force `
                            -Path $pythonPath | Out-Null

                        $pythonZip = Join-Path $toolsPath "python.zip"

                        $pythonUrl = "https://www.python.org/ftp/python/3.14.7/python-3.14.7-embed-amd64.zip"

                        Invoke-WebRequest `
                            -Uri $pythonUrl `
                            -OutFile $pythonZip

                        Expand-Archive `
                            -Path $pythonZip `
                            -DestinationPath $pythonPath `
                            -Force

                        Remove-Item `
                            $pythonZip `
                            -Force
                    }

                    Write-Host "Python available at: $pythonExe"


                    # --------------------------------------------------
					# Verify Tools
					# --------------------------------------------------
					
					Write-Host ""
					Write-Host "===== Tools Ready ====="
					
					Write-Host "Java: $javaExe"
					Write-Host "Maven: $mvnExe"
					Write-Host "Python: $pythonExe"
					
					Write-Host "======================"

                '''
            }
        }


        stage('Configure Test') {

		    steps {
		
		        powershell '''
		
		            $url = "http://localhost:$env:APP_PORT/$env:APP_VERSION/login.html"
		
		            Write-Host "Application URL: $url"
		
		            $file = Join-Path `
		                $env:PROJECT_PATH `
		                "src/test/java/resources/GlobalSettings.properties"
		
		
		            if (-not (Test-Path $file)) {
		
		                throw "GlobalSettings.properties not found: $file"
		
		            }
		
		
		            # Read existing properties
		            $content = Get-Content $file
		
		
		            # Update only application_url
		            $content = $content -replace `
		                '^application_url=.*$', `
		                "application_url=$url"
		
		
		            # Write properties back
		            Set-Content `
		                -Path $file `
		                -Value $content
		
		
		            Write-Host "GlobalSettings.properties updated successfully."
		
		        '''
		    }
		}


        stage('Start Application') {

            steps {

                powershell '''

                    $appPath = Join-Path `
                        $env:PROJECT_PATH `
                        "demo-app"

                    $pythonExe = Join-Path `
                        $env:PROJECT_PATH `
                        "tools/python/python.exe"

                    Write-Host "Starting application..."

                    Write-Host "Application Path: $appPath"

                    Write-Host "Python: $pythonExe"

                    Write-Host "Port: $env:APP_PORT"


                    if (-not (Test-Path $appPath)) {

                        throw "Application folder not found: $appPath"

                    }


                    if (-not (Test-Path $pythonExe)) {

                        throw "Python executable not found: $pythonExe"

                    }


                    $process = Start-Process `
                        -FilePath $pythonExe `
                        -ArgumentList "-m", "http.server", "$env:APP_PORT" `
                        -WorkingDirectory $appPath `
                        -PassThru


                    Set-Content `
                        -Path (Join-Path $env:PROJECT_PATH ".server.pid") `
                        -Value $process.Id


                    Start-Sleep -Seconds 3


                    if ($process.HasExited) {

                        throw "Application server stopped unexpectedly."

                    }


                    Write-Host "Application started successfully."

                    Write-Host "Process ID: $($process.Id)"

                '''
            }
        }


        stage('Run Tests') {

            steps {

                bat '''

                    cd /d "%PROJECT_PATH%"

                    set "JAVA_HOME=%PROJECT_PATH%\\tools\\java"

                    set "MAVEN_HOME=%PROJECT_PATH%\\tools\\maven"

                    "%MAVEN_HOME%\\bin\\mvn.cmd" test

                '''
            }
        }
    }


    post {

        always {

            powershell '''

                $pidFile = Join-Path `
                    $env:PROJECT_PATH `
                    ".server.pid"


                if (Test-Path $pidFile) {

                    $serverPid = Get-Content $pidFile

                    Write-Host "Stopping application. Process ID: $serverPid"


                    if (Get-Process `
                        -Id $serverPid `
                        -ErrorAction SilentlyContinue) {

                        Stop-Process `
                            -Id $serverPid `
                            -Force

                        Write-Host "Application stopped successfully."

                    }


                    Remove-Item `
                        $pidFile `
                        -Force `
                        -ErrorAction SilentlyContinue
                }

            '''
        }
    }
}