pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven', type: 'Tool'  // Укажите название инструмента Maven
        JAVA_HOME = tool name: 'JDK', type: 'Tool'    // Укажите название JDK
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    // Устанавливаем зависимости
                    sh "'${MAVEN_HOME}/bin/mvn' clean install"
                }
            }
        }

        stage('Run API Tests') {
            steps {
                script {
                    // Запуск тестов API с использованием Maven
                    sh "'${MAVEN_HOME}/bin/mvn' clean test -Dtest=com.yourpackage.*"
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    // Генерация отчета Allure
                    sh "'${MAVEN_HOME}/bin/mvn' allure:serve"
                }
            }
        }
    }

    post {
        always {
            // Завершающие действия (например, очистка ресурсов)
            cleanWs()
        }

        success {
            echo 'Pipeline успешно завершен!'
        }

        failure {
            echo 'Процесс завершился с ошибкой.'
        }
    }
}