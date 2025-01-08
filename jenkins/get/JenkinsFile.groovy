pipeline {
    agent any

    tools {
        // Указываем JDK и Maven, если они настроены в Jenkins
        jdk '19.0.2'
        maven '3.9.9'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/nikolay']],
                        userRemoteConfigs: [[
                                                    url: 'git@github.com:Inikolay/api-jenkons.git',
                                                    credentialsId: 'jenkins'
                                            ]]
                ])
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn -Dtest=com.** verify'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                echo 'Generating Allure Report...'
                allure([
                        results: [[path: 'target/allure-results']],
                        reportBuildPolicy: 'ALWAYS'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}