pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = 'lotfilouiz-5TWIN4-G2-kaddem'
        DOCKER_IMAGE_TAG = 'v2'
    }
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

        stage('building image')
        {
             steps {
                sh 'docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG -f Dockerfile ./'
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