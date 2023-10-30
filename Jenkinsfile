pipeline {
    agent any


    environment {

        DOCKER_IMAGE_NAME = 'eyakhechine/kaddemproject'
        DOCKER_IMAGE_TAG = 'v4'
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
                stage('building image') {
                                    steps {
                                        sh 'docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG -f Dockerfile ./'
                                    }
                                }

                                             stage('dockerhub') {
                                                          steps {
                                                              withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                                                                  sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                                                              }
                                                              sh "docker push $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG"
                                                          }
                                    }
   stage('docker-compose') {
                                                steps {

                                                  sh 'docker compose up -d'

                                                  echo 'docker-compose'
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