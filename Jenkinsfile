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
