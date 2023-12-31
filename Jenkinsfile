pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = 'lotfilouiz-5twin4-g2-kaddem'
        DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}" // Using Jenkins BUILD_NUMBER as the tag
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

        stage('lotfi SonarQube ') {
             steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                   }
             }

        stage('Deploy') {
             steps {
                    sh 'mvn deploy -DskipTests=true'
                         }
                     }

        stage('building docker image')
        {
             steps {
                sh 'docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG -f Dockerfile ./'
                      }
        }

           stage('dockerhub') {
                                  steps {

                             sh "docker login -u lotfi0310 -p lotfidevops"
                             sh "docker tag $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG lotfi0310/lotfilouiz-5twin4-g2-kaddem:$DOCKER_IMAGE_TAG"
                             sh "docker push  lotfi0310/lotfilouiz-5twin4-g2-kaddem:$DOCKER_IMAGE_TAG"
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
