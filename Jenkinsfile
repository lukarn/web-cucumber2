//variables
def network="network-${env.BUILD_TAG}"
def seleniumHub="selenium-hub-${env.BUILD_TAG}"
def chrome="chrome-${env.BUILD_TAG}"
def firefox="firefox-${env.BUILD_TAG}"
//def testVar="build-no-${env.BUILD_NUMBER}"
//def firefox='firefox-${env.BUILD_NUMBER}'
//def containertest='conatinertest-${env.BUILD_NUMBER}'

pipeline {
    agent any
    tools {
            jdk 'jdk11'
            maven 'myMavenRep'
        }

    stages {

        stage('Clean screenShots directory') {
                steps {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                    echo 'Cleaning screenShots directory..'
                    bat 'rmdir /Q /S screenShots'
                    bat 'mkdir screenShots'
                    }
                }
        }

        stage('Run selenium grid') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE'){
                script{
                    for (int i = 0; i < 50; i++) {
                        bat 'docker version | findstr "Server" || PING localhost -n 10 > NUL'
                             try {
                                 echo 'Waiting for Docker'
                                 bat 'docker version | findstr "Server"'
                                 i=50
                                 }
                             catch (Exception e) {
                                 echo "Stage failed, but we continue"
                                 }
                        }
                    //bat 'docker version | findstr "Server" & if %errorlevel% EQU 0 (echo OK ) Else ( Echo ERROR FAILED &color CF )'
                    //bat 'docker version | findstr "Server" & if %errorlevel% NEQ 0 (echo NOOOOOK ) Else ( Echo OKOKOK )'
                    //bat 'if %errorlevel% NEQ 0 (timeout 10) Else ( Echo OKOKOK )'

                    bat 'docker version'

                    println("JOB_NAME: ${env.JOB_NAME}")
                    println("JOB_BASE_NAME: ${env.JOB_BASE_NAME}")
                    println("BUILD_TAG: ${env.BUILD_TAG}")
                    //println("debug test: ${testVar}")
                }

                //bat 'docker-compose up -d' // Docker Selenium

                bat "docker system prune -f"
                bat "docker network create ${network}"
                bat "docker run -d -p 4444:4444 --shm-size=2g --name ${seleniumHub} --network ${network} selenium/hub:3.141.59-20200525"
                bat "docker run -d -e HUB_PORT_4444_TCP_ADDR=${seleniumHub} -e HUB_PORT_4444_TCP_PORT=4444 --shm-size=1g --network ${network} --name ${chrome} selenium/node-chrome:3.141.59-20200525"
                bat "docker run -d -e HUB_PORT_4444_TCP_ADDR=${seleniumHub} -e HUB_PORT_4444_TCP_PORT=4444 --shm-size=1g --network ${network} --name ${firefox} selenium/node-firefox:3.141.59-20200525"
                }
            }
        }

        stage('Compile Stage') {
                steps {
                    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE'){
                    echo 'Starting Compile..'
                    bat 'mvn clean compile'
                    }
                }
        }

        stage('Testing Stage1') {
                 steps {
                     catchError(buildResult: 'FAILURE', stageResult: 'FAILURE'){
                     echo 'Starting Testing..'
                     bat 'mvn test'
                     }
                 }
        }


        //stage('Deploy Stage') {
                //steps {
                    //echo 'Starting Deploy.. - empty for now'
                    //withMaven (maven : 'myMavenRep'){
                        //bat 'mvn deploy'
                    //}
                //}
        //}

        stage('Tear down stage') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE'){
                echo 'Starting Tear down..'
                //bat 'docker-compose down'
                //bat 'docker system prune -f'

                bat "docker rm -vf ${chrome}"
                bat "docker rm -vf ${firefox}"
                bat "docker rm -vf ${seleniumHub}"
                bat "docker network rm ${network}"
                bat "docker system prune -f"
                }
            }
        }

    }
}