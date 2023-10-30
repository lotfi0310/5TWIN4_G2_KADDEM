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
        stage('Test with Maven') {
                               steps {
                                       sh 'mvn test'
                                     }
                                 }

        stage('SonarQube Analysis') {
             steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                   }
             }
        stage('Deploy to Nexus') {
             steps {
                    sh 'mvn deploy -DskipTests=true'
                         }
                     }
    }

    post {
        success {
            echo 'Build successfully'
        }
        failure {
            echo 'failed '
        }
    }
}