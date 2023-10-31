pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = 'lotfilouiz-5twin4-g2-kaddem'
        DOCKER_IMAGE_TAG = 'v1'
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
           stage('push  to dockerhub') {
                                  steps {
                                      withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')])
                                       {
                                          sh "docker login -u lotfi0310 -p lotfidevops
                                      }
                                      sh "docker push $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG"
                                  }
            }
             stage('Run Spring && MySQL Containers') {
                                 steps {

                                   sh 'docker compose up -d'

                                   echo 'Run Spring && MySQL Containers'
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
