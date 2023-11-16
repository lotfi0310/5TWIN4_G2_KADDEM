pipeline {
    agent any


    environment {

        DOCKER_IMAGE_NAME = 'eyakhechine/eyakhechine-5twin-g2-kaddem:v5'
        DOCKER_IMAGE_TAG = 'v5'
    }


    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube') {
                    steps {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=eya'
                    }
                }


             stage('Test Unit Mockito') {
                    steps {
                        sh 'mvn test'
                    }
                }

             stage(' Nexus Deployment') {
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

         mail subject: "[Integration Continue] ${env.JOB_NAME} - Compilation # ${env.BUILD_NUMBER} - Build has been fixed",
                     body: "  pipeline réussi  ",
                     from: 'Admin',
                     to: 'khechineeya@gmail.com'

             }
        failure {
             echo 'Echec pipeline.'

       }
    }
}