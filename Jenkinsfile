pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube Analysis') {
                    steps {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=eya'
                    }
                }


             stage('Test Unit') {
                    steps {
                        sh 'mvn test'
                    }
                }

    }

    post {
        success {
            echo 'Build successful'
        }
        failure {
            echo 'fail'
        }
    }
}