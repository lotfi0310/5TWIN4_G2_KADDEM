pipeline {
    agent any


    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Configure your DockerHub credentials in Jenkins
        DOCKER_IMAGE_NAME = 'eyakhechine/kaddemproject'
        DOCKER_IMAGE_TAG = 'latest'
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

             stage('Deploy to Nexus') {
                         steps {
                             sh 'mvn deploy -DskipTests'
                         }
                     }
                       stage('Push Spring Boot Image to DockerHub') {
                                 steps {
                                     withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'eyakhechine', passwordVariable: 'college159')]) {
                                         sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                                     }
                                     sh "docker push $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG"
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