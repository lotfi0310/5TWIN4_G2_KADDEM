pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                https://github.com/lotfi0310/5TWIN4_G2_KADDEM.git
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube Analysis') {
                    steps {
                        withSonarQubeEnv('SonarQube') {
                            sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                        }
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