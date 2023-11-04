pipeline {
    agent any
    stages {
        stage('GIT') {
            steps {
                checkout scm
            }
        }

        stage('COMPILING') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SONARQUBE') {
                     steps {
                            sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ahmed2000'
                           }
                     }
       stage('JUNIT/MOCKITO') {
                                      steps {
                                              sh 'mvn test'
                                            }
                                        }
         stage('Nexus') {
                    steps {
                           sh 'mvn deploy -DskipTests=true'
                                }
                         }


         stage('Docker images')
                 {
                      steps {
                         sh 'docker build -t kaddemimage:v4 -f Dockerfile ./'
                               }

                 }
          stage('dockerhub') {
                                           steps {

                                      sh "docker login -u ahmed1990909 -p ahmed2000"
                                      sh "docker tag kaddemimage:v4 ahmed1990909/ahmedbenguebila-5twin4-g2-kaddem:kaddemimage:v4"
                                      sh "docker push  ahmed1990909/ahmedbenguebila-5twin4-g2-kaddem:kaddemimage:v4"
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
