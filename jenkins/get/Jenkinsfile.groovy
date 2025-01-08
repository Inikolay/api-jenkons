pipeline {
    agent any

    tools {
        // Указываем JDK и Maven, если они настроены в Jenkins
        jdk '19.0.2'
        maven '3.9.9'
    }

    parameters{
        booleanParams(defoultValue: true, description 'run teat set', name: "get")
    }

    stages {
        stage('rest test') {
            steps {
                when {
                    expression{
                        return param.rest
                    }
                }
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