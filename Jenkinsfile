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
                         sh 'docker build -t kaddemImage:v1 -f Dockerfile ./'
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
